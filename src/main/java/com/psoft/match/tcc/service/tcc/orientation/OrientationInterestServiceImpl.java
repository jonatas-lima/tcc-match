package com.psoft.match.tcc.service.tcc.orientation;

import com.psoft.match.tcc.model.tcc.orientation.OrientationInterest;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.repository.tcc.orientation.OrientationInterestRepository;
import com.psoft.match.tcc.service.tcc.orientation.OrientationInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrientationInterestServiceImpl implements OrientationInterestService {

    @Autowired
    private OrientationInterestRepository orientationInterestRepository;

    @Transactional
    @Override
    public void saveOrientationInterest(OrientationInterest orientationInterest) {
        orientationInterestRepository.save(orientationInterest);
    }

    @Override
    public List<OrientationInterest> findOrientationInterestsByTCC(TCC tcc) {
        return orientationInterestRepository.findByInterestedTCC(tcc);
    }
}
