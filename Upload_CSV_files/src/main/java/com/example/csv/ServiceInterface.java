package com.example.csv;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Professors;
import com.example.model.Tutorial;

public interface ServiceInterface {

	public void save(MultipartFile file, String table);
	public <T> Page<T> getAllData(String class_name);
	public Object getAllData(long id, String class_name);
	public void saveData(Tutorial t);
	public void saveData(Professors p);
	public void delete(Long id, String class_name);
}
