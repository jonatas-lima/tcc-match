package com.psoft.match.tcc.service;

import java.util.List;

import com.psoft.match.tcc.dto.TCCProposalDTO;
import com.psoft.match.tcc.model.tcc.TCCProposal;

public interface TCCProposalService {

	List<TCCProposal> getAllTCCProposal();
	
	TCCProposal findTCCProposalById(Long id);
	
	TCCProposal createTCCProposal(TCCProposalDTO tccProposal);
	
}
