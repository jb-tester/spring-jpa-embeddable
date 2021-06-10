package com.mytests.spring.data.embeddableTest.data;

import javax.persistence.Embeddable;

@Embeddable
public class UserName {
    private String givenName;
    private String familyName;

    protected UserName() {
    }

    public UserName(String givenName,
                    String familyName) {
        this.givenName = givenName;
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    @Override
    public String toString() {
        return givenName+" "+familyName;
    }
}
