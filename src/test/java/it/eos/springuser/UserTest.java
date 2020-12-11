package it.eos.springuser;

import it.eos.springuser.business.UserConverter;
import it.eos.springuser.repository.UserEntity;
import it.eos.springuser.model.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Autowired
    UserConverter userConverter;

    @Test
    void convertBidirectionalModelEntity() {
        UserModel user1 = new UserModel();
        user1.setMail("Mail");
        user1.setPassword("Password");
        user1.setName("Name");

        UserEntity entity = userConverter.toEntity(user1);
        UserModel convertedFromEntity = userConverter.toModel(entity);

        Assertions.assertEquals(convertedFromEntity, user1);
    }
}
