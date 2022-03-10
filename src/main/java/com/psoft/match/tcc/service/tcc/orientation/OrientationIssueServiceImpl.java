package com.psoft.match.tcc.service.tcc.orientation;

import com.psoft.match.tcc.model.tcc.orientation.OrientationIssue;
import com.psoft.match.tcc.repository.tcc.orientation.OrientationIssueRepository;
import com.psoft.match.tcc.service.tcc.orientation.OrientationIssueService;
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
