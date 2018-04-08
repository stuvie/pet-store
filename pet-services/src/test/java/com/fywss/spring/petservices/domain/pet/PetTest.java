package com.fywss.spring.petservices.domain.pet;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class PetTest {

	private static ValidatorFactory validatorFactory;
	private static Validator validator;

	@BeforeClass
	public static void createValidator() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@AfterClass
	public static void close() {
		validatorFactory.close();
	}

	@Test
	public void createShouldHaveNoViolations() {
		Pet created = new Pet("Spot", "sold", "dog", "https://i.imgur.com/gM37Bi4b.jpg");
		Set<ConstraintViolation<Pet>> violations = validator.validate(created);
		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void createShouldDetectInvalidName() {
		Pet created = new Pet("I", "sold", "dog", "https://i.imgur.com/gM37Bi4b.jpg");
		Set<ConstraintViolation<Pet>> violations = validator.validate(created);
		assertEquals(1, violations.size());

		ConstraintViolation<Pet> violation = violations.iterator().next();
		assertEquals("name must be between 2 and 25 characters long", violation.getMessage());
		assertEquals("name", violation.getPropertyPath().toString());
		assertEquals("I", violation.getInvalidValue());
	}
	
	@Test
	public void createShouldDetectInvalidPhotoUrl() {
		Pet created = new Pet("Spot", "sold", "dog", "o");
		Set<ConstraintViolation<Pet>> violations = validator.validate(created);
		assertEquals(1, violations.size());

		ConstraintViolation<Pet> violation = violations.iterator().next();
		assertEquals("photoUrl must be between 2 and 60 characters long", violation.getMessage());
		assertEquals("photoUrl", violation.getPropertyPath().toString());
		assertEquals("o", violation.getInvalidValue());
	}

}
