package com.psoft.match.tcc.service.tcc.orientation;

import com.psoft.match.tcc.model.tcc.OrientationIssue;
import com.psoft.match.tcc.repository.tcc.OrientationIssueRepository;
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
