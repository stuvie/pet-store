package com.fywss.spring.petservices.domain.pet;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fywss.spring.petservices.PetServicesApplication;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { PetServicesApplication.class })
@WebAppConfiguration
@SpringBootTest
public class PetControllerIT {
	
	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
	private static final String API_PATH = "/apis/v1/pets";

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testFindAllResponse() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(API_PATH))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(Matchers.greaterThanOrEqualTo(2))))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Ziggy"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Dexter"))
				.andReturn();

		Assert.assertEquals(CONTENT_TYPE, mvcResult.getResponse().getContentType());
	}
	
	@Test
	public void testFindByIdResponse() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(API_PATH + "/2"))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Dexter"))
				.andReturn();

		Assert.assertEquals(CONTENT_TYPE, mvcResult.getResponse().getContentType());
	}
	
	@Test
	public void testFindByIdNotFoundError() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get(API_PATH + "/20"))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	public void testFindByNameResponse() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(API_PATH + "?name=Piper"))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Piper"))
				.andReturn();

		Assert.assertEquals(CONTENT_TYPE, mvcResult.getResponse().getContentType());
	}
	
	@Test
	public void testFindByNameNotFoundError() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get(API_PATH + "?name=EltonJohn"))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	public void testCreateValidResponse() throws Exception {
		Pet created = new Pet("Spotty", "sold", "dog", "https://i.imgur.com/gM37Bi4b.jpg");
		this.mockMvc.perform(MockMvcRequestBuilders.post(API_PATH).contentType(CONTENT_TYPE)
				.content(asJsonString(created)))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(CONTENT_TYPE))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Spotty"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.category").value("dog"));
	}
	
	@Test
	public void testCreateDuplicateResponse() throws Exception {
		Pet created = new Pet("Piper", "sold", "dog", "https://i.imgur.com/gM37Bi4b.jpg");
		this.mockMvc.perform(MockMvcRequestBuilders.post(API_PATH).contentType(CONTENT_TYPE)
				.content(asJsonString(created)))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is(HttpStatus.CONFLICT.value()));
	}
	
	@Test
	public void testUpdateResponse() throws Exception {
		Pet updated = new Pet("Bear2", "sold", "racoon", "https://i.imgur.com/gM37Bi4b.jpg");
		this.mockMvc.perform(MockMvcRequestBuilders.put(API_PATH + "/9").contentType(CONTENT_TYPE)
				.content(asJsonString(updated)))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(CONTENT_TYPE))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Bear2"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("sold"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.category").value("racoon"));
	}
	
	@Test
	public void testUpdateNotFoundError() throws Exception {
		Pet updated = new Pet("Bear2", "sold", "racoon", "https://i.imgur.com/gM37Bi4b.jpg");
		this.mockMvc.perform(MockMvcRequestBuilders.put(API_PATH + "/20").contentType(CONTENT_TYPE)
				.content(asJsonString(updated)))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	public void testDeleteResponse() throws Exception {
		Pet deleted = new Pet("Chico", "sold", "racoon", "https://i.imgur.com/gM37Bi4b.jpg");
		this.mockMvc.perform(MockMvcRequestBuilders.delete(API_PATH + "/10").contentType(CONTENT_TYPE)
				.content(asJsonString(deleted)))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(CONTENT_TYPE))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Chico"));
	}
	
	@Test
	public void testDeleteNotFoundError() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.delete(API_PATH + "/20"))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
	}
	
	private String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        // System.out.println(jsonContent);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
