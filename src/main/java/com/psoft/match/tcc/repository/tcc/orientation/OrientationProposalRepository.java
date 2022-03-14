package com.psoft.match.tcc.repository.tcc.orientation;

import com.psoft.match.tcc.model.tcc.orientation.OrientationProposal;
import com.psoft.match.tcc.model.tcc.TCCProposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrientationProposalRepository extends JpaRepository<OrientationProposal, Long> {
}
