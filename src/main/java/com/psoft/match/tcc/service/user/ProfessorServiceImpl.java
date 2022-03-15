package com.psoft.match.tcc.service.user;

import com.psoft.match.tcc.dto.ProfessorDTO;
import com.psoft.match.tcc.dto.TCCDTO;
import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.tcc.TCCProposal;
import com.psoft.match.tcc.model.tcc.orientation.OrientationInterest;
import com.psoft.match.tcc.model.tcc.orientation.OrientationProposal;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.model.user.Student;
import com.psoft.match.tcc.model.user.TCCMatchUser;
import com.psoft.match.tcc.repository.user.ProfessorRepository;
import com.psoft.match.tcc.service.study_area.StudyAreaService;
import com.psoft.match.tcc.service.tcc.TCCProposalService;
import com.psoft.match.tcc.service.tcc.TCCService;
import com.psoft.match.tcc.service.tcc.orientation.OrientationInterestService;
import com.psoft.match.tcc.service.tcc.orientation.OrientationProposalService;
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
    private TCCMatchUserService tccMatchUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TCCProposalService tccProposalService;

    @Autowired
    private OrientationProposalService orientationProposalService;

    @Autowired
    private OrientationInterestService orientationInterestService;

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
        TCCMatchUser user = tccMatchUserService.findByEmailOpt(professorDTO.getEmail()).orElse(null);
        if (user != null) throw new UserAlreadyExistsException(professorDTO.getEmail());

        Professor professor = this.buildProfessor(professorDTO);
        tccMatchUserService.saveUser(professor);
        return professorRepository.save(professor);
    }

    @Transactional
    @Override
    public Professor updateProfessor(Long id, ProfessorDTO professorDTO) {
        Professor professor = this.findProfessorById(id);
        this.updateProfessor(professor, professorDTO);

        tccMatchUserService.saveUser(professor);
        return professorRepository.save(professor);
    }

    @Transactional
    @Override
    public void approveOrientationInterest(Long tccId, Long interestId) {
        Professor professor = tccMatchUserService.getLoggedUser();
        TCC tcc = tccService.findTCCById(tccId);
        OrientationInterest orientationInterest = orientationInterestService.findById(interestId);

        if (!professor.getRegisteredTCCs().contains(tcc)) throw new RuntimeException("tcc does not belong to professor");
        if (!professor.getInterestedTCCs().contains(orientationInterest)) throw new RuntimeException("orientation interest is not for this tcc");

        tcc.approveTCC();
        orientationInterestService.deleteOrientationInterest(orientationInterest);
        tccService.saveTCC(tcc);
    }

    @Transactional
    @Override
    public void refuseOrientationInterest(Long tccId, Long interestId) {

    }

    @Transactional
    @Override
    public void deleteProfessor(Long id) {
        Professor professor = this.findProfessorById(id);
        tccMatchUserService.deleteUser(professor);
        professorRepository.delete(professor);
    }

    @Transactional
    @Override
    public void declareOrientationInterest(Long tccProposalId) {
        Professor professor = tccMatchUserService.getLoggedUser();
        TCCProposal tccProposal = tccProposalService.findTCCProposalById(tccProposalId);

        OrientationProposal orientationProposal = new OrientationProposal(professor, tccProposal);
        professor.addOrientationInterest(orientationProposal);

        orientationProposalService.saveTccProposal(orientationProposal);
        professorRepository.save(professor);
    }

    @Transactional
    @Override
    public TCC createTCC(TCCDTO tccdto) {
        Professor professor = tccMatchUserService.getLoggedUser();
        Collection<StudyArea> studyAreas = studyAreaService.findStudyAreasById(tccdto.getStudyAreasIds());

        TCC tcc = new TCC(tccdto.getTitle(), tccdto.getDescription(), professor, studyAreas);
        professor.addTCC(tcc);
        professor.decrementQuota();

        tcc = tccService.saveTCC(tcc);
        professorRepository.save(professor);
        studyAreaService.notifyNewTCCToInterestedStudents(studyAreas, tcc);

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
