package it.eos.springuser.controller;

import it.eos.springuser.model.UserModel;
import it.eos.springuser.repository.UserEntity;
import it.eos.springuser.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerMockTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;

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

        given(userRepository.findById(1L)).willReturn(java.util.Optional.of(userEntity));

        //when
        MockHttpServletResponse response = mvc.perform(get("/user/1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                userEntityJacksonTester.write(userModel).getJson());

    }

}
