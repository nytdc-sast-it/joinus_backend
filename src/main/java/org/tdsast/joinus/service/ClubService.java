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

    public boolean isClubExist(String name) {
        return clubRepository.existsByName(name);
    }

    public boolean isClubExist(Long id) {
        return clubRepository.existsById(id);
    }

    public Club newClub(String name) {
        if (isClubExist(name)) {
            throw new IllegalArgumentException("社团已存在");
        }
        Club club = new Club(name, null, null, null);
        return clubRepository.save(club);
    }

    public void removeClub(Long clubId) {
        if (!isClubExist(clubId)) {
            throw new IllegalArgumentException("社团不存在");
        }
        clubRepository.deleteById(clubId);
    }
}
