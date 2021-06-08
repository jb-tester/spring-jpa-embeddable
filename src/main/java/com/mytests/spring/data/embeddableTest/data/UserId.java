package com.mytests.spring.data.embeddableTest.data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

//@Embeddable
public class UserId implements Serializable {
    @Column(name = "id")
    private Long value;

    protected UserId() {
    }

    public UserId(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
