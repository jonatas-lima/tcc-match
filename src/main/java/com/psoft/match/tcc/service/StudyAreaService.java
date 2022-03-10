package com.psoft.match.tcc.service;

import com.psoft.match.tcc.dto.StudyAreaDTO;
import com.psoft.match.tcc.model.StudyArea;

import java.util.Collection;
import java.util.List;

public interface StudyAreaService {

    StudyArea createStudyArea(StudyAreaDTO studyAreaDTO);

    StudyArea updateStudyArea(Long id, StudyAreaDTO studyAreaDTO);

    StudyArea saveStudyArea(StudyArea studyArea);

    List<StudyArea> findStudyAreasById(Collection<Long> studyAreasIds);

    void deleteStudyArea(Long id);
    
    StudyArea findStudyAreaById(Long id);
}
