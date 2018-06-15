package com.provectus.vaa25.service;

import com.provectus.vaa25.dao.AuthorRepository;
import com.provectus.vaa25.dao.BookRepository;
import com.provectus.vaa25.dao.GenreRepository;
import com.provectus.vaa25.dao.PurchaseRepository;
import com.provectus.vaa25.entity.Author;
import com.provectus.vaa25.entity.Book;
import com.provectus.vaa25.entity.Genre;
import com.provectus.vaa25.entity.Purchase;
import com.provectus.vaa25.model.BooksFilter;
import com.provectus.vaa25.model.PurchaseDetails;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BooksServiceImpl implements BooksService {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public BooksServiceImpl(
        final AuthorRepository authorRepository, final GenreRepository genreRepository,
        final BookRepository bookRepository, final PurchaseRepository purchaseRepository
    ) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> fetchAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> fetchAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional
    public void purchaseBook(final PurchaseDetails purchaseDetails) {
        purchaseRepository.saveAndFlush(new Purchase(purchaseDetails));
    }

    @Override
    @Transactional
    public void createGenre(final Genre genre) {
        if (!genreRepository.exists(Example.of(genre))){
            genreRepository.saveAndFlush(genre);
        }
    }

    @Override
    @Transactional
    public void createAuthor(final Author author) {
        if (!authorRepository.exists(Example.of(author))){
            authorRepository.saveAndFlush(author);
        }
    }

    @Override
    @Transactional
    public void saveBook(final Book book) {
        if (!bookRepository.exists(Example.of(book))){
            bookRepository.saveAndFlush(book);
        }
    }
}
