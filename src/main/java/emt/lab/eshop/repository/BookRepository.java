package emt.lab.eshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import emt.lab.eshop.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAll(Pageable pageable);
    void deleteByName(String name);
}
