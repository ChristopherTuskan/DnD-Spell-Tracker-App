package com.chris.dndspelltracker.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chris.dndspelltracker.models.DNDChar;

@Repository
public interface DNDCharRepository extends CrudRepository<DNDChar, Long>{

	Optional<DNDChar> findByLastName(String name);
}
