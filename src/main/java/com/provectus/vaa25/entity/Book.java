package com.provectus.vaa25.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "book")
@NoArgsConstructor
public final class Book {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String title;
    @OneToMany(targetEntity = Author.class, cascade = CascadeType.ALL)
    @JoinTable(name = "book_authors",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;
    @OneToMany(targetEntity = Genre.class, cascade = CascadeType.ALL)
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
