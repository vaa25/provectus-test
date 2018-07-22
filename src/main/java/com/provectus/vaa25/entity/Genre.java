package com.provectus.vaa25.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "genre")
@Accessors(chain = true)
public final class Genre {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;

}
