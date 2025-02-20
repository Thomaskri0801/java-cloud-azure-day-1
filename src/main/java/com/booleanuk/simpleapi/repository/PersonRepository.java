package com.booleanuk.simpleapi.repository;

import com.booleanuk.simpleapi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
