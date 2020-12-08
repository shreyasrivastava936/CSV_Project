package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.csv.CSVController;

@SpringBootTest
class ControllerTest {
	
	@Autowired
	private CSVController controller;
	
	
	//checks if controller is loading properly
	@Test
	public void contextLoad() throws Exception{
		assertThat(controller).isNotNull();
	}

}
