package com.psoft.match.tcc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.repository.tcc.TCCRepository;
import com.psoft.match.tcc.service.TCCService;

@Service
public class TCCServiceImpl implements TCCService {

	@Autowired
	TCCRepository tccRepository;
	
	@Override
	public List<TCC> getAllTccs() {
		return tccRepository.findAll();
	}
}
