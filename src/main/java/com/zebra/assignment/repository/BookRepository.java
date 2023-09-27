package com.zebra.assignment.repository;

import com.zebra.assignment.repository.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Boolean existsByIsbn(String isbn);
    Optional<Book> findOptionalByIsbn(String isbn);
    Optional<Book> findOptionalByIdAndIsbn(Long id, String isbn);
}
