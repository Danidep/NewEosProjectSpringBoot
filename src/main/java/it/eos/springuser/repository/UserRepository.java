package it.eos.springuser.repository;

import it.eos.springuser.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;

import it.eos.springuser.model.UserEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

    @Query("FROM #{#entityName} WHERE name = ?1 or mail = ?2")
    List<UserEntity> findByNameOrMail(String name, String mail);

    @Query("FROM #{#entityName} WHERE mail like %?1%")
    List<UserEntity> findByMailEndingWith(String end);

    @Query("FROM #{#entityName} WHERE mail like %?1%")
    List<UserEntity> findByMailContaining(String end);

    @Query("FROM #{#entityName} WHERE id < ?1")
    List<UserEntity> findByIdLessThan(Long id);

    @Query("FROM #{#entityName} WHERE id <= ?1")
    List<UserEntity> findByIdLessThanEqual(Long id);
}
