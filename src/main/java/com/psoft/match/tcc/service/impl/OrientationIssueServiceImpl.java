package com.psoft.match.tcc.service.impl;

import com.psoft.match.tcc.model.tcc.OrientationIssue;
import com.psoft.match.tcc.repository.OrientationIssueRepository;
import com.psoft.match.tcc.service.OrientationIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrientationIssueServiceImpl implements OrientationIssueService {

    @Autowired
    private OrientationIssueRepository orientationIssueRepository;

    @Transactional
    @Override
    public void saveOrientationIssue(OrientationIssue orientationIssue) {
        orientationIssueRepository.save(orientationIssue);
    }
}
