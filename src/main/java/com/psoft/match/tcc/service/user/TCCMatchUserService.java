package com.psoft.match.tcc.service.user;

import com.psoft.match.tcc.model.user.TCCMatchUser;

import java.util.Optional;

public interface TCCMatchUserService {

    <T extends TCCMatchUser> T getLoggedUser();

    TCCMatchUser saveUser(TCCMatchUser user);

    void deleteUser(TCCMatchUser user);

    TCCMatchUser findByUsername(String username);

    Optional<TCCMatchUser> findByUsernameOpt(String username);

    TCCMatchUser findByEmail(String email);

    Optional<TCCMatchUser> findByEmailOpt(String email);
}
