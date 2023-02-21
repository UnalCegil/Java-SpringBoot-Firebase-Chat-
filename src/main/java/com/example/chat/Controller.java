package com.example.chat;

import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	public FirebaseService crudService;
	
	public Controller(FirebaseService crudService) {
		this.crudService=crudService;
	}
	
	@PostMapping("/create")
	public String createCRUD(@RequestBody Models crud) throws InterruptedException,ExecutionException{
		return crudService.createCRUD(crud);
	}
	
	@GetMapping("/get")
	public Models getCRUD(@RequestParam String documentId) throws InterruptedException,ExecutionException{
		return crudService.getCRUD(documentId);
	}
	
	@GetMapping("/update")
	public String updateCRUD(@RequestBody Models crud) throws InterruptedException,ExecutionException{
		return crudService.updateCRUD(crud);
	}
	
	@GetMapping("/delete")
	public String deleteCRUD(@RequestBody String documentId) throws InterruptedException,ExecutionException{
		return crudService.deleteCRUD(documentId);
	}
	
}
