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

	@GetMapping("/userFind/{name}/{mail}")
	public ResponseEntity<List<UserEntity>> findByNameOrMail(@PathVariable("name") String name,@PathVariable("mail") String mail){
		return ResponseEntity.ok(repository.findByNameOrMail(name,mail));
	}

	@GetMapping("/userEnding/{end}")
	public ResponseEntity<List<UserEntity>> findByMailEndingWith(@PathVariable("end") String end){
		return ResponseEntity.ok(repository.findByMailEndingWith(end));
	}

	@GetMapping("/userContain/{contain}")
	public ResponseEntity<List<UserEntity>> findByMailContaining(@PathVariable("contain") String contain){
		return ResponseEntity.ok(repository.findByMailContaining(contain));
	}

	@GetMapping("/userLess/{id}")
	public ResponseEntity<List<UserEntity>> findByIdLessThan(@PathVariable("id") long id){
		return ResponseEntity.ok(repository.findByIdLessThan(id));
	}

	@GetMapping("/userEqual/{id}")
	public ResponseEntity<List<UserEntity>> findByIdLessThanEqual(@PathVariable("id") long id){
		return ResponseEntity.ok(repository.findByIdLessThanEqual(id));
	}
}
	


