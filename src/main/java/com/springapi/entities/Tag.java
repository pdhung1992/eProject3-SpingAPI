package com.springapi.entities;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tag_id;

    @Column(name = "tag_name")
    private String tag_name;

    public Tag(String tag_name) {
        this.tag_name = tag_name;
    }

    public Tag() {
        super();
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }
}
