package com.hms.controller;

import com.hms.entity.Property;
import com.hms.payload.PropertyDto;
import com.hms.service.PropertyService;
import jakarta.annotation.Priority;
import org.apache.coyote.Response;
import org.apache.logging.log4j.status.StatusConsoleListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyControllar {

    private PropertyService propertyService;

    public PropertyControllar(PropertyService propertyService) {
        this.propertyService = propertyService;
    }
    @PostMapping
    public ResponseEntity<?>add( @RequestParam Long cityId,@RequestBody PropertyDto dto) {

        Property property = propertyService.addData(cityId,dto);
        return  new ResponseEntity<>(property, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<?> getAll() {
         List< PropertyDto> data=propertyService.getData();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Property> updateData(@RequestBody PropertyDto data){
         Property updated=propertyService.update(data);
        return new ResponseEntity<>(updated,HttpStatus.OK);
    }
    @GetMapping("/search")
    public List<Property>searchHotels(@RequestParam String name){

        List<Property> data=propertyService.search(name);
        return data;
    }
}
