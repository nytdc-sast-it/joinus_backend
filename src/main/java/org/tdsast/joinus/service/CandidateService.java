package org.tdsast.joinus.service;

import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
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

    private Example<Candidate> paramToExample(String name) {
        if (name == null) {
            name = "";
        }
        Candidate candidate = new Candidate();
        candidate.setName(name);
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name",
                ExampleMatcher.GenericPropertyMatcher::contains);
        return Example.of(candidate, matcher);
    }

    public List<Candidate> getCandidates(int current, int size, String name) {
        if (current == 0 && size == 0) {
            return candidateRepository.findAll(paramToExample(name));
        }
        return candidateRepository.findAll(paramToExample(name), PageRequest.of(current - 1, size))
                .getContent();
    }

    public long getCandidatesCount(String name) {
        return candidateRepository.count(paramToExample(name));
    }

    public Candidate newCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }
}
