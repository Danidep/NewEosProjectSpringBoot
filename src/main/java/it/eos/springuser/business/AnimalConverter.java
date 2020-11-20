package it.eos.springuser.business;

import it.eos.springuser.model.AnimalEntity;
import it.eos.springuser.model.AnimalModel;

public class AnimalConverter {

    private AnimalConverter() {
    }
    public static AnimalModel toModel(AnimalEntity animal) {
        AnimalModel animalModel = new AnimalModel();
        animalModel.setId(animal.getId());
        animalModel.setType(animal.getType());
        animalModel.setFamily(animal.getFamily());
        animalModel.setGenus(animal.getGenus());
        animalModel.setSpecies(animal.getSpecies());
        return animalModel ;
    }

    public static AnimalEntity toEntity(AnimalModel animal) {
        AnimalEntity animalEntity = new AnimalEntity();
        animalEntity.setId(animal.getId());
        animalEntity.setType(animal.getType());
        animalEntity.setFamily(animal.getFamily());
        animalEntity.setGenus(animal.getGenus());
        animalEntity.setSpecies(animal.getSpecies());
        return animalEntity;
    }
}
