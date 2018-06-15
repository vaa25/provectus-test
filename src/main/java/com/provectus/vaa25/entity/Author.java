package com.provectus.vaa25.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "author")
@NoArgsConstructor
public final class Author {

    public Author(final Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;

}
