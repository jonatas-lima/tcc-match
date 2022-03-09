package com.psoft.match.tcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psoft.match.tcc.model.StudyArea;
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
}