package com.psoft.match.tcc.controller;

import com.psoft.match.tcc.dto.OrientationIssueDTO;
import com.psoft.match.tcc.service.StudentService;
import com.psoft.match.tcc.util.Constants;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Student operations")
@RestController
@RequestMapping(Constants.BASE_API_ENDPOINT + "/student")
@CrossOrigin
public class StudentApiController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/tcc/{tccId}/orientation")
    public ResponseEntity<Void> registerTccOrientationInterest(@PathVariable Long tccId) {
        studentService.declareTccOrientationInterest(tccId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/tcc/{tccId}/issue")
    public ResponseEntity<Void> registerTccOrientationIssue(@PathVariable Long tccId, @RequestBody OrientationIssueDTO orientationIssue) {
        studentService.performTccOrientationIssue(tccId, orientationIssue);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
