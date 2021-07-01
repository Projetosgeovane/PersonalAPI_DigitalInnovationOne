package com.digitalinnovation.personalapi.repository;

import com.digitalinnovation.personalapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
