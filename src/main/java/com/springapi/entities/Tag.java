package com.springapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@Entity
@Table(name = "tags")
@Getter
@Setter
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
}
