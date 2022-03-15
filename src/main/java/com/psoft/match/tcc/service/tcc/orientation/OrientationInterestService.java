package com.psoft.match.tcc.service.tcc.orientation;

import com.psoft.match.tcc.model.tcc.orientation.OrientationInterest;
import com.psoft.match.tcc.model.tcc.TCC;

import java.util.List;

public interface OrientationInterestService {

    void saveOrientationInterest(OrientationInterest orientationInterest);

    void deleteOrientationInterest(OrientationInterest orientationInterest);

    OrientationInterest findById(Long id);
}
