package it.eos.springuser.service;

import it.eos.springuser.model.AnimalEntity;
import it.eos.springuser.model.AnimalModel;

import java.util.List;

public interface AnimalServiceInterface {

    AnimalModel save(AnimalModel animal);

    AnimalModel getAnimalById(Long id);

    List<AnimalEntity> getAllAnimal();

    AnimalModel putAnimal(AnimalModel animal);

    String deleteAnimal(Long id);

}
