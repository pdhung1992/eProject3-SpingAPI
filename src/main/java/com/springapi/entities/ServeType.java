package com.springapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "servetypes")
@Getter
@Setter
public class ServeType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serve_type_id;

    @Column(name = "serve_type_name")
    private String serve_type_name;

    @Column(name = "value")
    private int value;

    public ServeType(String serve_type_name, int value) {
        this.serve_type_name = serve_type_name;
        this.value = value;
    }

    public ServeType() {
        super();
    }
}
