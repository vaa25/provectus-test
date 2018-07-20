package com.provectus.vaa25.api;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.provectus.vaa25.AbstractDbunitTest;
import com.provectus.vaa25.entity.Author;
import com.provectus.vaa25.entity.Book;
import com.provectus.vaa25.entity.Genre;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.github.springtestdbunit.assertion.DatabaseAssertionMode.NON_STRICT;
import static com.github.springtestdbunit.assertion.DatabaseAssertionMode.NON_STRICT_UNORDERED;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@DatabaseSetup("/dbunit/empty.xml")
public class AdminControllerTest extends AbstractDbunitTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DatabaseSetup("/dbunit/AdminControllerTest/opensBooksPage.xml")
    public void opensBooksPage() throws Exception {
        final List<Author> authors = asList(
                new Author().setId(1L).setName("author1"),
                new Author().setId(12L).setName("author2")
        );
        final List<Genre> genres = asList(
                new Genre().setId(2L).setName("genre1"),
                new Genre().setId(22L).setName("genre2")
        );
        this.mockMvc
                .perform(get("/admin/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin-books"))
                .andExpect(model().attribute("authors", authors))
                .andExpect(model().attribute("genres", genres))
                .andExpect(model().attribute("author", new Author()))
                .andExpect(model().attribute("genre", new Genre()))
                .andExpect(model().attribute("book", new Book()))
        ;
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/AdminControllerTest/createAuthor.xml", assertionMode = NON_STRICT)
    public void createAuthor() throws Exception {
        this.mockMvc
                .perform(post("/admin/authors/create")
                        .contentType(APPLICATION_FORM_URLENCODED)
                        .flashAttr("author", new Author().setName("author")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/books"))
        ;
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/AdminControllerTest/createGenre.xml", assertionMode = NON_STRICT)
    public void createGenre() throws Exception {
        this.mockMvc
                .perform(post("/admin/genres/create")
                        .contentType(APPLICATION_FORM_URLENCODED)
                        .flashAttr("genre", new Genre().setName("genre")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/books"))
        ;
    }

    @Test
    @DatabaseSetup("/dbunit/AdminControllerTest/createsBook_initial.xml")
    @ExpectedDatabase(value = "/dbunit/AdminControllerTest/createsBook_expected.xml", assertionMode = NON_STRICT)
    public void createsBook() throws Exception {
        final List<Author> authors = asList(
                new Author().setId(1L),
                new Author().setId(12L)
        );
        final List<Genre> genres = asList(
                new Genre().setId(2L),
                new Genre().setId(22L)
        );
        final Book book = new Book()
                .setTitle("book")
                .setAuthors(authors)
                .setGenres(genres)
                .setDescription("description")
                .setPrice(3.0);
        this.mockMvc
                .perform(post("/admin/books/save")
                        .contentType(APPLICATION_FORM_URLENCODED)
                        .flashAttr("book", book))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/books"))
        ;
    }

    @Test
    @DatabaseSetup("/dbunit/AdminControllerTest/editsBook_initial.xml")
    @ExpectedDatabase(
            value = "/dbunit/AdminControllerTest/editsBook_expected.xml", assertionMode = NON_STRICT_UNORDERED
    )
    public void editsBook() throws Exception {
        final List<Author> authors = singletonList(new Author().setId(12L));
        final List<Genre> genres = singletonList(new Genre().setId(22L));
        final Book book = new Book()
                .setId(3L)
                .setTitle("book")
                .setAuthors(authors)
                .setGenres(genres)
                .setDescription("description")
                .setPrice(3.0);
        this.mockMvc
                .perform(post("/admin/books/save")
                        .contentType(APPLICATION_FORM_URLENCODED)
                        .flashAttr("book", book))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/books"))
        ;
    }
}