package it.eos.springuser.service;

import it.eos.springuser.repository.AnimalEntity;
import it.eos.springuser.model.AnimalModel;

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

    AnimalModel changeType(String type, long id);

    void deletedSpecies(String species);

    void deletedType(String type);
}
