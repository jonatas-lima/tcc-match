package com.psoft.match.tcc.controller;

import com.psoft.match.tcc.dto.ProfessorDTO;
import com.psoft.match.tcc.dto.StudyAreaDTO;
import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.tcc.TCCStatus;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.service.user.AdminService;
import com.psoft.match.tcc.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Admin operations")
@RestController
@RequestMapping(Constants.BASE_API_ENDPOINT + "/admin")
@CrossOrigin
public class AdminApiController {

    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/professor")
    @ApiOperation(value = "Criação de um novo professor")
    public ResponseEntity<Professor> createProfessor(@RequestBody ProfessorDTO professorDTO) {
        Professor createdProfessor = adminService.createProfessor(professorDTO);
        return new ResponseEntity<>(createdProfessor, HttpStatus.CREATED);
    }

    @PutMapping(value = "/professor/{id}")
    @ApiOperation(value = "Atualiza as informações de um professor")
    public ResponseEntity<Professor> updateProfessor(@PathVariable Long id, @RequestBody ProfessorDTO professorDTO) {
        Professor updatedProfessor = adminService.updateProfessor(id, professorDTO);
        return new ResponseEntity<>(updatedProfessor, HttpStatus.OK);
    }

    @DeleteMapping(value = "/professor/{id}")
    @ApiOperation(value = "Deleta um professor")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        adminService.deleteProfessor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/study-area")
    @ApiOperation(value = "Criação de uma nova área de estudo")
    public ResponseEntity<StudyArea> createStudyArea(@RequestBody StudyAreaDTO studyAreaDTO) {
        StudyArea createdStudyArea = adminService.createStudyArea(studyAreaDTO);
        return new ResponseEntity<>(createdStudyArea, HttpStatus.CREATED);
    }

    @PutMapping(value = "/study-area/{id}")
    @ApiOperation(value = "Atualização da descrição de uma área de estudo")
    public ResponseEntity<StudyArea> updateStudyArea(@PathVariable Long id, @RequestBody StudyAreaDTO studyAreaDTO) {
        StudyArea updatedStudyArea = adminService.updateStudyArea(id, studyAreaDTO);
        return new ResponseEntity<>(updatedStudyArea, HttpStatus.OK);
    }

    @DeleteMapping(value = "/study-area/{id}")
    @ApiOperation(value = "Deleção de uma área de estudo")
    public ResponseEntity<Void> deleteStudyArea(@PathVariable Long id) {
        adminService.deleteStudyArea(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/tcc/")
    @ApiOperation(value = "Consulta de TCCs (em andamento ou finalizados) de um período")
    public ResponseEntity<List<TCC>> getTCCs(@RequestParam String status, @RequestParam String term) {
        List<TCC> tccs = adminService.getTCCs(status, term);
        return new ResponseEntity<>(tccs, HttpStatus.OK);
    }
}
