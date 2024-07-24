package com.springapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;


    public User(String fullname, String email, String phone, String password) {
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.password = password;
    };

    public User() {
        super();
    }

}
