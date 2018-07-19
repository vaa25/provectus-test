package com.provectus.vaa25.dao;

import com.provectus.vaa25.entity.Author;
import com.provectus.vaa25.entity.Book;
import com.provectus.vaa25.entity.Genre;
import com.provectus.vaa25.model.BooksFilter;
import lombok.val;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookRepositoryImpl implements BookRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Book> findBooksByFilter(final BooksFilter filter) {
        val builder = em.getCriteriaBuilder();
        val query = builder.createQuery(Book.class);
        val book = query.from(Book.class);
        query.select(book);
        final Predicate predicate = createPredicate(filter, book);
        query.where(predicate);
        query.orderBy(new OrderImpl(book.get("id")));
        return em.createQuery(query).getResultList();
    }

    private Predicate createPredicate(final BooksFilter filter, final Root<Book> book) {
        val builder = em.getCriteriaBuilder();
        final List<Predicate> predicates = new ArrayList<>();
        if (filter.getAuthor() != null) {
            predicates.add(builder.isMember(new Author().setId(filter.getAuthor()), book.get("authors")));
        }
        if (filter.getGenre() != null) {
            predicates.add(builder.isMember(new Genre().setId(filter.getGenre()), book.get("genres")));
        }
        return builder.and(predicates.toArray(new Predicate[]{}));
    }
}