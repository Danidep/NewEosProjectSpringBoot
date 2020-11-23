package it.eos.springuser.service;


import it.eos.springuser.business.AnimalConverter;
import it.eos.springuser.exeption.ResourceNotFoundException;
import it.eos.springuser.model.AnimalEntity;
import it.eos.springuser.model.AnimalModel;
import it.eos.springuser.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService implements AnimalServiceInterface{

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public AnimalModel save(AnimalModel animal) {
        AnimalEntity saveAnimal = AnimalConverter.toEntity(animal);
        animalRepository.save(saveAnimal);
        return AnimalConverter.toModel(saveAnimal);
    }

    @Override
    public List<AnimalEntity> getAllAnimal() {
        return this.animalRepository.findAll();
    }

    @Override
    public AnimalModel putAnimal(AnimalModel animal) {
        Optional<AnimalEntity> animalDB= this.animalRepository.findById(animal.getId());

        if (animalDB.isPresent()) {
            AnimalEntity putAnimal=animalDB.get();
            putAnimal.setType(animal.getType());
            putAnimal.setFamily(animal.getFamily());
            putAnimal.setGenus(animal.getGenus());
            putAnimal.setSpecies(animal.getSpecies());
            animalRepository.save(putAnimal);
            return AnimalConverter.toModel(putAnimal);
        }else {
            throw new ResourceNotFoundException("Animal not found");
        }
    }

    @Override
    public String deleteAnimal(Long id) {
        Optional<AnimalEntity> animalDB = this.animalRepository.findById(id);

        if(animalDB.isPresent()) {
            this.animalRepository.delete(animalDB.get());
            return "Deleted";
        }else {
            return "Error";
        }
    }

    @Override
    public AnimalModel getAnimalById(Long id) {
        Optional<AnimalEntity> animalDB = this.animalRepository.findById(id);

        if(animalDB.isPresent()) {
            return AnimalConverter.toModel(animalDB.get());
        }else {
            throw new ResourceNotFoundException("Animal not found");
        }
    }

    @Override
    public List<AnimalEntity> findBySpeciesOrGenus(String species, String genus) {
        return this.animalRepository.findBySpeciesOrGenus(species, genus);
    }

    @Override
    public List<AnimalEntity> findByIdOrderBySpeciesDesc(long desc) {
        return this.animalRepository.findByIdOrderBySpeciesDesc(desc);
    }

    @Override
    public List<AnimalEntity> findByFamilyLike(String family) {
        return this.animalRepository.findByFamilyLike(family);
    }
}
