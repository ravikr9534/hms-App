package com.hms.controller;

import com.hms.entity.AppUser;
import com.hms.payload.CountryDto;
import com.hms.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/api/v1/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("/addCountry")
    public ResponseEntity<?> addCountry(@RequestBody CountryDto dtoa, @AuthenticationPrincipal AppUser user) {

        countryService.addCountry(dtoa);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<String> deleteCountry(@RequestParam Long id) {
        countryService.deleteCountry(id);
        return new ResponseEntity<>("Country deleted successfully", HttpStatus.OK);
    }
}
