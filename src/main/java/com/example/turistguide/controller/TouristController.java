package com.example.turistguide.controller;

import org.springframework.ui.Model;
import com.example.turistguide.model.Attraction;
import com.example.turistguide.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class TouristController {
    private TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }


    @GetMapping("/search")
    public String searchTouristAttraction(@RequestParam (value="name", defaultValue ="Pyramid of Giza") String name, Model model){
        Attraction searchAttraction = touristService.getAttractionByName(name);
        model.addAttribute("attraction", searchAttraction);
        return "attractions";
    }

    @GetMapping("/")
    public String getAllAttractions(Model model){
        List<Attraction> attractions = touristService.getAllTouristAttractions();
        model.addAttribute("attractions", attractions);
        return "index";
    }

}


