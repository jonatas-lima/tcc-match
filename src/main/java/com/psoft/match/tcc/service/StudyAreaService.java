package com.psoft.match.tcc.service;

import com.psoft.match.tcc.dto.StudyAreaDTO;
import com.psoft.match.tcc.model.StudyArea;

public interface StudyAreaService {

    StudyArea createStudyArea(StudyAreaDTO studyAreaDTO);

    StudyArea updateStudyArea(Long id, StudyAreaDTO studyAreaDTO);

    void deleteStudyArea(Long id);
}
