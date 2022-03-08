package com.psoft.match.tcc.service.impl;

import com.psoft.match.tcc.dto.StudyAreaDTO;
import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.model.user.User;
import com.psoft.match.tcc.repository.StudyAreaRepository;
import com.psoft.match.tcc.service.StudyAreaService;
import com.psoft.match.tcc.util.exception.studyarea.StudyAreaAlreadyExistsException;
import com.psoft.match.tcc.util.exception.studyarea.StudyAreaNotFoundException;
import com.psoft.match.tcc.util.exception.user.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;

public class StudyAreaServiceImpl implements StudyAreaService {

    @Autowired
    private StudyAreaRepository studyAreaRepository;

    public StudyArea createStudyArea(StudyAreaDTO studyAreaDTO){
        StudyArea studyArea = findStudyAreaByDescription(studyAreaDTO.getDescription());

        StudyArea newStudyArea = this.buildStudyArea(studyAreaDTO);
        return studyAreaRepository.save(newStudyArea);
    }

    public StudyArea updateStudyArea(Long id, StudyAreaDTO studyAreaDTO){
        StudyArea studyArea = this.findStudyAreaById(id);
        this.updateStudyArea(studyArea, studyAreaDTO);

        return studyAreaRepository.save(studyArea);
    }

   public void deleteStudyArea(Long id){
       StudyArea studyArea = this.findStudyAreaById(id);
       studyAreaRepository.delete(studyArea);
   }

    public StudyArea buildStudyArea(StudyAreaDTO studyAreaDTO){
        return new StudyArea(studyAreaDTO.getDescription());
    }

    public StudyArea findStudyAreaByDescription(String description) {
        return studyAreaRepository.findByDescription(description).orElseThrow(() -> new StudyAreaAlreadyExistsException(description));
    }

    public StudyArea findStudyAreaById(Long id) {
        return studyAreaRepository.findById(id).orElseThrow(() -> new StudyAreaNotFoundException(id));
    }

    public void updateStudyArea(StudyArea oldStudyArea, StudyAreaDTO newStudyArea){
        oldStudyArea.setDescription(newStudyArea.getDescription());
        oldStudyArea.setInterestedStudents(newStudyArea.getInterestedStudents());
        oldStudyArea.setInterestedProfessors(newStudyArea.getInterestedProfessors());
    }
}
