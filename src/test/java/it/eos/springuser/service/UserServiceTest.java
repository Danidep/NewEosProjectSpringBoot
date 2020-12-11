package it.eos.springuser.service;

import it.eos.springuser.model.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void when_save_user_then_find_record() {
        //given
        UserModel userModel = new UserModel();
        userModel.setMail("prova@mail.com");
        userModel.setName("test");
        userModel.setPassword("333");
        userModel.setActive(true);

        //when
        UserModel saved = userService.save(userModel);
        UserModel toFind = userService.getUserById(saved.getId());

        //then
        Assertions.assertNotNull(toFind);
        Assertions.assertEquals(userModel.getMail(), toFind.getMail());
        Assertions.assertEquals(userModel.getName(), toFind.getName());
    }

    @Test
    void when_find_user_by_mail() {
        //given
        UserModel userModel = new UserModel();
        userModel.setMail("andrea@mail.it");
        userModel.setName("test");
        userModel.setPassword("333");
        userModel.setActive(true);

        //when
        UserModel saved = userService.save(userModel);
        List<Long> id = userService.findIdByMail(userModel.getMail());
        UserModel toFind = userService.getUserById(id.get(0));

        //then
        Assertions.assertNotNull(toFind);
        Assertions.assertEquals(saved.getMail(), toFind.getMail());
    }

    @Test
    void when_change_active() {
        //given
        UserModel userModel = new UserModel();
        userModel.setMail("andrea@mail.it");
        userModel.setName("test");
        userModel.setPassword("333");
        userModel.setActive(false);
        boolean active = false;

        //when
        UserModel saved = userService.save(userModel);
        userService.changeActive(active, saved.getId());
        UserModel toFind = userService.getUserById(saved.getId());

        //then
        Assertions.assertNotNull(toFind);
        Assertions.assertEquals(active, toFind.isActive());
    }

}
