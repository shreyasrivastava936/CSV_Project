package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Tutorial;
import com.example.repository.TutorialRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class UnitTestExample {
	
	@Autowired
	private TutorialRepository tut_repo;
	
//	@Test
//	public void uploadFileTest() throws Exception{
//	    //given
//	    InputStream uploadStream = UnitTestExample.class.getClassLoader().getResourceAsStream("exceldocument.xlsx");
//	    MultipartFile file = new MultipartFile("file", uploadStream);
//	    assert uploadStream != null;
//
//	    //when
//	    this.mockMvc.perform(fileUpload("/DefectImport")
//	            .file(file))
//	    //then
//	            .andExpect(status().isOk());
//	}
	
	@Test
	@Rollback(false)
	public void TestCreateTutorial() {
		Tutorial tutorial = new Tutorial(2, "Title_Test", "Descript_ion", true);
		Tutorial savedTutorial = tut_repo.save(tutorial);
		
		assertNotNull(savedTutorial);
	}

	@Test 
	public void findTutorialbyId()
	{
		long id = 2;
		
		Object data = tut_repo.findById(id);
	}
	
	@Test
	void test() {
		assertEquals("Hello", "actual");
		fail("Not yet implemented");
	}

}
