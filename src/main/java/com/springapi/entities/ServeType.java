package com.springapi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "servetypes")
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

    public int getServe_type_id() {
        return serve_type_id;
    }

    public void setServe_type_id(int serve_type_id) {
        this.serve_type_id = serve_type_id;
    }

    public String getServe_type_name() {
        return serve_type_name;
    }

    public void setServe_type_name(String serve_type_name) {
        this.serve_type_name = serve_type_name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
