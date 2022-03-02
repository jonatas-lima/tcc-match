package com.psoft.match.tcc.repository.tcc;

import com.psoft.match.tcc.model.tcc.TCC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TCCRepository extends JpaRepository<TCC, Long> {
}
