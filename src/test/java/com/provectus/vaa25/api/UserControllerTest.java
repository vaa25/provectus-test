package com.provectus.vaa25.api;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.provectus.vaa25.AbstractDbunitTest;
import com.provectus.vaa25.entity.Author;
import com.provectus.vaa25.entity.Genre;
import com.provectus.vaa25.model.PurchaseDetails;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.github.springtestdbunit.assertion.DatabaseAssertionMode.NON_STRICT;
import static com.provectus.vaa25.ResourceReader.resourceAsString;
import static java.util.Arrays.asList;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@DatabaseSetup("/dbunit/empty.xml")
@DatabaseSetup("/dbunit/UserControllerTest/UserControllerTest.xml")
public class UserControllerTest extends AbstractDbunitTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
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
                .perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(model().attribute("authors", authors))
                .andExpect(model().attribute("genres", genres))
                .andExpect(model().attribute("purchaseDetails", new PurchaseDetails()))
        ;
    }

    @Test
    public void fetchesFilteredBooks() throws Exception {
        this.mockMvc
                .perform(post("/books/filtered")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content("{\"author\":1,\"genre\":22}"))
                .andExpect(status().isOk())
                .andExpect(content().json(resourceAsString("/json/fetchBooks.json")))
        ;
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/UserControllerTest/purchasesBook.xml", assertionMode = NON_STRICT)
    public void purchasesBook() throws Exception {
        final PurchaseDetails purchase = new PurchaseDetails()
                .setBookId(3L)
                .setFirstName("first")
                .setLastName("last")
                .setAddress("address")
                .setQuantity(1);
        this.mockMvc
                .perform(post("/books/purchase")
                        .contentType(APPLICATION_FORM_URLENCODED)
                        .flashAttr("purchaseDetails", purchase))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
        ;
    }
}