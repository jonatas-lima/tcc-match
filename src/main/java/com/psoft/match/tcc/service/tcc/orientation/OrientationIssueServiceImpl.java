package com.psoft.match.tcc.service.tcc.orientation;

import com.psoft.match.tcc.dto.OrientationIssueDTO;
import com.psoft.match.tcc.model.tcc.OrientationIssue;
import com.psoft.match.tcc.model.user.TCCMatchUser;
import com.psoft.match.tcc.repository.tcc.OrientationIssueRepository;
import com.psoft.match.tcc.service.user.TCCMatchUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrientationIssueServiceImpl implements OrientationIssueService {

    @Autowired
    private OrientationIssueRepository orientationIssueRepository;

    @Autowired
    private TCCMatchUserService tccMatchUserService;

    @Transactional
    @Override
    public void saveOrientationIssue(OrientationIssue orientationIssue) {
        orientationIssueRepository.save(orientationIssue);
    }

    @Transactional
    @Override
    public void registerOrientationIssue(TCCMatchUser user, OrientationIssueDTO orientationIssueDTO) {


        tccMatchUserService.saveUser(user);
    }
}
