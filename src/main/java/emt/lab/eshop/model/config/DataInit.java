package emt.lab.eshop.model.config;

import emt.lab.eshop.model.Author;
import emt.lab.eshop.model.Book;
import emt.lab.eshop.model.Country;
import emt.lab.eshop.model.dto.BookDto;
import emt.lab.eshop.model.enumerations.Category;
import emt.lab.eshop.repository.CountryRepository;
import emt.lab.eshop.service.AuthorService;
import emt.lab.eshop.service.BookService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInit {
    private final CountryRepository countryRepository;
    private final AuthorService authorService;
    private final BookService bookService;

    private static List<Country> countries = new ArrayList<>();
    private static List<Author> authors = new ArrayList<>();
    private static List<Book> books = new ArrayList<>();

    public DataInit(CountryRepository countryRepository, AuthorService authorService, BookService bookService) {
        this.countryRepository = countryRepository;
        this.authorService = authorService;
        this.bookService = bookService;
    }


    @PostConstruct
    public void init(){
        initCountries();
        initAuthors();
        initBooks();
    }

    private void initCountries(){
        countries.add(countryRepository.save(new Country("England", "Europe")));
        countries.add(countryRepository.save(new Country("Russia", "Europe")));
        countries.add(countryRepository.save(new Country("France", "Europe")));
    }

    private void initAuthors(){
        authors.add(authorService.save("Joanne", "Rowling", countries.get(0).getId()).orElse(null));
        authors.add(authorService.save("William", "Shakespeare", countries.get(0).getId()).orElse(null));
        authors.add(authorService.save("Jane", "Austen", countries.get(0).getId()).orElse(null));
        authors.add(authorService.save("Leo", "Tolstoy", countries.get(1).getId()).orElse(null));
        authors.add(authorService.save("Fyodor", "Dostoyevsky", countries.get(1).getId()).orElse(null));
        authors.add(authorService.save("Victor", "Hugo", countries.get(2).getId()).orElse(null));
    }

    private void initBooks(){
        books.add(bookService.save(new BookDto("Harry Potter and the Philosopher's Stone", Category.FANTASY, authors.get(0).getId(), 100)).orElse(null));
        books.add(bookService.save(new BookDto("Harry Potter and the Chamber of Secrets", Category.FANTASY, authors.get(0).getId(), 100)).orElse(null));
        books.add(bookService.save(new BookDto("Harry Potter and the Prisoner of Azkaban", Category.FANTASY, authors.get(0).getId(), 100)).orElse(null));
        books.add(bookService.save(new BookDto("Harry Potter and the Goblet of Fire", Category.FANTASY, authors.get(0).getId(), 100)).orElse(null));
        books.add(bookService.save(new BookDto("Harry Potter and the Order of the Phoenix", Category.FANTASY, authors.get(0).getId(), 100)).orElse(null));
        books.add(bookService.save(new BookDto("Harry Potter and the Half-Blood Prince", Category.FANTASY, authors.get(0).getId(), 100)).orElse(null));
        books.add(bookService.save(new BookDto("Harry Potter and the Deathly Hallows", Category.FANTASY, authors.get(0).getId(), 100)).orElse(null));
        books.add(bookService.save(new BookDto("Hamlet", Category.FANTASY, authors.get(1).getId(), 100)).orElse(null));
        books.add(bookService.save(new BookDto("Pride and Prejudice", Category.NOVEL, authors.get(2).getId(), 100)).orElse(null));
        books.add(bookService.save(new BookDto("War and Peace", Category.NOVEL, authors.get(3).getId(), 100)).orElse(null));
        books.add(bookService.save(new BookDto("Crime and Punishment", Category.NOVEL, authors.get(4).getId(), 100)).orElse(null));
        books.add(bookService.save(new BookDto("Les Misérables", Category.NOVEL, authors.get(5).getId(), 100)).orElse(null));
    }
}
