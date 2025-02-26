package com.example.turistguide.controller;

import org.springframework.ui.Model;
import com.example.turistguide.model.Attraction;
import com.example.turistguide.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Attr;

import java.util.List;

@Controller
@RequestMapping("/")
public class TouristController {
    private TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }


    @GetMapping("/search")
    public String searchTouristAttraction(@RequestParam (value="name") String name, Model model){
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

    @GetMapping("/attractionList")
    public String getAttractionsList(Model model){
        List<Attraction> attractions = touristService.getAllTouristAttractions();
        model.addAttribute("attractions", attractions);
        return "attractionList";
    }

    @GetMapping("/{name}/tags")
    public String getAttractionTags(@RequestParam (value="name") String name, Model model){
        Attraction attraction = touristService.getAttractionByName(name);
        model.addAttribute("attraction", attraction);
        return "tags";

    }

    @GetMapping("/addGet")
    public String addAttractionGetMethod(){
        return "addAttraction";
    }

    @PostMapping
    public String addAttraction(@RequestParam (value="name") String name, Model model ){
        Attraction addAttraction = touristService.getAttractionByName(name);
        return "index";
    }
}


