package org.tdsast.joinus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tdsast.joinus.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
