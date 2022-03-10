package com.psoft.match.tcc.service.impl;

import com.psoft.match.tcc.dto.TCCProposalDTO;
import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCCProposal;
import com.psoft.match.tcc.model.user.Student;
import com.psoft.match.tcc.repository.tcc.TCCProposalRepository;
import com.psoft.match.tcc.service.StudyAreaService;
import com.psoft.match.tcc.service.TCCProposalService;
import com.psoft.match.tcc.util.exception.tccproposal.TCCProposalNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TCCProposalServiceImpl implements TCCProposalService{

	@Autowired
	TCCProposalRepository tccProposalRepository;
	
	@Autowired
	StudyAreaService studyAreaService;
	
	@Override
	public List<TCCProposal> getAllTCCProposal() {
		return tccProposalRepository.findAll();
	}

	@Override
	public TCCProposal findTCCProposalById(Long id) {
		return tccProposalRepository.findById(id).orElseThrow(() -> new TCCProposalNotFoundException(id));
	}

	@Transactional
	@Override
	public TCCProposal createTCCProposal(TCCProposalDTO tccProposal, Student student) {
		List<StudyArea> areas = studyAreaService.findStudyAreasById(tccProposal.getIdsAreas());
		TCCProposal tcc = new TCCProposal(student, tccProposal.getTittle(), tccProposal.getDescription(), areas);

		return tccProposalRepository.save(tcc);
	}

}
