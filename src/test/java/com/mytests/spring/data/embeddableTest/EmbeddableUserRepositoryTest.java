package com.mytests.spring.data.embeddableTest;

import com.mytests.spring.data.embeddableTest.data.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmbeddableUserRepositoryTest {

    @Autowired
    private EmbeddableUserRepository repository;

    @Test
    void testSave() {
        repository.save(new EmbeddableUser(new UserId("1"),
                                           new UserName("vova", "pupkin"),
                                           new Email("otmorozok@mail.ru"),
                                           new Email("vladimir.pupkin@sk.ru")));
    }

    @Test
    void testFindByEmail() {
        repository.save(new EmbeddableUser(new UserId("1"),
                new UserName("vova", "pupkin"),
                new Email("otmorozok@mail.ru"),
                new Email("vladimir.pupkin@sk.ru")));

        Optional<EmbeddableUser> byEmail = repository.findByPersonalEmail(new Email("otmorozok@mail.ru"));
        assertThat(byEmail).isPresent();
    }
    @Test
    void testUpdateEmail() {
        repository.save(new EmbeddableUser(new UserId("1"),
                new UserName("vova", "pupkin"),
                new Email("otmorozok@mail.ru"),
                new Email("vladimir.pupkin@sk.ru")));

        repository.updateWorkMail("vp@president.ru");

        List<EmbeddableUser> users = (List<EmbeddableUser>) repository.findAll();
        for (EmbeddableUser user : users) {
            System.out.println("********************** "+user.getWorkEmail().getValue());
        }
        
    }
    @Test
    void testFindById() {
        List<EmbeddableUser> users = new ArrayList<>();
        users.add(new EmbeddableUser(new UserId("1"),
                new UserName("vova", "pupkin"),
                new Email("otmorozok@mail.ru"),
                new Email("vladimir.pupkin@sk.ru")));
        users.add(new EmbeddableUser(new UserId("2"),
                new UserName("vanya", "ivanov"),
                new Email("ivanuska@mail.ru"),
                new Email("ivan.ivanov@ochistka.ru")));
        repository.saveAll(users);
        EmbeddableUser user = repository.findByIdValue("2");
        System.out.println("**********************************");
        System.out.println(user);
        System.out.println("**********************************");
        assertThat(user.getId().getValue()).isEqualTo("2");


    }
}
