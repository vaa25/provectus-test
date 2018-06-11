package com.provectus.vaa25.service;

import com.provectus.vaa25.entity.Author;
import com.provectus.vaa25.entity.Book;
import com.provectus.vaa25.entity.Genre;
import com.provectus.vaa25.model.BooksFilter;
import com.provectus.vaa25.model.OrderDetails;
import java.util.List;

public interface BooksService {
    List<Author> fetchAllAuthors();

    List<Genre> fetchAllGenres();

    List<Book> fetchBooks(final BooksFilter booksFilter);

    void orderBook(Long bookId, OrderDetails orderDetails);
}
