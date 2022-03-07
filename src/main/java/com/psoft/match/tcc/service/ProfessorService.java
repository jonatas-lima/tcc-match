package com.psoft.match.tcc.service;

import com.psoft.match.tcc.dto.ProfessorDTO;
import com.psoft.match.tcc.model.user.Professor;

import java.util.List;

public interface ProfessorService {

    List<Professor> getAllProfessors();

    Professor findProfessorById(Long id);

    Professor createProfessor(ProfessorDTO professorDTO);

    Professor updateProfessor(Long id, ProfessorDTO professorDTO);

    void deleteProfessor(Long id);
}
