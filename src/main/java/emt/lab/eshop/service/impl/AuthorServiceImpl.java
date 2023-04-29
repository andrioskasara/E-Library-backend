package emt.lab.eshop.service.impl;

import emt.lab.eshop.model.Author;
import emt.lab.eshop.model.Country;
import emt.lab.eshop.model.exceptions.AuthorNotFoundException;
import emt.lab.eshop.model.exceptions.CountryNotFoundException;
import emt.lab.eshop.repository.AuthorRepository;
import emt.lab.eshop.repository.CountryRepository;
import emt.lab.eshop.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(String name, String surname, Long countryId) {
        Country country = this.countryRepository.findById(countryId).get();
        Author author = new Author(name, surname, country);
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }

}
