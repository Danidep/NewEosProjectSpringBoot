package it.eos.springuser;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.client.ExpectedCount;
import org.junit.jupiter.api.Assertions;

import it.eos.springuser.model.UserEntity;
import it.eos.springuser.model.UserModel;
import it.eos.springuser.business.UserConverter;

class EosProjectApplicationTests {

	@Test
	void contextLoads() {
		String t = "test@mail.com";

		Assertions.assertTrue(t.contains("@"));
	}

	@Test
	void contextEqual() {
		String n = "new@mail.it";

		Assertions.assertEquals("new@mail.it", n);
	}

	@Test
	void contextFalse() {
		String f = "Mario Rossi";

		Assertions.assertFalse(f.contains("@"));
	}

	@Test
	void convertToModel() {
		UserEntity userEntity = new UserEntity();
		userEntity.setMail("Mail");
		userEntity.setPassword("Password");
		userEntity.setName("Name");

		UserModel user = UserConverter.toModel(userEntity);

		Assertions.assertEquals(userEntity.getMail(), user.getMail());
		Assertions.assertEquals(userEntity.getPassword(), user.getPassword());
		Assertions.assertEquals(userEntity.getName(), user.getName());
	}

	@Test
	void convertToEntity() {
		UserModel user = new UserModel();
		user.setMail("Mail");
		user.setPassword("Password");
		user.setName("Name");

		String s1 = user.toString();
		String s2 = s1.substring(s1.indexOf("{"));
		String converted = UserConverter.toEntity(user).toString();
		String converted2 = converted.substring(converted.indexOf("{"));

		Assertions.assertEquals(s2, converted2);
	}

	@Test
	void convertBidirectionalModelEntity() {
		UserModel user1 = new UserModel();
		user1.setMail("Mail");
		user1.setPassword("Password");
		user1.setName("Name");

		UserEntity entity = UserConverter.toEntity(user1);
		UserModel convertedFromEntity = UserConverter.toModel(entity);

		Assertions.assertEquals(convertedFromEntity, user1);
	}
}
