package com.provectus.vaa25.api;

import com.provectus.vaa25.entity.Author;
import com.provectus.vaa25.entity.Book;
import com.provectus.vaa25.entity.Genre;
import com.provectus.vaa25.model.BooksFilter;
import com.provectus.vaa25.model.OrderDetails;
import com.provectus.vaa25.service.BooksService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public final class AdminController {

    private final BooksService booksService;

    @Autowired
    public AdminController(final BooksService booksService) {
        this.booksService = booksService;
    }

    @RequestMapping(method = GET, path = "/books")
    public String openBooksPage(final Model model) {
        final List<Author> authors = booksService.fetchAllAuthors();
        final List<Genre> genres = booksService.fetchAllGenres();
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "books";
    }

    @RequestMapping(
        method = POST, path = "/books/filtered", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseBody
    public List<Book> fetchBooks(@RequestBody final BooksFilter booksFilter) {
        return booksService.fetchBooks(booksFilter);
    }

    @RequestMapping(
        method = POST, path = "/books/{bookId}/order", consumes = APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseBody
    public void orderBook(@PathVariable("bookId") final Long bookId, @RequestBody final OrderDetails orderDetails){
        booksService.orderBook(bookId, orderDetails);
    }

}
