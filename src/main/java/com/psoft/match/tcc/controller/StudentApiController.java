package com.psoft.match.tcc.controller;

import com.psoft.match.tcc.dto.OrientationIssueDTO;
import com.psoft.match.tcc.dto.TCCProposalDTO;
import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.tcc.TCCProposal;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.service.StudentService;
import com.psoft.match.tcc.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Student operations")
@RestController
@RequestMapping(Constants.BASE_API_ENDPOINT + "/student")
@CrossOrigin
public class StudentApiController {

    @Autowired
    private StudentService studentService;

	@ApiOperation("Registra interesse em ser orientado em um TCC")
    @PostMapping("/tcc/{tccId}/orientation")
    public ResponseEntity<Void> registerTccOrientationInterest(@PathVariable Long tccId) {
        studentService.declareTccOrientationInterest(tccId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

	@ApiOperation("Informa problema na orientação de um TCC")
    @PostMapping("/tcc/issue")
    public ResponseEntity<Void> registerTccOrientationIssue(@RequestBody OrientationIssueDTO orientationIssue) {
        studentService.performTccOrientationIssue(orientationIssue);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

	@ApiOperation(value = "Adiciona uma área de estudo no estudante logado")
	@PutMapping(value = "/study_area/{idStudyArea}")
    public ResponseEntity<StudyArea> addInterestedStudyArea(@PathVariable Long idStudyArea) {
		StudyArea addedStudyArea = studentService.addInterestedStudyArea(idStudyArea);
		return new ResponseEntity<>(addedStudyArea, HttpStatus.OK);
	}

	@ApiOperation(value = "Lista os professores que tem interesse nas áreas do estudante")
	@GetMapping(value = "/professors")
    public ResponseEntity<List<Professor>> listInterestedProfessors() {
		List<Professor> professors = studentService.listInterestedProfessors();
		return new ResponseEntity<>(professors, HttpStatus.OK);
	}

	@ApiOperation(value = "Adiciona uma proposta de tcc no sistema")
	@PostMapping(value = "/tcc_proposal")
    public ResponseEntity<TCCProposal> addTccProposal(@RequestBody TCCProposalDTO tccProposalDTO) {
		TCCProposal tccProposal = studentService.addTccProposal(tccProposalDTO);
    	return new ResponseEntity<TCCProposal>(tccProposal, HttpStatus.CREATED);
    }

	@ApiOperation(value = "Lista os tccs cadastrados no sistema")
	@GetMapping(value = "/tcc")
    public ResponseEntity<List<TCC>> listTccs() {
		List<TCC> tccs = studentService.listTccs();
		return new ResponseEntity<>(tccs, HttpStatus.OK);
	}
}
