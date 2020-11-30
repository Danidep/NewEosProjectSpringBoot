package it.eos.springuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.eos.springuser.model.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

    List<UserEntity> findByNameOrMail(String name, String mail);

    List<UserEntity> findByMailEndingWith(String end);

    List<UserEntity> findByMailContaining(String contain);

    List<UserEntity> findByIdLessThan(Long id);

    List<UserEntity> findByIdLessThanEqual(Long id);

    @Query(value = "SELECT id FROM USER WHERE mail like %?1%",nativeQuery = true)
    List<Long> findIdByMail(String contain);

    @Query(value = "SELECT id FROM USER WHERE name=?1",nativeQuery = true)
    List<Long> findIdByName(String name);

    @Modifying
    @Query("UPDATE UserEntity SET active=?1 WHERE id=?2")
    void changeActive(boolean active, long id);

    @Modifying
    @Query("UPDATE UserEntity SET name=?1 WHERE id=?2")
    void changeName(String name, long id);

    @Modifying
    @Query("DELETE FROM UserEntity WHERE active=FALSE")
    void deletedActiveFalse();
}
