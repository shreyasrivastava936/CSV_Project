package com.example.csv;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Professors;
import com.example.model.Tutorial;
import com.example.repository.ProfessorsRepository;
import com.example.repository.TutorialRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


@Service
public class CSVService implements ServiceInterface{
	@Autowired
	TutorialRepository tut_repository;

	@Autowired
	ProfessorsRepository prof_repository;
	
	

	public void save(MultipartFile file, String table) {
		try {
			List item_list = CSVHelper.csvToTutorial(file.getInputStream(), table);
			switch(table) {
			case "Tutorial":
				tut_repository.saveAll(item_list);
				break;
			case "Professors":
				prof_repository.saveAll(item_list);
				break;
			default:
				throw new RuntimeException("content not supported");
			}

		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}

	public <T> Page<T> getAllData(String class_name) {

		Pageable pageRequest = createPageRequest();
		return  (Page<T>) findRepo(class_name).findAll(pageRequest);
	}

	private Pageable createPageRequest() {
		return PageRequest.of(0, 4);
	}

	public  Object getAllData(long id, String class_name) {
		return  findRepo(class_name).findById(id).get();
	}

	public void saveData(Tutorial t)
	{
		tut_repository.save(t);
	}

	public void saveData(Professors p)
	{
		prof_repository.save(p);
	}

	public void delete(Long id, String class_name)
	{
		findRepo(class_name).deleteById(id);
	}

	private JpaRepository<?, Long> findRepo(String class_name) {
		if(class_name.equals("Tutorial"))
			return (JpaRepository<?, Long>) tut_repository;
		return (JpaRepository<?, Long>) prof_repository;
	}
}
