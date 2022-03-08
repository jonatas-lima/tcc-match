package com.psoft.match.tcc.controller;

import com.psoft.match.tcc.dto.ProfessorDTO;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.service.ProfessorService;
import com.psoft.match.tcc.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Admin operations")
@RestController
@RequestMapping(Constants.BASE_API_ENDPOINT + "/admin")
@CrossOrigin
public class AdminApiController {

    @Autowired
    private ProfessorService professorService;

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
}
