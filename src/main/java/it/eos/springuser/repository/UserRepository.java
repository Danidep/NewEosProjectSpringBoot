package it.eos.springuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.eos.springuser.model.UserEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

    List<UserEntity> findByNameOrMail(String name, String mail);

    List<UserEntity> findByMailEndingWith(String end);

    @Query("FROM #{#entityName} WHERE mail like %?1%")
    List<UserEntity> findByMailContaining(String contain);

    @Query("FROM #{#entityName} WHERE id < ?1")
    List<UserEntity> findByIdLessThan(Long id);

    @Query("FROM #{#entityName} WHERE id <= ?1")
    List<UserEntity> findByIdLessThanEqual(Long id);

    @Query(value = "SELECT id FROM USER WHERE mail like %?1%",nativeQuery = true)
    List<Long> findIdByMail(String contain);

    @Query(value = "SELECT id FROM USER WHERE name=?1",nativeQuery = true)
    List<Long> findIdByName(String name);
}
