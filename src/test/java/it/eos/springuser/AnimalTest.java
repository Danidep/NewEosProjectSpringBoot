package it.eos.springuser;

import it.eos.springuser.business.AnimalConverter;
import it.eos.springuser.model.AnimalEntity;
import it.eos.springuser.model.AnimalModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnimalTest {

    @Test
    void animalConvertModelEntity() {
        AnimalModel animal = new AnimalModel();
        animal.setType("Type");
        animal.setFamily("Family");
        animal.setGenus("Genus");
        animal.setSpecies("Species");

        AnimalEntity entity = AnimalConverter.toEntity(animal);
        AnimalModel convertedFromEntity = AnimalConverter.toModel(entity);

        Assertions.assertEquals(convertedFromEntity, animal);
    }
}
