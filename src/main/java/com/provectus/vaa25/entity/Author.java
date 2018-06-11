package com.provectus.vaa25.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "author")
public final class Author {

    public Author() {
    }

    public Author(final Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;

    @JsonProperty
    public Long id(){
        return id;
    }

    @JsonProperty
    public String name(){
        return name;
    }
}
