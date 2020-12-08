package com.example.csv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Professors;
import com.example.model.Tutorial;
import com.example.response.ResponseMessage;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/api/csv")
public class CSVController {

	
  @Autowired
  CSVService fileService;

  @RequestMapping("/upload/{table}")
  public ResponseEntity<ResponseMessage> uploadFile(
		  @RequestParam("file") MultipartFile file, @PathVariable("table") String table) {
	  
    String message = "";

    if (CSVHelper.hasCSVFormat(file)) {
      try {
        fileService.save(file, table);

        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
      } catch (Exception e) {
        message = "Could not upload the file: " + file.getOriginalFilename() + "!"+ e;
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
      }
    }

    message = "Please upload a csv file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
  }

  @GetMapping("/get/{table}")
  public <T> ResponseEntity<Page<T>> getAllTutorials(@PathVariable("table") String table) {
    try {
       Page<T> tutorials = fileService.getAllData(table);

      if (tutorials.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(tutorials, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null,  HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
 
  
  @GetMapping("/get/{table}/{id}")
  public  ResponseEntity<Object> get(@PathVariable("id") Long id, @PathVariable("table") String table_name) {
	  try {
		  System.out.println(table_name);
	      Object tutorials = fileService.getAllData(id, table_name);

	      return new  ResponseEntity<>(tutorials, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }    
  }
  
  @PostMapping("/add/Tutorial")
  public ResponseEntity<ResponseMessage> add(@RequestBody Tutorial product) {
	  String message = "";
	  try {
	      fileService.saveData(product);
	        message = "Uploaded the data successfully";
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	  }
	  catch (Exception e) {
		  message = "Could not upload data " + "!"+ e;
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }  
  }
  
  @PostMapping("/add/Professors")
  public void add(@RequestBody Professors product) {
	  try {
	      fileService.saveData(product);
	  }
	  catch (Exception e) {
	      return;
	    }  
  }
  
  @DeleteMapping("/delete/{table}/{id}")
  public ResponseEntity<Object> delete(@PathVariable("id") Long id, @PathVariable("table") String table) {
	  try {
		  Object tutorials = fileService.getAllData(id, table);
	      
	      if (tutorials == null) {
	          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }
	      
	      fileService.delete(id, table);
	      
	      return new ResponseEntity<>(tutorials, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }  
      
  }

 
}