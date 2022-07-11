package org.tdsast.joinus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tdsast.joinus.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
