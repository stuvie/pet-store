package com.fywss.spring.petservices.domain.pet;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
	Optional<Pet> getByName(String name);
}
