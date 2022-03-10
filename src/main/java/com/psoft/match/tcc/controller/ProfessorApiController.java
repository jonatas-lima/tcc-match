package com.psoft.match.tcc.controller;

import com.psoft.match.tcc.dto.TCCDTO;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.service.ProfessorService;
import com.psoft.match.tcc.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Professor operations")
@RestController
@RequestMapping(Constants.BASE_API_ENDPOINT + "/professor")
@CrossOrigin
public class ProfessorApiController {

    @Autowired
    private ProfessorService professorService;

    @ApiOperation("Registra interesse em uma proposta de TCC")
    @PostMapping("/tcc_proposal/{tccProposalId}/orientation")
    public ResponseEntity<Void> declareOrientationInterest(@PathVariable Long tccProposalId) {
        professorService.declareOrientationInterest(tccProposalId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation("Adiciona um novo TCC ao sistema")
    @PostMapping("/tcc")
    public ResponseEntity<TCC> createTCC(@RequestBody TCCDTO tccDTO) {
        TCC tcc = professorService.createTCC(tccDTO);
        return new ResponseEntity<>(tcc, HttpStatus.CREATED);
    }
}
