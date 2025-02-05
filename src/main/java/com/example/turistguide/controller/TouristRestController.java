package com.example.turistguide.controller;


import com.example.turistguide.model.Attraction;
import com.example.turistguide.service.TouristService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/attractions")
public class TouristRestController {

    private final TouristService touristService;

    public TouristRestController(TouristService touristService) {
        this.touristService = touristService;
    }

    // ✅ Return all attractions as JSON
    @GetMapping("")
    public List<Attraction> getAllTouristAttractions() {
        return touristService.getAllTouristAttractions();
    }

    // ✅ Return one attraction by name as JSON
    @GetMapping("/{name}")
    public Attraction getTouristAttractionByName(@PathVariable String name) {
        return touristService.getAttractionByName(name);
    }

    // ✅ Add new attraction (POST request with JSON body)
    @PostMapping("/add")
    public Attraction addTouristAttraction(@RequestBody Attraction attraction) {
        return touristService.addTouristAttraction(attraction);
    }


}
