package emt.lab.eshop.web;

import emt.lab.eshop.model.Country;
import emt.lab.eshop.service.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryService countryService;


    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }


    @GetMapping
    public List<Country> findAll() {
        return this.countryService.findAll();
    }
}
