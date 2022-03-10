package com.psoft.match.tcc.service;

import com.psoft.match.tcc.model.tcc.TCC;

import java.util.List;

public interface TCCService {

    TCC findTCCById(Long id);

    List<TCC> findAvailableTCCs();

    TCC saveTCC(TCC tcc);
}
