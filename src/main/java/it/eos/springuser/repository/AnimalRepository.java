package it.eos.springuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {

    List<AnimalEntity> findBySpeciesOrGenus(String species, String genus);

    List<AnimalEntity> findByIdOrderBySpeciesDesc(long desc);

    List<AnimalEntity> findByFamilyLike(String family);

    @Modifying
    @Query("UPDATE AnimalEntity SET species=?1 WHERE id=?2")
    void changeSpecies(String species, long id);

    @Modifying
    @Query("UPDATE AnimalEntity SET type=?1 WHERE id=?2")
    void changeType(String type, long id);

    @Modifying
    @Query("DELETE FROM AnimalEntity WHERE species=?1")
    void deletedSpecies(String species);

    @Modifying
    @Query("DELETE FROM AnimalEntity WHERE type=?1")
    void deletedType(String type);
}
