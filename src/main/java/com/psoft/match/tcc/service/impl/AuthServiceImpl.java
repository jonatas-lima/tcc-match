package com.psoft.match.tcc.service.impl;

import com.psoft.match.tcc.dto.CredentialsDTO;
import com.psoft.match.tcc.security.JWTUtil;
import com.psoft.match.tcc.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(CredentialsDTO credentialsDTO) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(credentialsDTO.getUsername(), credentialsDTO.getPassword(), new ArrayList<>());
        authenticationManager.authenticate(authToken);
        String token = jwtUtil.generateToken(credentialsDTO.getUsername());
        return token;
    }

}
