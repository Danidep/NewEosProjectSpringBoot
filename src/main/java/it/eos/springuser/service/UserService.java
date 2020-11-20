package it.eos.springuser.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.eos.springuser.business.UserConverter;
import it.eos.springuser.exeption.ResourceNotFoundException;
import it.eos.springuser.model.UserEntity;
import it.eos.springuser.model.UserModel;
import it.eos.springuser.repository.UserRepository;


@Service
public class UserService implements UserServiceInterface {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserModel save(UserModel user) {
		UserEntity saveUser = UserConverter.toEntity(user);
		userRepository.save(saveUser);
		return UserConverter.toModel(saveUser);
	}

	@Override
	public List<UserEntity> getAllUser() {
		return this.userRepository.findAll();
	}

	@Override
	public UserModel putUser(UserModel user) {
		Optional<UserEntity> userDB= this.userRepository.findById(user.getId());

		if (userDB.isPresent()) {
			UserEntity putUser=userDB.get();
			putUser.setMail(user.getMail());
			putUser.setPassword(user.getPassword());
			putUser.setName(user.getName());
			userRepository.save(putUser);
			return UserConverter.toModel(putUser);
		}else {
			throw new ResourceNotFoundException("User not found");
		}
	}

	@Override
	public String deleteUser(Long id) {
		Optional<UserEntity> userDB = this.userRepository.findById(id);

		if(userDB.isPresent()) {
			this.userRepository.delete(userDB.get());
			return "Deleted";
		}else {
			return "Error";
		}
	}

	@Override
	public UserModel getUserById(Long id) {
		Optional<UserEntity> userDB = this.userRepository.findById(id);

		if(userDB.isPresent()) {
			return UserConverter.toModel(userDB.get());
		}else {
			throw new ResourceNotFoundException("User not found");
		}
	}
}
