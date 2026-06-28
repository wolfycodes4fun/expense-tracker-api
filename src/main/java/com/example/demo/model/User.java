package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class User {

    @Id
    private final Long id;

    @NotNull(message = "First Name is required!")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull(message = "Last Name is required")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull(message = "Username is required!")
    @Column(name = "username", nullable = false)
    private String username;

    @NotNull(message = "Password is required!!!")
    @Column(name = "password", nullable = false)
    private String password;

    public User(Long id, String firstName, String lastName, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
}
