package com.psoft.match.tcc.util.exception.tcc;

import javax.persistence.EntityNotFoundException;

public class TCCProposalNotFoundException extends EntityNotFoundException {
	
	private static final String TCCPROPOSAL_NOT_FOUND_MSG = "TCCProposal with id %d not found.";

    public TCCProposalNotFoundException(Long id) {
        super(String.format(TCCPROPOSAL_NOT_FOUND_MSG, id));
    }
}
