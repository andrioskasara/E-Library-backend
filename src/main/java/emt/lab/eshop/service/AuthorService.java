package emt.lab.eshop.service;

import emt.lab.eshop.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> save(String name, String surname, Long country);

    void deleteById(Long id);
}
