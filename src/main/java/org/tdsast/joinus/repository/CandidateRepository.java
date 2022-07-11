package org.tdsast.joinus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tdsast.joinus.model.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

}
