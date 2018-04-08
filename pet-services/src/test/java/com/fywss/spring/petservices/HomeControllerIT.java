package com.fywss.spring.petservices;

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

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { PetServicesApplication.class })
@WebAppConfiguration
@SpringBootTest
public class HomeControllerIT {
	
	private static final String CONTENT_TYPE = "text/html;charset=UTF-8";
	
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testHomePageResponse() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/home"))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();

		Assert.assertEquals(CONTENT_TYPE, mvcResult.getResponse().getContentType());
	}
	
	@Test
	public void testActuatorPageResponse() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/actuatorlinks"))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();

		Assert.assertEquals(CONTENT_TYPE, mvcResult.getResponse().getContentType());
	}
	
	@Test
	public void test404PageResponse() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/unknownPage"))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
	}
}