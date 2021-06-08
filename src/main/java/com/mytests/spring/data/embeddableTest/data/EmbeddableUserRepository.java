package com.mytests.spring.data.embeddableTest.data;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmbeddableUserRepository extends CrudRepository<EmbeddableUser, UserId> {
    Optional<EmbeddableUser> findByPersonalEmail(Email email);

    @Modifying
    @Query("update EmbeddableUser u set u.workEmail.value = :newmail")
    void updateWorkMail( @Param(value = "newmail") String newmail);
}
