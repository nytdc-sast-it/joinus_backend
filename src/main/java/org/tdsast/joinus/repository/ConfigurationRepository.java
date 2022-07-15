package org.tdsast.joinus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tdsast.joinus.model.entity.Configuration;

public interface ConfigurationRepository extends JpaRepository<Configuration, Integer> {
}
