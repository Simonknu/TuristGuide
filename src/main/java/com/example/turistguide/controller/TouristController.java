package com.example.turistguide.controller;

import com.example.turistguide.model.Attraction;
import com.example.turistguide.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/attractions")
public class TouristController {
    private TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }


    @GetMapping("")
    public ResponseEntity<List<Attraction>> getAllTouristAttractions() {
        List<Attraction> touristAttractions = touristService.getAllTouristAttractions();
        return new ResponseEntity<>(touristAttractions, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Attraction> getTouristAttractionByName(@PathVariable String name) {
        Attraction touristAttraction = touristService.getAttractionByName(name);
        return new ResponseEntity<>(touristAttraction, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Attraction> addTouristAttraction(@RequestBody Attraction attraction){
        Attraction newAttraction = touristService.addTouristAttraction(attraction);
        return new ResponseEntity<>(newAttraction,  HttpStatus.CREATED);

    }
}


