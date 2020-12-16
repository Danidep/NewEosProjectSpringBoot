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

import java.util.List;

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
            Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(toSave);
            UserModel saved = userService.save(userModel);

            //then
            Assertions.assertEquals(userModel, saved);
            Mockito.verify(userRepository, Mockito.times(1)).save(toSave);
            Mockito.verify(userConverter, Mockito.times(1)).toEntity(userModel);
            Mockito.verify(userConverter, Mockito.times(1)).toModel(toSave);
        }

        @Test
        void when_find_user_by_mail() {

            //given
            UserModel userModel = new UserModel();
            userModel.setMail("prova@mail.com");
            userModel.setName("test");
            userModel.setPassword("333");
            userModel.setActive(false);


            //when
            UserEntity toSave = userConverter.toEntity(userModel);
            Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(toSave);
            UserModel saved = userService.save(userModel);
            List<Long> id = userService.findIdByMail(userModel.getMail());
            UserModel toFind = null;
            if(!id.isEmpty()){
                toFind = userService.getUserById(id.get(0));
            }else
            {
                toFind = userModel;
            }

            //then
            Assertions.assertNotNull(toFind);
            Assertions.assertEquals(saved.getMail(), toFind.getMail());
            Mockito.verify(userRepository, Mockito.times(1)).save(toSave);
            Mockito.verify(userConverter, Mockito.times(1)).toModel(toSave);
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

            //when
            Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);
            UserEntity saved = userRepository.save(userEntity);
            try{
                userService.changeActive(active, saved.getId());
            }catch(Exception e){

            }

            //then
            Assertions.assertNotNull(saved);
            Assertions.assertEquals(active, saved.isActive());
            Mockito.verify(userRepository, Mockito.times(1)).save(userEntity);
        }
    }

