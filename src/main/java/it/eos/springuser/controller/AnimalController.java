package it.eos.springuser.controller;

import java.util.List;

import it.eos.springuser.repository.AnimalEntity;
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
    public ResponseEntity<AnimalModel> getAnimalById(@PathVariable("id") long id){
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

    @GetMapping("/animalFind/{species}/{genus}")
    public ResponseEntity<List<AnimalEntity>> findBySpeciesOrGenus(@PathVariable("species") String species, @PathVariable("genus") String genus){
        return ResponseEntity.ok(repository.findBySpeciesOrGenus(species,genus));
    }

    @GetMapping("/animalOrder/{desc}")
    public ResponseEntity<List<AnimalEntity>> findByIdOrderBySpeciesDesc(@PathVariable("desc") long desc){
        return ResponseEntity.ok(repository.findByIdOrderBySpeciesDesc(desc));
    }

    @GetMapping("/animalLike/{family}")
    public ResponseEntity<List<AnimalEntity>> findByFamilyLike(@PathVariable("family") String family){
        return ResponseEntity.ok(repository.findByFamilyLike(family));
    }

    @GetMapping("/changeSpecies/{species}/{id}")
    public ResponseEntity<AnimalModel> changeSpecies(@PathVariable("species")String species, @PathVariable("id")long id){
        return ResponseEntity.ok(repository.changeSpecies(species, id));
    }

    @GetMapping("/changeType/{type}/{id}")
    public ResponseEntity<AnimalModel> changeType(@PathVariable("type")String type, @PathVariable("id")long id){
        return ResponseEntity.ok(repository.changeType(type, id));
    }

    @GetMapping("/deletedSpecies/{species}")
    public ResponseEntity<String> deletedSpecies(@PathVariable("species")String species){
        repository.deletedSpecies(species);
        return ResponseEntity.ok("Delete species");
    }

    @GetMapping("/deletedType/{type}")
    public ResponseEntity<String> deletedType(@PathVariable("type")String type){
        repository.deletedType(type);
        return ResponseEntity.ok("Delete type");
    }
}