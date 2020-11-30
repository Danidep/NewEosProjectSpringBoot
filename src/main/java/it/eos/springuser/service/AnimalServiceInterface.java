package it.eos.springuser.service;

import it.eos.springuser.model.AnimalEntity;
import it.eos.springuser.model.AnimalModel;
import it.eos.springuser.model.UserEntity;

import java.util.List;

public interface AnimalServiceInterface {

    AnimalModel save(AnimalModel animal);

    AnimalModel getAnimalById(Long id);

    List<AnimalEntity> getAllAnimal();

    AnimalModel putAnimal(AnimalModel animal);

    String deleteAnimal(Long id);

    List<AnimalEntity> findBySpeciesOrGenus(String species, String genus);

    List<AnimalEntity> findByIdOrderBySpeciesDesc(long desc);

    List<AnimalEntity> findByFamilyLike(String family);

    AnimalModel changeSpecies(String species, long id);

    void deletedSpecies(String species);
}
