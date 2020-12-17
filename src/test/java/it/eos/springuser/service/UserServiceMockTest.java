package it.eos.springuser.service;

import it.eos.springuser.business.UserConverter;
import it.eos.springuser.model.UserModel;
import it.eos.springuser.repository.UserEntity;
import it.eos.springuser.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
    public class UserServiceMockTest {

        @InjectMocks
        UserService userService;

        @Mock
        UserRepository userRepository;

        @Spy
        UserConverter userConverter;

        @Test
        void when_save_user_then_find_record() {

            //given
            UserModel userModel = new UserModel();
            userModel.setMail("prova@mail.com");
            userModel.setName("test");
            userModel.setPassword("333");
            userModel.setActive(false);

            UserEntity toSave = new UserEntity();
            toSave.setMail("prova@mail.com");
            toSave.setName("test");
            toSave.setPassword("333");
            toSave.setActive(false);

            //when
            when(userRepository.save(any(UserEntity.class))).thenReturn(toSave);
            UserModel saved = userService.save(userModel);

            //then
            Assertions.assertEquals(userModel, saved);
            verify(userRepository, times(1)).save(toSave);
            verify(userConverter, times(1)).toEntity(userModel);
            verify(userConverter, times(1)).toModel(toSave);
        }

    @Test
    void when_find_id_by_mail_then_check_correct_id() {

        //given
        UserModel userModel = new UserModel();
        userModel.setMail("prova@mail.com");

        UserEntity toSave = new UserEntity();
        toSave.setMail("prova@mail.com");

        List<Long> idListMockingOutput = Collections.singletonList(1L);

        //when
        when(userRepository.save(any(UserEntity.class))).thenReturn(toSave);
        when(userRepository.findIdByMail(same(userModel.getMail()))).thenReturn(idListMockingOutput);
        userService.save(userModel);
        List<Long> idListOutputService = userService.findIdByMail(userModel.getMail());

        //then
        Assertions.assertNotNull(idListOutputService);
        Assertions.assertEquals(idListMockingOutput, idListOutputService);
        verify(userRepository, times(1)).findIdByMail(userModel.getMail());
        verify(userRepository, times(1)).save(any(UserEntity.class));
        verify(userConverter, times(1)).toEntity(userModel);
        verify(userConverter, times(1)).toModel(toSave);
    }

        @Test
        void when_change_active() {

            //given
            UserModel userModel = new UserModel();
            userModel.setMail("test@mail.it");
            userModel.setName("test");
            userModel.setPassword("333");
            userModel.setActive(false);
            boolean active = false;

            UserEntity userEntity = new UserEntity();
            userEntity.setMail("prova@mail.com");
            userEntity.setName("test");
            userEntity.setPassword("333");
            userEntity.setActive(false);

            UserEntity userModelMock = new UserEntity();

            UserEntity userEntityActive = new UserEntity();

            //when
            when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
            when(userRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(userModelMock));
            Mockito.doAnswer(RETURNS_MOCKS).when(userRepository).changeActive(eq(active),Mockito.anyLong());
            userService.save(userModel);
            userModel = userService.changeActive(active,0);

            //then
            Assertions.assertNotNull(userModel);
            Assertions.assertEquals(active, userModel.isActive());
            verify(userRepository, times(1)).save(any(UserEntity.class));
            verify(userRepository, times(1)).changeActive(active,0);
            verify(userRepository, times(1)).findById(0L);
        }
    }

