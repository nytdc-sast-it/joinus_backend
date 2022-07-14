package org.tdsast.joinus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tdsast.joinus.model.entity.Club;

public interface ClubRepository extends JpaRepository<Club, Long> {
    boolean existsByName(String name);
}
