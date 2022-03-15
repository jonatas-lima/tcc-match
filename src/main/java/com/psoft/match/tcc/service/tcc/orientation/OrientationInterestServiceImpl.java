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

    @Transactional
    @Override
    public void deleteOrientationInterest(OrientationInterest orientationInterest) {
        orientationInterestRepository.delete(orientationInterest);
    }

    @Override
    public OrientationInterest findById(Long id) {
        return orientationInterestRepository.findById(id).orElseThrow(() -> new RuntimeException("interest not found!"));
    }

}
