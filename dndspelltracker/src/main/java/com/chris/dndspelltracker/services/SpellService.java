package com.chris.dndspelltracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chris.dndspelltracker.models.Spell;
import com.chris.dndspelltracker.repositories.SpellRepository;


@Service
public class SpellService {
	
	@Autowired
	private SpellRepository spellRepo;
	
	public Spell saveSpell(Spell spell) {
		return spellRepo.save(spell);
	}
	
	public Spell updateSpell(Spell spell) {
		return spellRepo.save(spell);
	}
	
	public List<Spell> findAllSpells(){
		return (List<Spell>) spellRepo.findAll();
	}
	
	public Optional<Spell> findSpellById(Long id) {
		return spellRepo.findById(id);
	}
	
	public Optional<Spell> findSpellByName(String name) {
		return spellRepo.findByName(name);
	}
}
