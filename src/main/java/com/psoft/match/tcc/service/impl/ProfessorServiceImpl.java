package com.psoft.match.tcc.service.impl;

import com.psoft.match.tcc.dto.ProfessorDTO;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.model.user.User;
import com.psoft.match.tcc.repository.user.ProfessorRepository;
import com.psoft.match.tcc.repository.user.UserRepository;
import com.psoft.match.tcc.service.ProfessorService;
import com.psoft.match.tcc.util.exception.user.UserAlreadyExistsException;
import com.psoft.match.tcc.util.exception.professor.ProfessorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
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

    private Professor buildProfessor(ProfessorDTO professorDTO) {
        String encryptedPassword = passwordEncoder.encode(professorDTO.getPassword());
        return new Professor(professorDTO.getFullName(), professorDTO.getEmail(), professorDTO.getUsername(), encryptedPassword);
    }

    private void updateProfessor(Professor oldProfessor, ProfessorDTO newProfessor) {
        oldProfessor.setEmail(newProfessor.getEmail());
        oldProfessor.setUsername(newProfessor.getUsername());
        oldProfessor.setFullName(newProfessor.getFullName());
    }

}
