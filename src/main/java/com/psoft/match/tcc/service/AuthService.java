package com.psoft.match.tcc.service;

import com.psoft.match.tcc.dto.CredentialsDTO;

public interface AuthService {

    String login(CredentialsDTO credentialsDTO);

}
