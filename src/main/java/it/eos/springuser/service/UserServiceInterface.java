package it.eos.springuser.service;

import java.util.List;

import it.eos.springuser.repository.UserEntity;
import it.eos.springuser.model.UserModel;

public interface UserServiceInterface {
	
	UserModel save(UserModel user);
	
	UserModel getUserById(Long id);
	
	List<UserEntity> getAllUser();
	
	UserModel putUser(UserModel user);
	
	String deleteUser(Long id);

	List<UserEntity> findByNameOrMail(String name,String mail);

	List<UserEntity> findByMailEndingWith(String end);

	List<UserEntity> findByMailContaining(String contain);

	List<UserEntity> findByIdLessThan(Long id);

	List<UserEntity> findByIdLessThanEqual(Long id);

	List<Long> findIdByMail(String contain);

	List<Long> findIdByName(String name);

	UserModel changeActive(boolean active, long id);

	UserModel changeName(String name, long id);

	void deletedActiveFalse();
}
