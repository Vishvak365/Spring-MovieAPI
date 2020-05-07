package com.example.movielistapi.movielist.Collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

// @Data
@Document(collection = "user")
public class UserCollection {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String emailAddress;

    public UserCollection(String firstName, String lastName, String emailAddress) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "UserCollection [emailAddress=" + emailAddress + ", firstName=" + firstName + ", id=" + id
                + ", lastName=" + lastName + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
}