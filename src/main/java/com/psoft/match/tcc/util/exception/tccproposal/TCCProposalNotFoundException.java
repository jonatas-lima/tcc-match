package com.psoft.match.tcc.util.exception.tccproposal;

public class TCCProposalNotFoundException extends RuntimeException {

	
	private static final String TCCPROPOSAL_NOT_FOUND_MSG = "TCCProposal with id %d not found.";

    public TCCProposalNotFoundException(Long id) {
        super(String.format(TCCPROPOSAL_NOT_FOUND_MSG, id));
    }
}
