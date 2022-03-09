package com.psoft.match.tcc.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psoft.match.tcc.dto.TCCProposalDTO;
import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCCProposal;
import com.psoft.match.tcc.repository.tcc.TCCProposalRepository;
import com.psoft.match.tcc.service.StudyAreaService;
import com.psoft.match.tcc.service.TCCProposalService;
import com.psoft.match.tcc.util.exception.tccproposal.TCCProposalNotFoundException;

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
	public TCCProposal createTCCProposal(TCCProposalDTO tccProposal) {
		List<StudyArea> areas = getAreasOfString(tccProposal.getIdsAreas());
		TCCProposal tcc = new TCCProposal(tccProposal.getTittle(), tccProposal.getDescription());
		for(StudyArea s : areas) {
			tcc.addStudyArea(s);
		}
		tccProposalRepository.save(tcc);
        return tcc;
	}
	
	private List<StudyArea> getAreasOfString(Collection<Long> idsAreas) {
		List<StudyArea> studyAreas = new ArrayList<StudyArea>();
		for(Long l : idsAreas) {
			studyAreas.add(studyAreaService.findStudyAreaById(l));
		}
		return studyAreas;
	}
}
