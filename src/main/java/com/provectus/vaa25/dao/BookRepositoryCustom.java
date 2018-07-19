package com.provectus.vaa25.dao;

import com.provectus.vaa25.entity.Book;
import com.provectus.vaa25.model.BooksFilter;

import java.util.List;

public interface BookRepositoryCustom {

    List<Book> findBooksByFilter(BooksFilter filter);

}