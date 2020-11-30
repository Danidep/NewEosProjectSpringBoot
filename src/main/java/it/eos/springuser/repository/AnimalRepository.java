package it.eos.springuser.repository;

import it.eos.springuser.model.AnimalEntity;
import it.eos.springuser.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {

    List<AnimalEntity> findBySpeciesOrGenus(String species, String genus);

    List<AnimalEntity> findByIdOrderBySpeciesDesc(long desc);

    List<AnimalEntity> findByFamilyLike(String family);
}
