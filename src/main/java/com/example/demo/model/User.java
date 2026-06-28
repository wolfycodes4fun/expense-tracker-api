package com.example.demo.model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class User {

    @Id
    private final Long id;

    @NotNull(message = "First Name is required!")
    private String firstName;

    @NotNull(message = "Last Name is required")
    private String lastName;

    @NotNull(message = "Username is required!")
    private String username;

    @NotNull(message = "Password is required!!!")
    private String password;

    public User(Long id, String firstName, String lastName, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
}
