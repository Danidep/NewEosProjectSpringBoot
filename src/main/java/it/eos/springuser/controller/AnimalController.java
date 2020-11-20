package it.eos.springuser.controller;

import java.util.List;

import it.eos.springuser.model.AnimalEntity;
import it.eos.springuser.model.AnimalModel;
import it.eos.springuser.service.AnimalService;
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

@RestController
@RequestMapping("/api")
public class AnimalController {

    @Autowired
    AnimalService repository;

    @PostMapping("/animal")
    public ResponseEntity<AnimalModel> createAnimal(@RequestBody AnimalModel animal){
        return ResponseEntity.ok(repository.save(animal));
    }

    @GetMapping("/animal/{id}")
    public ResponseEntity<AnimalModel> getUserById(@PathVariable("id") long id){
        return ResponseEntity.ok(repository.getAnimalById(id));
    }

    @GetMapping("/animal")
    public ResponseEntity<List<AnimalEntity>> getAllAnimal(){
        return ResponseEntity.ok(repository.getAllAnimal());
    }

    @DeleteMapping("/animal/{id}")
    public ResponseEntity<String> deleteAnimal(@PathVariable("id") long id){
        return ResponseEntity.ok(repository.deleteAnimal(id));
    }

    @PutMapping("/animal")
    public ResponseEntity<AnimalModel> putAnimal(@RequestBody AnimalModel animal){
        return ResponseEntity.ok(repository.putAnimal(animal));
    }
}