package it.eos.springuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.eos.springuser.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

}
