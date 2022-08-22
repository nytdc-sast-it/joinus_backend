package com.sastit.joinus.service;

import com.sastit.joinus.model.entity.Club;
import com.sastit.joinus.repository.ClubRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

@Service
public class ClubService {
    private final ClubRepository clubRepository;

    @Inject
    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Cacheable(value = "club", key = "#root.methodName")
    public List<Club> getClubs() {
        return clubRepository.findAll();
    }

    @Cacheable(value = "club", key = "#clubId")
    public Club getClubById(Long clubId) {
        return clubRepository.findById(clubId).orElse(null);
    }

    public boolean isClubExist(String name) {
        return clubRepository.existsByName(name);
    }

    public boolean isClubExist(Long id) {
        return clubRepository.existsById(id);
    }

    @CacheEvict(value = "club", allEntries = true)
    public Club newClub(String name) {
        if (isClubExist(name)) {
            throw new IllegalArgumentException("社团已存在");
        }
        Club club = new Club(name, Set.of(), null, null, null);
        return clubRepository.save(club);
    }

    @CacheEvict(value = "club", allEntries = true)
    public void removeClub(Long clubId) {
        if (!isClubExist(clubId)) {
            throw new IllegalArgumentException("社团不存在");
        }
        clubRepository.deleteById(clubId);
    }

    @CacheEvict(value = "club", allEntries = true)
    public Club saveClub(Club club) {
        return clubRepository.save(club);
    }
}
