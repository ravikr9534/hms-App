package com.hms.service;

import com.hms.entity.Country;
import com.hms.payload.CountryDto;
import com.hms.repository.CountryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;

    public CountryService(CountryRepository countryRepository, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
    }

    private Country mapToEntity(CountryDto dto) {
        return modelMapper.map(dto, Country.class);
    }

    public void addCountry(CountryDto dto) {
        Country data = mapToEntity(dto);
        countryRepository.save(data);
    }

    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }
}
