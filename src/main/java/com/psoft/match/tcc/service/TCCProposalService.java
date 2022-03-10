package com.psoft.match.tcc.service;

import java.util.List;

import com.psoft.match.tcc.dto.TCCProposalDTO;
import com.psoft.match.tcc.model.tcc.TCCProposal;
import com.psoft.match.tcc.model.user.Student;

public interface TCCProposalService {

	List<TCCProposal> getAllTCCProposal();
	
	TCCProposal findTCCProposalById(Long id);
	
	TCCProposal createTCCProposal(TCCProposalDTO tccProposal, Student student);
	
}
