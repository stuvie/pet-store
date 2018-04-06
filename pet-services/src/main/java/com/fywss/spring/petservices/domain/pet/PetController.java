package com.fywss.spring.petservices.domain.pet;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@ControllerAdvice
@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:5000"})
@RequestMapping ("/apis/v1/pets")
public class PetController {
	
	@Autowired
	private PetService service;
	
	@GetMapping(value = "")
	@ApiOperation (value = "Get all Pets", notes = "Returns a List of Pet records", response = Pet.class, responseContainer = "List")
	public List<Pet> findAll() {
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Gets one Pet based on id", notes = "Returns a single Pet record", response = Pet.class)
	public Optional<Pet> findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@GetMapping(params = {"name"})
	@ApiOperation (value = "Gets one Pet based on name", notes = "Returns a single Pet record", response = Pet.class)
    public Optional<Pet> findWithName(@RequestParam(value="name") String name) {
    	return service.findByName(name);
    }
    
	@PostMapping
	@ApiOperation (value = "Create a new Pet", notes = "Creates and returns a single Pet record", response = Pet.class)
	public Pet create(@Valid @RequestBody Pet obj, BindingResult bindingResult) {
		return service.create(obj);
	}
	
	@PutMapping (value = "/{id}")
	@ApiOperation (value = "Update an existing Pet", notes = "Updates and returns the modified Pet record", response = Pet.class)
	public Pet update(@Valid @RequestBody Pet obj, @PathVariable Long id) {
		return service.update(obj, id);
	}
	
	@DeleteMapping (value = "/{id}")
	@ApiOperation (value = "Delete one Pet based on id", notes = "Returns the deleted Pet record", response = Pet.class)
	public Optional<Pet> delete(@PathVariable Long id) {
		return service.delete(id);
	}

}
