package com.provectus.vaa25.service;

import com.provectus.vaa25.dao.AuthorRepository;
import com.provectus.vaa25.dao.BookRepository;
import com.provectus.vaa25.dao.GenreRepository;
import com.provectus.vaa25.dao.OrderRepository;
import com.provectus.vaa25.entity.Author;
import com.provectus.vaa25.entity.Book;
import com.provectus.vaa25.entity.Genre;
import com.provectus.vaa25.entity.Purchase;
import com.provectus.vaa25.model.BooksFilter;
import com.provectus.vaa25.model.OrderDetails;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class BooksServiceImpl implements BooksService {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public BooksServiceImpl(
        final AuthorRepository authorRepository, final GenreRepository genreRepository,
        final BookRepository bookRepository, final OrderRepository orderRepository
    ) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Author> fetchAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public List<Genre> fetchAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public List<Book> fetchBooks(final BooksFilter booksFilter) {
        final List<Book> result;
        if (booksFilter.getAuthor() != null && booksFilter.getGenre() == null){
            result = bookRepository.findBooksByAuthors(new Author(booksFilter.getAuthor()));
        } else if (booksFilter.getAuthor() == null && booksFilter.getGenre() != null){
            result = bookRepository.findBooksByGenres(new Genre(booksFilter.getGenre()));
        } else if (booksFilter.getAuthor() != null && booksFilter.getGenre() != null){
            result = bookRepository.findBooksByAuthorsAndGenres(
                new Author(booksFilter.getAuthor()), new Genre(booksFilter.getGenre())
            );
        } else {
            result = bookRepository.findAll();
        }
        return result;
    }

    @Override
    public void orderBook(final Long bookId, final OrderDetails orderDetails) {
        orderRepository.saveAndFlush(new Purchase(new Book(bookId), orderDetails));
    }
}
