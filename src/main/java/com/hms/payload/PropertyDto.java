package com.hms.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyDto {
    private Long id;
    private String name;
    private Integer  no_of_guest;
    private Integer no_of_bedrooms;
    private Integer no_of_bathrooms;
    private Integer no_of_beds;
    private Long countryId;


}
