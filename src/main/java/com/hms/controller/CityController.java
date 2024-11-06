package com.hms.controller;

import com.hms.payload.CityDto;
import com.hms.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public ResponseEntity<String> addCity(@RequestBody CityDto cityDto) {
        cityService.addCity(cityDto);
        return new ResponseEntity<>("City added successfully", HttpStatus.CREATED);
    }

    // Get method to retrieve a city by ID
    @GetMapping("/{id}")
    public ResponseEntity<CityDto> getCity(@PathVariable Long id) {
        CityDto cityDto = cityService.getCityById(id);
        if (cityDto != null) {
            return new ResponseEntity<>(cityDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Additional Get method to retrieve all cities (optional)
    @GetMapping
    public ResponseEntity<List<CityDto>> getAllCities() {
        List<CityDto> cities = cityService.getAllCities();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    // Delete method
    @DeleteMapping("/deleteCity/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable Long id) {
        boolean isDeleted = cityService.deleteCity(id);
        if (isDeleted) {
            return new ResponseEntity<>("City deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("City not found", HttpStatus.NOT_FOUND);
        }
    }
}
