package com.provectus.vaa25.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.NoArgsConstructor;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "book")
@NoArgsConstructor
public final class Book {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JsonProperty
    private Long id;
    @JsonProperty
    private String title;
    @OneToMany(targetEntity = Author.class, cascade = CascadeType.ALL)
    @JoinTable(name = "book_authors",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id"))
    @JsonProperty
    private List<Author> authors;
    @OneToMany(targetEntity = Genre.class, cascade = CascadeType.ALL)
    @JoinTable(name = "book_genres",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @JsonProperty
    private List<Genre>genres;
    @JsonProperty
    private String description;
    @JsonProperty
    private Double price;

    public Book(final Long id) {
        this.id = id;
    }
}
