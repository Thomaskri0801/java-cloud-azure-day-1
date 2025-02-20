package com.booleanuk.simpleapi.repository;

import com.booleanuk.simpleapi.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanetRepository extends JpaRepository<Planet, Integer> {
}
