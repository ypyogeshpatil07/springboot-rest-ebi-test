package com.ebi.springboot;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ebi.springboot.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SpringbootEbiCodeTestApplication.class)
@AutoConfigureMockMvc
public class SpringbootEbiCodeIntegrationTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	WebApplicationContext webApplicationContext;

	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	@Test
	public void givenNoAuthz_whenGetPersons_thenStatus401() throws Exception {
		mvc.perform(get("/person/person-all")).andExpect(status().isUnauthorized());
	}

	@Test
	public void givenUserNoPrivileges_whenGetPersons_thenStatus401() throws Exception {
		mvc.perform(get("/person/person-all").with(httpBasic("user4", "123"))).andExpect(status().isUnauthorized());
	}

	@Test
	public void givenReadUser_whenGetPersons_thenStatus200() throws Exception {
		mvc.perform(get("/person/person-all").with(httpBasic("user2", "password"))).andExpect(status().isOk())
				.andDo(print()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}

	@Test
	public void givenReadWriteUser_whenGetPersons_thenStatus200() throws Exception {
		mvc.perform(get("/person/person-all").with(httpBasic("manager", "password"))).andExpect(status().isOk())
				.andDo(print()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}

	@Test
	public void givenNoAuthz_whenDeletePersons_thenStatus401() throws Exception {
		mvc.perform(delete("/person/delete-person/0")).andExpect(status().isUnauthorized());
	}
	
	@Test
    public void givenNoAuthz_whenPutPerson_thenStatus401() throws Exception {
        mvc.perform(put("/person/update-person/0"))
                .andExpect(status().isUnauthorized());
    }
	
	

	@Test
	public void createProduct_Success_Status_200() throws Exception {
		setUp();
		String uri = "/person/store-person";
		Person person = new Person();
		person.setId(1);
		person.setFirstName("yogesh");
		person.setLastName("patil");
		person.setAge("23");
		person.setFavouriteColour("red");
		String inputJson = mapToJson(person);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}

	@Test
	public void deleteProduct_Success_Status_200() throws Exception {
		setUp();
		String uri = "/person/delete-person/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	


}
