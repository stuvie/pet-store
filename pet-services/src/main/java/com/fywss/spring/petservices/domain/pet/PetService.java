package com.fywss.spring.petservices.domain.pet;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fywss.spring.petservices.exception.ConflictException;
import com.fywss.spring.petservices.exception.NotFoundException;

@Service
public class PetService {
	
	private static final Logger logger = LoggerFactory.getLogger(PetService.class);
	private static final String ID_NOT_FOUND = "no user with id ";
	
	@Autowired
	private PetRepository repository;
	
	public List<Pet> findAll() {
		List<Pet> records = repository.findAll();
		logger.info("PetService list: {}", records);
		return records;
	}
	
	public Optional<Pet> findById(Long id) {
		Optional<Pet> existing = repository.findById(id);

		if (!existing.isPresent()) {
			throw new NotFoundException(ID_NOT_FOUND + id);
		}
		return existing;
	}
	
	public Pet create(Pet obj) {
		if (nameExists(obj.getName())) {
    		throw new ConflictException("name already taken, " + obj.getName());
    	}
		Pet created = repository.save(obj);
    	logger.info("PetService created: {}", created);
    	return created;
    }
	
	public Pet update(Pet obj, Long id) {
		Optional<Pet> existing = findById(id);
		
		if (!existing.isPresent()) {
			throw new NotFoundException(ID_NOT_FOUND + id);
		}
		Pet record = existing.get();
		record.setName(obj.getName());
		record.setPhotoUrl(obj.getPhotoUrl());
		
		logger.info("PetService updated: {}", record);
		return repository.saveAndFlush(record);
	}

	public Optional<Pet> delete(Long id) {
		Optional<Pet> existing = repository.findById(id);
		if (!existing.isPresent()) {
			throw new NotFoundException(ID_NOT_FOUND + id);
		}
		repository.deleteById(id);
		return existing;
	}
	
    public Optional<Pet> findByName(String name) {
    	Optional<Pet> existing = repository.getByName(name);
    	if (!existing.isPresent()) {
			throw new NotFoundException("PetService found no record with name " + name);
		}
		return existing;
    }
    
    private boolean nameExists(String name) {
    	Optional<Pet> existing = repository.getByName(name);
        return existing.isPresent();
    }
}
