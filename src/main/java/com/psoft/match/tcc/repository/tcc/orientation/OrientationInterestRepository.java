package com.psoft.match.tcc.repository.tcc.orientation;

import com.psoft.match.tcc.model.tcc.orientation.OrientationInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrientationInterestRepository extends JpaRepository<OrientationInterest, Long> {

}
