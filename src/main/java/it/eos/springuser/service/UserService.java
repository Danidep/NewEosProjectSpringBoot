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

	@Autowired
	private UserConverter userConverter;

	@Override
	public UserModel save(UserModel user) {
		UserEntity saveUser = userConverter.toEntity(user);
		userRepository.save(saveUser);
		return userConverter.toModel(saveUser);
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
			putUser.setActive(user.isActive());
			userRepository.save(putUser);
			return userConverter.toModel(putUser);
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
			return userConverter.toModel(userDB.get());
		}else {
			throw new ResourceNotFoundException("User not found");
		}
	}

	@Override
	public List<UserEntity> findByNameOrMail(String name, String mail) {
		return this.userRepository.findByNameOrMail(name, mail);
	}

	@Override
	public List<UserEntity> findByMailEndingWith(String end) {
		return this.userRepository.findByMailEndingWith(end);
	}

	@Override
	public List<UserEntity> findByMailContaining(String contain) {
		return this.userRepository.findByMailContaining(contain);
	}

	@Override
	public List<UserEntity> findByIdLessThan(Long id) {
		return this.userRepository.findByIdLessThan(id);
	}

	@Override
	public List<UserEntity> findByIdLessThanEqual(Long id) {
		return this.userRepository.findByIdLessThanEqual(id);
	}

	@Override
	public List<Long> findIdByMail(String contain) {
		return this.userRepository.findIdByMail(contain);
	}

	@Override
	public List<Long> findIdByName(String name) {
		return this.userRepository.findIdByName(name);
	}

}
