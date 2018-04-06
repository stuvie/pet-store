package com.fywss.spring.petservices.domain.pet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Pet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "name cannot be null")
	@Column(unique = true)
	@Size(min = 2, max = 25, message = "name must be between {min} and {max} characters long")
	private String name;
	
	@NotNull(message = "status cannot be null")
	@Size(min = 2, max = 20, message = "status must be between {min} and {max} characters long")
	private String status;

	@NotNull(message = "category cannot be null")
	@Size(min = 2, max = 20, message = "category must be between {min} and {max} characters long")
	private String category;
	
	@NotNull(message = "photoUrl cannot be null")
	@Size(min = 2, max = 60, message = "photoUrl must be between {min} and {max} characters long")
	private String photoUrl;

	@Override
	public String toString() {
		return "Pet [id=" + id + ", name=" + name + ", status=" + status + ", category=" + category + ", photoUrl="
				+ photoUrl + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
}
