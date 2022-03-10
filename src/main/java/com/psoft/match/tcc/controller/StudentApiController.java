package com.psoft.match.tcc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psoft.match.tcc.dto.TCCProposalDTO;
import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.tcc.TCCProposal;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.service.StudentService;
import com.psoft.match.tcc.util.Constants;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(Constants.BASE_API_ENDPOINT + "/student")
@CrossOrigin
public class StudentApiController {

	@Autowired
	private StudentService studentService;
	
	@PutMapping(value = "/study_area/{idStudyArea}")
	@ApiOperation(value = "Adiciona uma área de estudo no estudante logado")
    public ResponseEntity<StudyArea> addInterestedStudyArea(@PathVariable Long idStudyArea) {
		StudyArea addedStudyArea = studentService.addInterestedStudyArea(idStudyArea);
		return new ResponseEntity<>(addedStudyArea, HttpStatus.OK);
	}
	
	@GetMapping(value = "/professors")
	@ApiOperation(value = "Lista os professores que tem interesse nas áreas do estudante")
    public ResponseEntity<List<Professor>> listInterestedProfessors() {
		List<Professor> professors = studentService.listInterestedProfessors();
		return new ResponseEntity<>(professors, HttpStatus.OK);
	}
	
	@PostMapping(value = "/tcc_proposal")
	@ApiOperation(value = "Adiciona uma proposta de tcc no sistema")
    public ResponseEntity<TCCProposal> addTccProposal(@RequestBody TCCProposalDTO tccProposalDTO) {
		TCCProposal tccProposal = studentService.addTccProposal(tccProposalDTO);
    	return new ResponseEntity<TCCProposal>(tccProposal, HttpStatus.CREATED);
    }
	
	@GetMapping(value = "/tcc}")
	@ApiOperation(value = "Lista os tccs cadastrados no sistema")
    public ResponseEntity<List<TCC>> listTccs() {
		List<TCC> tccs = studentService.listTccs();
		return new ResponseEntity<List<TCC>>(tccs, HttpStatus.OK);
	}
}