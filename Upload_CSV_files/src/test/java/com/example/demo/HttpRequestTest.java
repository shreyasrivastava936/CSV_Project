package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Answer1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.csv.CSVController;
import com.example.csv.CSVService;
import com.example.model.Tutorial;
import com.example.repository.ProfessorsRepository;
import com.example.repository.TutorialRepository;

@WebMvcTest(CSVController.class)
public class HttpRequestTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	CSVService service;
	
	@MockBean
	TutorialRepository tut_repository;

	@MockBean
	ProfessorsRepository prof_repository;
	
	@Test
	void testSave() throws Exception {
		Tutorial value = new Tutorial(2, "Hello", "Shreysa", true);
		
		doNothing().when(service).saveData(value);
		
		String url = "/api/csv/add/Tutorial/";
		

		String content = "{\"id\" : 1,"
				+ "\"title\": \"tit\","
				+ "\"description\": \"desptnnn\","
				+ "\"published\": true}";
				

		mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isOk());
	}
	
	@Test
	void testGetAllData() {
		
		String class_name = "Tutorial";
		
		Answer<?> answer = null;
		Mockito.when(service.getAllData(class_name)).then(answer);
		
	}
	
	

}
