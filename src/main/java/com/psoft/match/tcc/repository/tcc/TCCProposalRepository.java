package com.psoft.match.tcc.repository.tcc;

import com.psoft.match.tcc.model.tcc.TCCProposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TCCProposalRepository extends JpaRepository<TCCProposal, Long> {
}
