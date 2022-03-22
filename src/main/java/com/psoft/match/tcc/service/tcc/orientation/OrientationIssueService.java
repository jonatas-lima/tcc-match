package com.psoft.match.tcc.service.tcc.orientation;

import com.psoft.match.tcc.dto.OrientationIssueDTO;
import com.psoft.match.tcc.model.tcc.OrientationIssue;
import com.psoft.match.tcc.model.user.TCCMatchUser;
import com.psoft.match.tcc.model.user.UserRole;

import java.util.Collection;

public interface OrientationIssueService {

    void saveOrientationIssue(OrientationIssue orientationIssue);

    void registerOrientationIssue(TCCMatchUser user, OrientationIssueDTO orientationIssueDTO);

    Collection<OrientationIssue> getOrientationIssues(String term, UserRole userRole);
}
