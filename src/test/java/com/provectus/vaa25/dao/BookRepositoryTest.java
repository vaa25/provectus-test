package com.provectus.vaa25.dao;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.provectus.vaa25.AbstractDbunitTest;
import com.provectus.vaa25.entity.Author;
import com.provectus.vaa25.entity.Book;
import com.provectus.vaa25.entity.Genre;
import com.provectus.vaa25.model.BooksFilter;
import lombok.val;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@DatabaseSetup("/dbunit/empty.xml")
@DatabaseSetup("/dbunit/BookRepositoryTest/BookRepositoryTest.xml")
public class BookRepositoryTest extends AbstractDbunitTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @Transactional
    public void findBooksByAuthorAndGenre() {
        val genre1 = new Genre(2L).setName("genre1");
        val genre2 = new Genre(22L).setName("genre2");
        val author1 = new Author(1L).setName("author1");
        val author2 = new Author(12L).setName("author2");
        val book3 = new Book(33L)
                .setTitle("title3")
                .setDescription("description3")
                .setPrice(3.0)
                .setAuthors(asList(author1, author2))
                .setGenres(asList(genre1, genre2));

        val expected = singletonList(book3);
        val actual = bookRepository.findBooksByFilter(new BooksFilter(1L, 22L));

        assertThat(actual.toString(), is(expected.toString()));

    }

    @Test
    @Transactional
    public void findBooksByAuthor() {
        val genre1 = new Genre(2L).setName("genre1");
        val genre2 = new Genre(22L).setName("genre2");
        val author1 = new Author(1L).setName("author1");
        val author2 = new Author(12L).setName("author2");
        val book2 = new Book(32L)
                .setTitle("title2")
                .setDescription("description2")
                .setPrice(2.0)
                .setAuthors(singletonList(author2))
                .setGenres(singletonList(genre2));
        val book3 = new Book(33L)
                .setTitle("title3")
                .setDescription("description3")
                .setPrice(3.0)
                .setAuthors(asList(author1, author2))
                .setGenres(asList(genre1, genre2));

        val expected = asList(book2, book3);
        final List<Book> actual = bookRepository.findBooksByFilter(new BooksFilter(12L, null));

        assertThat(actual.toString(), is(expected.toString()));
    }

    @Test
    @Transactional
    public void findBooksByGenres() {
        val genre1 = new Genre(2L).setName("genre1");
        val genre2 = new Genre(22L).setName("genre2");
        val author1 = new Author(1L).setName("author1");
        val author2 = new Author(12L).setName("author2");
        val book1 = new Book(3L)
                .setTitle("title1")
                .setDescription("description1")
                .setPrice(1.0)
                .setAuthors(singletonList(author1))
                .setGenres(singletonList(genre1));
        val book3 = new Book(33L)
                .setTitle("title3")
                .setDescription("description3")
                .setPrice(3.0)
                .setAuthors(asList(author1, author2))
                .setGenres(asList(genre1, genre2));

        val expected = asList(book1, book3);
        final List<Book> actual = bookRepository.findBooksByFilter(new BooksFilter(null, 2L));

        assertThat(actual.toString(), is(expected.toString()));
    }

    @Test
    @Transactional
    public void findAllBooks() {
        val genre1 = new Genre(2L).setName("genre1");
        val genre2 = new Genre(22L).setName("genre2");
        val author1 = new Author(1L).setName("author1");
        val author2 = new Author(12L).setName("author2");
        val book1 = new Book(3L)
                .setTitle("title1")
                .setDescription("description1")
                .setPrice(1.0)
                .setAuthors(singletonList(author1))
                .setGenres(singletonList(genre1));
        val book2 = new Book(32L)
                .setTitle("title2")
                .setDescription("description2")
                .setPrice(2.0)
                .setAuthors(singletonList(author2))
                .setGenres(singletonList(genre2));
        val book3 = new Book(33L)
                .setTitle("title3")
                .setDescription("description3")
                .setPrice(3.0)
                .setAuthors(asList(author1, author2))
                .setGenres(asList(genre1, genre2));

        val expected = asList(book1, book2, book3);
        final List<Book> actual = bookRepository.findBooksByFilter(new BooksFilter(null, null));

        assertThat(actual.toString(), is(expected.toString()));
    }

}