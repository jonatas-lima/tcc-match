package com.psoft.match.tcc.service.tcc;

import com.psoft.match.tcc.model.tcc.TCC;

import java.util.List;

public interface TCCService {

    List<TCC> getAllTccs();

    TCC findTCCById(Long id);

    List<TCC> findAvailableTCCs();

    TCC saveTCC(TCC tcc);

}
