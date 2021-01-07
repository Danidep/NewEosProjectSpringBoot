package it.eos.springuser.controller;

import it.eos.springuser.model.UserModel;
import it.eos.springuser.repository.UserEntity;
import it.eos.springuser.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@AutoConfigureJsonTesters
@WebMvcTest(UserController.class)
public class UserControllerMockTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Autowired
    private JacksonTester<UserModel> userEntityJacksonTester;

    @Test
    void canRetrieveByIdWhenExists() throws Exception {

        //given
        UserEntity userEntity = new UserEntity();
        userEntity.setMail("prova@mail.com");
        userEntity.setName("test");
        userEntity.setPassword("333");
        userEntity.setActive(false);

        UserModel userModel = new UserModel();
        userModel.setMail("prova@mail.com");
        userModel.setName("test");
        userModel.setPassword("333");
        userModel.setActive(false);

        Mockito.when(userService.getUserById(any(long.class))).thenReturn(userModel);

        //when
        MockHttpServletResponse response = mvc.perform(get("/user/1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //then
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(response.getContentAsString(), userEntityJacksonTester.write(userModel).getJson());

    }

    @Test
    void canSaveTheUser() throws Exception {

        //given
        UserEntity userEntity = new UserEntity();
        userEntity.setMail("prova@mail.com");
        userEntity.setName("test");
        userEntity.setPassword("333");
        userEntity.setActive(false);

        UserModel userModel = new UserModel();
        userModel.setMail("prova@mail.com");
        userModel.setName("test");
        userModel.setPassword("333");
        userModel.setActive(false);

        Mockito.when(userService.save(any(UserModel.class))).thenReturn(userModel);

        //when
        MockHttpServletResponse response = mvc.perform(post("/user")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //then
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(response.getContentAsString(), userEntityJacksonTester.write(userModel).getJson());

    }

    @Test
    void canUpdateTheUser() throws Exception {

        //given
        UserEntity userEntity = new UserEntity();
        userEntity.setMail("prova@mail.com");
        userEntity.setName("test");
        userEntity.setPassword("333");
        userEntity.setActive(false);

        UserModel userModel = new UserModel();
        userModel.setMail("prova@mail.com");
        userModel.setName("test");
        userModel.setPassword("333");
        userModel.setActive(false);

        Mockito.when(userService.putUser(any(UserModel.class))).thenReturn(userModel);

        //when
        MockHttpServletResponse response = mvc.perform(put("/user")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //then
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(response.getContentAsString(), userEntityJacksonTester.write(userModel).getJson());
    }

}
