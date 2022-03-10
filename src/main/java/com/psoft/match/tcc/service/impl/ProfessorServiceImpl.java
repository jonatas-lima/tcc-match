package com.psoft.match.tcc.service.impl;

import com.psoft.match.tcc.dto.ProfessorDTO;
import com.psoft.match.tcc.dto.TCCDTO;
import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.OrientationProposal;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.tcc.TCCProposal;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.model.user.Student;
import com.psoft.match.tcc.model.user.User;
import com.psoft.match.tcc.repository.user.ProfessorRepository;
import com.psoft.match.tcc.repository.user.UserRepository;
import com.psoft.match.tcc.service.*;
import com.psoft.match.tcc.util.exception.professor.ProfessorNotFoundException;
import com.psoft.match.tcc.util.exception.user.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TCCProposalService tccProposalService;

    @Autowired
    private AuthService authService;

    @Autowired
    private OrientationProposalService orientationProposalService;

    @Autowired
    private TCCService tccService;

    @Autowired
    private StudyAreaService studyAreaService;

    @Override
    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    @Override
    public List<Professor> getAvailableProfessors() {
        return professorRepository
                .findAll()
                .stream()
                .filter(professor -> professor.getQuota() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Professor> getAvailableProfessorsWithSharedInterests(Student student) {
        return this.getAvailableProfessors()
                .stream()
                .filter(professor -> this.hasSharedInterest(professor, student))
                .collect(Collectors.toList());
    }

    private boolean hasSharedInterest(Professor professor, Student student) {
        for(StudyArea s : student.getInterestedStudyAreas()) {
            if (professor.getInterestedStudyAreas().contains(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Professor findProfessorById(Long id) {
        return professorRepository.findById(id).orElseThrow(() -> new ProfessorNotFoundException(id));
    }

    @Transactional
    @Override
    public Professor createProfessor(ProfessorDTO professorDTO) {
        User user = userRepository.findUserByEmail(professorDTO.getEmail()).orElse(null);
        if (user != null) throw new UserAlreadyExistsException(professorDTO.getEmail());

        Professor professor = this.buildProfessor(professorDTO);
        userRepository.save(professor);
        return professorRepository.save(professor);
    }

    @Transactional
    @Override
    public Professor updateProfessor(Long id, ProfessorDTO professorDTO) {
        Professor professor = this.findProfessorById(id);
        this.updateProfessor(professor, professorDTO);

        userRepository.save(professor);
        return professorRepository.save(professor);
    }

    @Transactional
    @Override
    public void deleteProfessor(Long id) {
        Professor professor = this.findProfessorById(id);
        userRepository.delete(professor);
        professorRepository.delete(professor);
    }

    @Transactional
    @Override
    public void declareOrientationInterest(Long tccProposalId) {
        Professor professor = authService.getLoggedUser();
        TCCProposal tccProposal = tccProposalService.findTCCProposalById(tccProposalId);

        OrientationProposal orientationProposal = new OrientationProposal(professor, tccProposal);
        professor.addOrientationInterest(orientationProposal);

        tccProposal.getStudent().notify();
        orientationProposalService.saveTccProposal(orientationProposal);
        professorRepository.save(professor);
    }

    @Transactional
    @Override
    public TCC createTCC(TCCDTO tccdto) {
        Professor professor = authService.getLoggedUser();
        Collection<StudyArea> studyAreas = studyAreaService.findStudyAreasById(tccdto.getStudyAreasIds());

        TCC tcc = new TCC(tccdto.getTitle(), tccdto.getDescription(), professor, studyAreas);
        professor.addOrientedTCC(tcc);
        professor.decrementQuota();

        tcc = tccService.saveTCC(tcc);
        professorRepository.save(professor);

        studyAreas.forEach(StudyArea::notifyStudent);

        return tcc;
    }

    private Professor buildProfessor(ProfessorDTO professorDTO) {
        String encryptedPassword = passwordEncoder.encode(professorDTO.getPassword());
        return new Professor(professorDTO.getFullName(), professorDTO.getEmail(), professorDTO.getUsername(), encryptedPassword, professorDTO.getLabs(), professorDTO.getQuota());
    }

    private void updateProfessor(Professor oldProfessor, ProfessorDTO newProfessor) {
        oldProfessor.setEmail(newProfessor.getEmail());
        oldProfessor.setUsername(newProfessor.getUsername());
        oldProfessor.setFullName(newProfessor.getFullName());
    }

}
