package com.chris.dndspelltracker.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chris.dndspelltracker.models.DNDChar;
import com.chris.dndspelltracker.repositories.DNDCharRepository;

@Service
public class DNDCharService {
	
	@Autowired
	private DNDCharRepository dndCharRepo;

	public DNDChar findById(Long id) {
    	return dndCharRepo.findById(id).get();
    }
	
	public DNDChar saveChar(DNDChar dndChar) {
		return dndCharRepo.save(dndChar);
	}
	
	public DNDChar updateChar(DNDChar dndChar) {
		return dndCharRepo.save(dndChar);
	}
	
	public void deleteDNDChar(DNDChar dndChar) {
		dndCharRepo.delete(dndChar);
	}
	
	public DNDChar findCharByName(String name) {
		return dndCharRepo.findByLastName(name).get();
	}
}
