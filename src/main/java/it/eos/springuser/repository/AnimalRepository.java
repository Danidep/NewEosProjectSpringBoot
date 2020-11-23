package it.eos.springuser.repository;

import it.eos.springuser.model.AnimalEntity;
import it.eos.springuser.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {

    @Query("FROM #{#entityName} WHERE species = ?1 or genus = ?2")
    List<AnimalEntity> findBySpeciesOrGenus(String species, String genus);

    @Query("FROM #{#entityName} WHERE id < ?1 order by species desc")
    List<AnimalEntity> findByIdOrderBySpeciesDesc(long desc);

    @Query("FROM #{#entityName} WHERE family like %?1%")
    List<AnimalEntity> findByFamilyLike(String family);
}
