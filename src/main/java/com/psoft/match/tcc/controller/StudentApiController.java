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
import com.psoft.match.tcc.model.tcc.TCCProposal;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.service.StudentService;
import com.psoft.match.tcc.util.Constants;

@RestController
@RequestMapping(Constants.BASE_API_ENDPOINT + "/student")
@CrossOrigin
public class StudentApiController {

	@Autowired
	private StudentService studentService;
	
	@PutMapping(value = "/update/{idStudyArea}")
    public ResponseEntity<StudyArea> addStudyAreaOnStudent(@PathVariable Long idStudyArea) {
		StudyArea addedStudyArea = studentService.addStudyAreaOnStudent(idStudyArea);
		return new ResponseEntity<>(addedStudyArea, HttpStatus.OK);
	}
	
	@GetMapping(value = "/list/professors}")
    public ResponseEntity<List<Professor>> listProfessorsInterested() {
		List<Professor> professors = studentService.listProfessorsInterested();
		return new ResponseEntity<>(professors, HttpStatus.OK);
	}
	
	@PostMapping(value = "/creat/tccproposal}")
    public ResponseEntity<TCCProposal> addTccProposal(@RequestBody TCCProposalDTO tccProposalDTO) {
		TCCProposal tccProposal = studentService.addTccProposal(tccProposalDTO);
    	return new ResponseEntity<TCCProposal>(tccProposal, HttpStatus.OK);
    }
}