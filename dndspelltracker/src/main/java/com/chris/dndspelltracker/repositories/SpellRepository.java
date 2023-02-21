package com.chris.dndspelltracker.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chris.dndspelltracker.models.Spell;

@Repository
public interface SpellRepository extends CrudRepository<Spell, Long>{
	
	Optional<Spell> findByName(String name);
}
