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

        //when
        List<Long> id = userRepository.findIdByMail(mail);
        UserEntity toFind = userRepository.findById(id.get(0)).orElse(null);

        //then
        Assertions.assertNotNull(toFind);
        Assertions.assertEquals(mail, toFind.getMail());
    }

    @Test
    void when_delete_user_by_id() {
        //given
        long id = 1;

        //when
        userRepository.deleteById(id);
        UserEntity toFind = userRepository.findById(id).orElse(null);

        //then
        Assertions.assertNull(toFind);
    }

    @Test
    void when_change_name() {
        //given
        long id = 1;
        boolean state = true;

        //when
        userRepository.changeActive(state, id);
        UserEntity toFind = userRepository.findById(id).orElse(null);

        //then
        Assertions.assertNotNull(toFind);
        Assertions.assertEquals(state, toFind.isActive());
    }

    @Test
    void when_change_active() {
        //given
        long id = 1;
        boolean active = true;

        //when
        userRepository.changeActive(active, id);
        UserEntity toFind = userRepository.findById(id).orElse(null);

        //then
        Assertions.assertNotNull(toFind);
        Assertions.assertEquals(active, toFind.isActive());
    }

    @Test
    void when_mail_ending_with(){
         String contain =".com";

         List<UserEntity> list = userRepository.findByMailContaining(contain);

         Assertions.assertNotNull(list);

         UserEntity userEntity = list.get(0);
         UserEntity toFind = userRepository.findById(userEntity.getId()).orElse(null);

         Assertions.assertNotNull(toFind);
         Assertions.assertEquals(userEntity, toFind);

    }

    @Test
    void when_find_name_or_mail(){
        String name = "andrea";

        UserEntity toFindName = userRepository.findByNameOrMail(name,null).get(0);

        Assertions.assertNotNull(toFindName);
        Assertions.assertEquals(name, toFindName.getName());

        String mail ="giulia@mail.com";

        UserEntity toFindMail = userRepository.findByNameOrMail(null,mail).get(0);

        Assertions.assertNotNull(toFindMail);
        Assertions.assertEquals(mail, toFindMail.getMail());
        
    }
}
