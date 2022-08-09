package com.sastit.joinus.repository;

import com.sastit.joinus.model.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
    boolean existsByName(String name);
}
