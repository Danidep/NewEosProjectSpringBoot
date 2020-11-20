package it.eos.springuser.service;

import java.util.List;

import it.eos.springuser.model.UserEntity;
import it.eos.springuser.model.UserModel;

public interface UserServiceInterface {
	
	UserModel save(UserModel user);
	
	UserModel getUserById(Long id);
	
	List<UserEntity> getAllUser();
	
	UserModel putUser(UserModel user);
	
	String deleteUser(Long id);
}
