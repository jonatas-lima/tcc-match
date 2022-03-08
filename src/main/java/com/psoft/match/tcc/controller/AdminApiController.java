package com.psoft.match.tcc.controller;

import com.psoft.match.tcc.dto.ProfessorDTO;
import com.psoft.match.tcc.dto.StudyAreaDTO;
import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.service.ProfessorService;
import com.psoft.match.tcc.service.StudyAreaService;
import com.psoft.match.tcc.util.Constants;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.BASE_API_ENDPOINT + "/admin")
@CrossOrigin
public class AdminApiController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private StudyAreaService studyAreaService;

    @PostMapping(value = "/create/professor")
    @ApiOperation(value = "Criação de um novo professor")
    public ResponseEntity<Professor> createProfessor(@RequestBody ProfessorDTO professorDTO) {
        Professor createdProfessor = professorService.createProfessor(professorDTO);
        return new ResponseEntity<>(createdProfessor, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/professor/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable Long id, @RequestBody ProfessorDTO professorDTO) {
        Professor updatedProfessor = professorService.updateProfessor(id, professorDTO);
        return new ResponseEntity<>(updatedProfessor, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/professor/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        professorService.deleteProfessor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/create/study-area")
    @ApiOperation(value = "Criação de uma nova área de estudo")
    public ResponseEntity<StudyArea> createStudyArea(@RequestBody StudyAreaDTO studyAreaDTO) {
        StudyArea createdStudyArea = studyAreaService.createStudyArea(studyAreaDTO);
        return new ResponseEntity<>(createdStudyArea, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/study-area/{id}")
    public ResponseEntity<StudyArea> updateStudyArea(@PathVariable Long id, @RequestBody StudyAreaDTO studyAreaDTO) {
        StudyArea updatedStudyArea = studyAreaService.updateStudyArea(id, studyAreaDTO);
        return new ResponseEntity<>(updatedStudyArea, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/study-area/{id}")
    public ResponseEntity<Void> deleteStudyArea(@PathVariable Long id) {
        studyAreaService.deleteStudyArea(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
