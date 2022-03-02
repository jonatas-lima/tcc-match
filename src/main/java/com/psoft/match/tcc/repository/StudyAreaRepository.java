package com.psoft.match.tcc.repository;

import com.psoft.match.tcc.model.StudyArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyAreaRepository extends JpaRepository<StudyArea, Long> {
}