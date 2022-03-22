package com.psoft.match.tcc.service.user;

import com.psoft.match.tcc.dto.OrientationIssueDTO;
import com.psoft.match.tcc.model.tcc.OrientationIssue;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.user.TCCMatchUser;

import java.util.Optional;

public interface TCCMatchUserService {

    <T extends TCCMatchUser> T getLoggedUser();

    TCCMatchUser saveUser(TCCMatchUser user);

    void deleteUser(TCCMatchUser user);

    void registerOrientationIssue(TCCMatchUser user, TCC tcc, OrientationIssueDTO orientationIssueDTO);

    TCCMatchUser findByUsername(String username);

    Optional<TCCMatchUser> findByUsernameOpt(String username);

    TCCMatchUser findByEmail(String email);

    Optional<TCCMatchUser> findByEmailOpt(String email);
}
