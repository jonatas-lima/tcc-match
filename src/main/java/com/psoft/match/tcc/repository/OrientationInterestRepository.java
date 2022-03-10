package com.psoft.match.tcc.repository;

import com.psoft.match.tcc.model.tcc.OrientationInterest;
import com.psoft.match.tcc.model.tcc.TCC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrientationInterestRepository extends JpaRepository<OrientationInterest, Long> {

    List<OrientationInterest> findByInterestedTCC(TCC tcc);
}
