package org.tdsast.joinus.service;

import org.springframework.stereotype.Service;
import org.tdsast.joinus.model.entity.Club;
import org.tdsast.joinus.repository.ClubRepository;

import javax.inject.Inject;
import java.util.List;

@Service
public class ClubService {
    private final ClubRepository clubRepository;

    @Inject
    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public List<Club> getClubs() {
        return clubRepository.findAll();
    }

    public Club getClubById(Long clubId) {
        return clubRepository.findById(clubId).orElse(null);
    }
}
