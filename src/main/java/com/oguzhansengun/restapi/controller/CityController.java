package com.oguzhansengun.restapi.controller;

import com.oguzhansengun.restapi.model.City;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private static final List<City> cities = new ArrayList<>();
    public CityController(){
        if (cities.isEmpty()) {
            City city1 = new City("07", "Antalya", new Date());
            City city2 = new City("06", "Ankara", new Date());
            cities.add(city1);
            cities.add(city2);
        }
    }

    @GetMapping
    public ResponseEntity<List<City>> getCities(){



        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCity(@PathVariable String id){
        City result =getCityById(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City newCity) {
        newCity.setCreateDate(new Date());
        cities.add(newCity);
        return new ResponseEntity<>(newCity,HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Void> updateCity(@PathVariable String id, @RequestBody City newCity){
        City oldCity = getCityById(id);
        oldCity.setName(newCity.getName());
        oldCity.setCreateDate(new Date());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private City getCityById(String id){
        return cities.stream()
                .filter(city -> city.getId().equals(id))
                .findFirst()
                .orElseThrow(()->new RuntimeException("City Not Found"));
    }
}
