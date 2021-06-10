package com.mytests.spring.data.embeddableTest.data;

import javax.persistence.Column;
import java.io.Serializable;

//@Embeddable
public class UserId implements Serializable {
    @Column(name = "id")
    private String value;

    protected UserId() {
    }

    public UserId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
