package it.eos.springuser.repository;

import it.eos.springuser.model.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
public class UserRepositoryTest {

        @Autowired
        private UserRepository userRepository;

        @Test
        void when_save_user_then_find_record() {
            //given
            UserEntity userEntity = new UserEntity();
            userEntity.setMail("prova@mail.com");
            userEntity.setName("test");
            userEntity.setPassword("333");
            userEntity.setActive(true);

            //when
            UserEntity saved = userRepository.save(userEntity);
            UserEntity toFind = userRepository.findById(saved.getId()).orElse(null);

            //then
            Assertions.assertNotNull(toFind);
            Assertions.assertEquals(userEntity.getMail(), toFind.getMail());
            Assertions.assertEquals(userEntity.getName(), toFind.getName());
        }

    @Test
    void when_find_user_by_mail() {
        //given
        String mail ="andrea@mail.it";
        UserEntity userEntity = new UserEntity();
        userEntity.setMail("andrea@mail.it");
        userEntity.setName("test");
        userEntity.setPassword("333");
        userEntity.setActive(true);

        //when
        UserEntity saved = userRepository.save(userEntity);
        List<Long> id = userRepository.findIdByMail(mail);
        UserEntity toFind = userRepository.findById(id.get(0)).orElse(null);

        //then
        Assertions.assertNotNull(toFind);
        Assertions.assertEquals(mail, toFind.getMail());
    }



    @Test
    void when_delete_user_by_id() {
        //given
        UserEntity userEntity = new UserEntity();
        userEntity.setMail("andrea@mail.it");
        userEntity.setName("test");
        userEntity.setPassword("333");
        userEntity.setActive(true);
        long id = 1;

        //when
        UserEntity saved = userRepository.save(userEntity);
        userRepository.deleteById(id);
        UserEntity toFind = userRepository.findById(id).orElse(null);

        //then
        Assertions.assertNull(toFind);
    }

    @Test
    void when_change_name() {
        //given
        UserEntity userEntity = new UserEntity();
        userEntity.setMail("andrea@mail.it");
        userEntity.setName("test");
        userEntity.setPassword("333");
        userEntity.setActive(true);
        long id = 1;
        boolean state = true;

        //when
        UserEntity saved = userRepository.save(userEntity);
        userRepository.changeActive(state, id);
        UserEntity toFind = userRepository.findById(id).orElse(null);

        //then
        Assertions.assertNotNull(toFind);
        Assertions.assertEquals(state, toFind.isActive());
    }

    @Test
    void when_change_active() {
        //given
        UserEntity userEntity = new UserEntity();
        userEntity.setMail("andrea@mail.it");
        userEntity.setName("test");
        userEntity.setPassword("333");
        userEntity.setActive(true);
        long id = 1;
        boolean active = true;

        //when
        UserEntity saved = userRepository.save(userEntity);
        userRepository.changeActive(active, id);
        UserEntity toFind = userRepository.findById(id).orElse(null);

        //then
        Assertions.assertNotNull(toFind);
        Assertions.assertEquals(active, toFind.isActive());
    }

    @Test
    void when_mail_ending_with(){

        UserEntity userEntitySave = new UserEntity();
        userEntitySave.setMail("andrea@mail.com");
        userEntitySave.setName("test");
        userEntitySave.setPassword("333");
        userEntitySave.setActive(true);


        String contain =".com";

        UserEntity saved = userRepository.save(userEntitySave);
        List<UserEntity> list = userRepository.findByMailContaining(contain);

         Assertions.assertNotNull(list);

         UserEntity userEntity = list.get(0);
         UserEntity toFind = userRepository.findById(userEntity.getId()).orElse(null);

         Assertions.assertNotNull(toFind);
         Assertions.assertEquals(userEntity, toFind);

    }

    @Test
    void when_find_name_or_mail(){
        UserEntity userEntity = new UserEntity();
        userEntity.setMail("prova@mail.com");
        userEntity.setName("test");
        userEntity.setPassword("333");
        userEntity.setActive(true);

        String name = "test";

        UserEntity saved = userRepository.save(userEntity);
        UserEntity toFindName = userRepository.findByNameOrMail(name,null).get(0);

        Assertions.assertNotNull(toFindName);
        Assertions.assertEquals(name, toFindName.getName());

        UserEntity userMailEntity = new UserEntity();
        String mail ="prova@mail.com";

        UserEntity savedMail = userRepository.save(userEntity);
        UserEntity toFindMail = userRepository.findByNameOrMail(null,mail).get(0);

        Assertions.assertNotNull(toFindMail);
        Assertions.assertEquals(mail, toFindMail.getMail());
        
    }

    @Test
    void when_not_save_user_then_find_record() {
        //given
        UserEntity userEntity = new UserEntity();
        userEntity.setMail("prova@mail.com");
        userEntity.setName("test");
        userEntity.setPassword("333");
        userEntity.setActive(true);

        //when
        UserEntity saved = userRepository.save(userEntity);
        UserEntity toFind = userRepository.findById(saved.getId()).orElse(null);

        //then
        Assertions.assertNull(toFind);
    }

    @Test
    void when_not_change_active() {
        //given
        UserEntity userEntity = new UserEntity();
        userEntity.setMail("andrea@mail.it");
        userEntity.setName("test");
        userEntity.setPassword("333");
        userEntity.setActive(true);
        long id = 1;
        boolean active = false;

        //when
        UserEntity saved = userRepository.save(userEntity);
        userRepository.changeActive(true, id);
        UserEntity toFind = userRepository.findById(id).orElse(null);

        //then
        Assertions.assertNotNull(toFind);
        Assertions.assertEquals(active, toFind.isActive());
    }
}
