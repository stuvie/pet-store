package com.fywss.spring.petservices;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fywss.spring.petservices.domain.pet.Pet;
import com.fywss.spring.petservices.domain.pet.PetRepository;

@Controller
public class HomeController {
	@Value("${info.message}")
	private String message;
	
	@Autowired
    private PetRepository repository;
	
	@RequestMapping("/home")
	public String loadHome(Model model) {
		List<Pet> records = repository.findAll();
		model.addAttribute("pets", records);
		model.addAttribute("message", message);
		return "home";
	}
	
	@RequestMapping("/actuatorlinks")
	public String loadActuatorLinks(Model model) {
		model.addAttribute("message", message);
		return "actuator";
	}
}
