package com.hms.service;

import com.hms.entity.City;
import com.hms.entity.Country;
import com.hms.entity.Property;
import com.hms.payload.PropertyDto;
import com.hms.repository.CityRepository;
import com.hms.repository.CountryRepository;
import com.hms.repository.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    private PropertyRepository propertyRepository;
     private ModelMapper modelMapper;
     private CityRepository cityRepository;
     private CountryRepository countryRepository;
    public PropertyService(PropertyRepository propertyRepository, ModelMapper modelMapper, CityRepository cityRepository, CountryRepository countryRepository) {
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }
    protected Property mapToEntity(PropertyDto dto){
       return modelMapper.map(dto,Property.class);

    } protected List<PropertyDto> mapToDto(List<Property> dto) {
        return  dto.stream().map(e -> modelMapper.map(e, PropertyDto.class)).collect(Collectors.toList());
    }

    public Property addData(Long cityId, PropertyDto data) {

        if (data.getCountryId() == null) {
            throw new IllegalArgumentException("Country ID must not be null");
        }

      City  city=cityRepository.findById(cityId).get();
        Long countryId = data.getCountryId();
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new IllegalArgumentException("Country not found with ID: " + countryId));

        Property entity = mapToEntity(data);
        entity.setCity(city);
        entity.setCountry(country);
       Property saved= propertyRepository.save(entity);
       return saved;
    }

    public List<PropertyDto> getData() {
      List<Property> data=propertyRepository.findAll();
        return mapToDto(data);

    }

    public Property update(PropertyDto data) {
        Property entity=mapToEntity(data);
        Optional<Property> dar=propertyRepository.findById(data.getId());
        Property savedData= propertyRepository.save(entity);
       return savedData;
    }

    public List<Property> search(String name) {
        List<Property> result= propertyRepository.searchHotels(name);
        return result;
    }
}
