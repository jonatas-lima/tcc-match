package com.psoft.match.tcc.service;

import com.psoft.match.tcc.dto.CredentialsDTO;
import com.psoft.match.tcc.model.user.User;

public interface AuthService {

    String login(CredentialsDTO credentialsDTO);

    <T extends User> T getLoggedUser();
}
