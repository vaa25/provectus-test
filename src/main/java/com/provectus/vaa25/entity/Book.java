package com.provectus.vaa25.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "book")
@NoArgsConstructor
@Accessors(chain = true)
public final class Book {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String title;
    @ManyToMany(targetEntity = Author.class)
    @JoinTable(name = "book_authors",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;
    @ManyToMany(targetEntity = Genre.class)
    @JoinTable(name = "book_genres",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre>genres;
    private String description;
    private Double price;

    public Book(final Long id) {
        this.id = id;
    }
}
