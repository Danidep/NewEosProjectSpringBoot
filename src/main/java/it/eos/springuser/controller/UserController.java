package it.eos.springuser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.eos.springuser.model.UserEntity;
import it.eos.springuser.model.UserModel;
import it.eos.springuser.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserService repository;
	
	@PostMapping("/user")
	public ResponseEntity<UserModel> createUser(@RequestBody UserModel user){
			return ResponseEntity.ok(repository.save(user));
	}
	
	@GetMapping("/user/{id}") 
	public ResponseEntity<UserModel> getUserById(@PathVariable("id") long id){
			return ResponseEntity.ok(repository.getUserById(id));
	}
	
	@GetMapping("/user") 
	public ResponseEntity<List<UserEntity>> getAllUser(){
			return ResponseEntity.ok(repository.getAllUser());
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
			return ResponseEntity.ok(repository.deleteUser(id));
	}
	
	@PutMapping("/user")
	public ResponseEntity<UserModel> putuser(@RequestBody UserModel user){
			return ResponseEntity.ok(repository.putUser(user));
	}
}
	


