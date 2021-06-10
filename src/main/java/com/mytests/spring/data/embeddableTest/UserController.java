package com.mytests.spring.data.embeddableTest;

import com.mytests.spring.data.embeddableTest.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * *
 * <p>Created by irina on 6/10/2021.</p>
 * <p>Project: spring-jpa-embeddable</p>
 * *
 */
@RestController
public class UserController {
    @Autowired
    private EmbeddableUserRepository repository;

    @PostMapping("/add/{name}-{surname}-{id}")
    public void add(@PathVariable("name") String name, @PathVariable String surname, @PathVariable String id) {
        EmbeddableUser user = new EmbeddableUser(new UserId(id),
                new UserName(name, surname),
                new Email(name+"."+surname+"@gmail.com"),
                new Email(name+"."+surname+"@mycompany.com"));
        repository.save(user);
    }
    @PutMapping("/update/{company}")
    public void updateWorkEmail(@PathVariable String company){
        List<EmbeddableUser> users = (List<EmbeddableUser>) repository.findAll();
        for (EmbeddableUser user : users) {
            String prefix = user.getName().getGivenName()+"."
                            +user.getName().getFamilyName()
                             +"@";
            repository.updateWorkMail(prefix+company+".com");

        }
    }

    @GetMapping("/")
    public String getAll() {
        StringBuilder rez = new StringBuilder();

        List<EmbeddableUser> users = (List<EmbeddableUser>) repository.findAll();
        for (EmbeddableUser user : users) {
            rez.append("User: ").append(user.getName()).append(" ").append(user.getWorkEmail().getValue()).append("; ");
        }
        return rez.toString();
    }
}
