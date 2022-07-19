package org.tdsast.joinus.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.tdsast.joinus.model.entity.Candidate;
import org.tdsast.joinus.model.entity.Club;
import org.tdsast.joinus.model.entity.Department;
import org.tdsast.joinus.repository.CandidateRepository;
import org.tdsast.joinus.utils.ExcelHelper;

import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;

    @Inject
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public List<Candidate> getCandidates() {
        return candidateRepository.findAll();
    }

    private Example<Candidate> paramToExample(String name, Club club, Department department) {
        if (name == null) {
            name = "";
        }
        Candidate candidate = new Candidate();
        candidate.setName(name);
        candidate.setClub(club);
        candidate.setChoice1(department);
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name",
            ExampleMatcher.GenericPropertyMatcher::contains);
        if (club != null) {
            matcher = matcher.withMatcher("club", ExampleMatcher.GenericPropertyMatcher::exact);
        }
        if (department != null) {
            matcher = matcher.withMatcher("choice1", ExampleMatcher.GenericPropertyMatcher::exact);
        }
        return Example.of(candidate, matcher);
    }

    public List<Candidate> getCandidates(int current, int size, String name, Club club, Department department) {
        if (current == 0 && size == 0) {
            return candidateRepository.findAll(paramToExample(name, club, department));
        }
        return candidateRepository
            .findAll(paramToExample(name, club, department), PageRequest.of(current - 1, size))
            .getContent();
    }

    public long getCandidatesCount(String name, Club club, Department department) {
        return candidateRepository.count(paramToExample(name, club, department));
    }

    public Candidate newCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public ByteArrayInputStream export(String name, Club club, Department department) {
        List<Candidate> candidates = getCandidates(0, 0, name, club, department);
        return ExcelHelper.candidatesToExcel(candidates);
    }
}
