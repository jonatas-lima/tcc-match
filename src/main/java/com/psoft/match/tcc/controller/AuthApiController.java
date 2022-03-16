package com.psoft.match.tcc.controller;

import com.psoft.match.tcc.dto.CredentialsDTO;
import com.psoft.match.tcc.model.user.TCCMatchUser;
import com.psoft.match.tcc.service.auth.AuthService;
import com.psoft.match.tcc.util.Constants;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Api(tags = "Authentication api")
@RestController
@RequestMapping(Constants.BASE_API_ENDPOINT + "/auth")
@CrossOrigin
public class AuthApiController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody CredentialsDTO credentialsDTO) {
        String token = authService.login(credentialsDTO);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<TCCMatchUser> getLoggedUser() {
        TCCMatchUser loggedUser = authService.getLoggedUser();
        return new ResponseEntity<>(loggedUser, HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<Collection<String>> getEmails() {
        Collection<String> emails = authService.getLoggedUser().getEmails();
        return new ResponseEntity<>(emails, HttpStatus.OK);
    }
}
