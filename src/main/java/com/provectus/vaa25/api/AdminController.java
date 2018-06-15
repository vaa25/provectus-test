package com.provectus.vaa25.api;

import com.provectus.vaa25.entity.Author;
import com.provectus.vaa25.entity.Genre;
import com.provectus.vaa25.service.BooksService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@Controller
public final class AdminController {

    private final BooksService booksService;

    @Autowired
    public AdminController(final BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping(path = "/admin/books")
    public String openBooksPage(final Model model) {
        final List<Author> authors = booksService.fetchAllAuthors();
        final List<Genre> genres = booksService.fetchAllGenres();
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        model.addAttribute("genre", new Genre());
        model.addAttribute("author", new Author());
        return "admin-books";
    }

    @PostMapping(path = "/admin/books/create", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    public String createBook(@ModelAttribute final Author author) {
        booksService.createAuthor(author);
        return "redirect:/admin/books";
    }

    @PostMapping(path = "/admin/genres/create", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    public String createGenre(@ModelAttribute final Genre genre) {
        booksService.createGenre(genre);
        return "redirect:/admin/books";
    }


}
