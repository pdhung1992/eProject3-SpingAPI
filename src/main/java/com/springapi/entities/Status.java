package com.springapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "status")
@Getter
@Setter
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int status_id;

    @Column(name = "status")
    private String status;

    public Status(String status) {
        this.status = status;
    }

    public Status() {
        super();
    }

}
