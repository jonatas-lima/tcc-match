package com.psoft.match.tcc.service;

import com.psoft.match.tcc.model.tcc.OrientationInterest;
import com.psoft.match.tcc.model.tcc.TCC;

import java.util.List;

public interface OrientationInterestService {

    void saveOrientationInterest(OrientationInterest orientationInterest);

    List<OrientationInterest> findOrientationInterestsByTCC(TCC tcc);
}
