package emt.lab.eshop.service;

import emt.lab.eshop.model.Book;
import emt.lab.eshop.model.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id, BookDto bookDto);

    void deleteById(Long id);

    Optional<Book> markAsTaken(Long id);

    Page<Book> findAllByPagination(Pageable pageable);

}
