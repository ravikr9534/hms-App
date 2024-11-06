package com.hms.payload;

import java.util.List;

public class CityDto {
    private Long id;
    private String name;
    // Add a list to hold associated properties
    private List<PropertyDto> properties;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PropertyDto> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyDto> properties) {
        this.properties = properties;
    }
}
