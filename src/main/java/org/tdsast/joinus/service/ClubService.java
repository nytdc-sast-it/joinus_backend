package org.tdsast.joinus.service;

import org.springframework.stereotype.Service;
import org.tdsast.joinus.repository.ClubRepository;

@Service
public class ClubService {
    private final ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }
}
