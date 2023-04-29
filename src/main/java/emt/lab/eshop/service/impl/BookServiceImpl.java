package emt.lab.eshop.service.impl;

import emt.lab.eshop.model.Author;
import emt.lab.eshop.model.Book;
import emt.lab.eshop.model.dto.BookDto;
import emt.lab.eshop.model.enumerations.Category;
import emt.lab.eshop.model.exceptions.AuthorNotFoundException;
import emt.lab.eshop.model.exceptions.BookNotFoundException;
import emt.lab.eshop.model.exceptions.CannotTakeBookException;
import emt.lab.eshop.repository.AuthorRepository;
import emt.lab.eshop.repository.BookRepository;
import emt.lab.eshop.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }


    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
/*
        Category category = Category.valueOf(bookDto.getCategory().name());
*/
        this.bookRepository.deleteByName(bookDto.getName());
        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        if (book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
        } else {
            throw new CannotTakeBookException();
        }
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Page<Book> findAllByPagination(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
}
