package org.tdsast.joinus.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.tdsast.joinus.model.entity.Candidate;
import org.tdsast.joinus.repository.CandidateRepository;

@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public List<Candidate> getCandidates() {
        return candidateRepository.findAll();
    }

    public Candidate newCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }
}
