package it.eos.springuser.business;

import it.eos.springuser.repository.UserEntity;
import it.eos.springuser.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
	
	public UserModel toModel(UserEntity user) {
		UserModel userModel = new UserModel();
		userModel.setId(user.getId());
		userModel.setMail(user.getMail());
		userModel.setPassword(user.getPassword());
		userModel.setName(user.getName());
		userModel.setActive(user.isActive());
		return userModel;
	}
	
	public UserEntity toEntity(UserModel user) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(user.getId());
		userEntity.setMail(user.getMail());
		userEntity.setPassword(user.getPassword());
		userEntity.setName(user.getName());
		userEntity.setActive(userEntity.isActive());
		return userEntity;
	}
}
