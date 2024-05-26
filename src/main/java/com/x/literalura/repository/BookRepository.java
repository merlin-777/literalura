package com.x.literalura.repository;

import com.x.literalura.models.Book;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByTitleContainingIgnoreCase(String bookName);
}
