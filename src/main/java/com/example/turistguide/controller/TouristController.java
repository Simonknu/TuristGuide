package com.example.turistguide.controller;

import org.springframework.ui.Model;
import com.example.turistguide.model.Attraction;
import com.example.turistguide.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class TouristController {
    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }


    @GetMapping("/")
    public String getAllAttractions(Model model) {
        List<Attraction> attractions = touristService.getAllTouristAttractions();
        model.addAttribute("attractions", attractions);
        return "index";
    }

    @GetMapping("/attractionList")
    public String getAttractionsList(Model model) {
        List<Attraction> attractions = touristService.getAllTouristAttractions();
        model.addAttribute("attractions", attractions);
        return "attractionList";
    }

    @GetMapping("/{name}/tags")
    public String getAttractionTags(String name, Model model) {
        Attraction attraction = touristService.getAttractionByName(name);
        model.addAttribute("attraction", attraction);
        return "tags";

    }

    @GetMapping("/addGet")
    public String addAttractionGetMethod(Model model) {
        List<String> tags = touristService.getTags();
        model.addAttribute("tags", tags);
        List<String> cities = touristService.getCities();
        model.addAttribute("cities", cities);
        return "addAttraction";
    }

    @PostMapping("/addPost")
    public String addAttractionPostMethod( String name,
                                           String description,
                                           String city,
                                          String tags) {
        List<String> tagList = new ArrayList<>();
        if (tags != null) {
            tagList = Arrays.asList(tags.split("\\s*,\\s*"));
        }
        touristService.createAttraction(name, description, city, tagList);


        return "redirect:/";
    }

    @PostMapping("/{name}/delete")
    public String deleteAttraction(String name) {
        Attraction deleteAttraction = touristService.getAttractionByName(name);
        touristService.deleteTouristAttraction(deleteAttraction);


        return "redirect:/attractionList";
    }

    @GetMapping("/{name}/update")
    public String updateAttractionGetMethod(String name, Model model) {
        Attraction updateAttraction = touristService.getAttractionByName(name);
        List<String> tagsRepository = touristService.getTags();
        List<String> selectedTags = touristService.getAttractionByName(name).getTags();
        model.addAttribute("tagsRepository", tagsRepository);
        model.addAttribute("attraction", updateAttraction);
        model.addAttribute("selectedTags", selectedTags);
        List<String> cities = touristService.getCities();
        model.addAttribute("cities", cities);
        return "updateAttraction";
    }

    @PostMapping("/{name}/saveUpdate")
    public String updateAttractionPostMethod( String newName,
                                              String description,
                                              String city,
                                              String tags,
                                              String name, Model model) {
        List<String> tagList = Arrays.asList(tags.split("\\s*,\\s*"));
touristService.updateAttraction(name, newName, description, city, tagList);
        List<Attraction> attractions = touristService.getAllTouristAttractions();
        model.addAttribute("attractions", attractions);


return "attractionList";
    }


}


