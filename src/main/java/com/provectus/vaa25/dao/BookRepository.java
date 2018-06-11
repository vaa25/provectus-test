package com.provectus.vaa25.dao;

import com.provectus.vaa25.entity.Author;
import com.provectus.vaa25.entity.Book;
import com.provectus.vaa25.entity.Genre;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBooksByAuthorsAndGenres(Author author, Genre genre);
    List<Book> findBooksByAuthors(Author author);
    List<Book> findBooksByGenres(Genre genre);
}
