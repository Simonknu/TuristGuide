package com.example.turistguide.controller;


import com.example.turistguide.model.Attraction;
import com.example.turistguide.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.util.List;

@RestController
@RequestMapping("api/attractions")
public class TouristRestController {

    private final TouristService touristService;

    public TouristRestController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping("")
    public List<Attraction> getAllTouristAttractions() {
        return touristService.getAllTouristAttractions();
    }

    @GetMapping("/{name}")
    public Attraction getTouristAttractionByName(@PathVariable String name) {
        return touristService.getAttractionByName(name);
    }

    @PostMapping("/add")
    public ResponseEntity<Attraction> addTouristAttraction(@RequestBody Attraction attraction){
        Attraction newAttraction = touristService.addTouristAttraction(attraction);
        return new ResponseEntity<>(newAttraction, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Attraction> deleteAttraction(@PathVariable String name){
        Attraction attractionToDelete = touristService.getAttractionByName(name);
        touristService.deleteTouristAttraction(attractionToDelete);
        return new ResponseEntity<>(attractionToDelete, HttpStatus.OK);
    }

}
