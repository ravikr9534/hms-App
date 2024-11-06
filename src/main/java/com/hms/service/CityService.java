package com.hms.service;

import com.hms.entity.City;
import com.hms.payload.CityDto;
import com.hms.payload.PropertyDto;
import com.hms.repository.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {
    private final ModelMapper modelMapper;
    private final CityRepository cityRepository;

    public CityService(ModelMapper modelMapper, CityRepository cityRepository) {
        this.modelMapper = modelMapper;
        this.cityRepository = cityRepository;
    }

    private City mapToEntity(CityDto cityDto) {
        return modelMapper.map(cityDto, City.class);
    }

    private CityDto mapToDto(City city) {
        CityDto cityDto = modelMapper.map(city, CityDto.class);
        // Map each property in the city to PropertyDto
        List<PropertyDto> propertyDtos = city.getProperties().stream()
                .map(property -> modelMapper.map(property, PropertyDto.class))
                .collect(Collectors.toList());
        cityDto.setProperties(propertyDtos);
        return cityDto;
    }

    public City addCity(CityDto cityDto) {
        City data = mapToEntity(cityDto);
        cityRepository.save(data);
        return data;
    }

    public boolean deleteCity(Long id) {
        if (cityRepository.existsById(id)) {
            cityRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public CityDto getCityById(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("City with ID " + id + " does not exist."));
        return mapToDto(city);
    }

    public List<CityDto> getAllCities() {
        return cityRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
