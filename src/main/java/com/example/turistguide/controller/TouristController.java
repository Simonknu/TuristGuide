package com.example.turistguide.controller;

import org.springframework.ui.Model;
import com.example.turistguide.model.Attraction;
import com.example.turistguide.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class TouristController {
    private TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }


    @GetMapping("/search")
    public String searchTouristAttraction(@RequestParam(value = "name") String name, Model model) {
        Attraction searchAttraction = touristService.getAttractionByName(name);
        model.addAttribute("attraction", searchAttraction);
        return "attractions";
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
    public String addAttractionPostMethod(@RequestParam(value = "name") String name,
                                          @RequestParam(value = "description") String description,
                                          @RequestParam(value = "city") String city,
                                          @RequestParam(value = "tags") String tags, Model model) {

        List<String> tagList = Arrays.asList(tags.split("\\s*,\\s*"));
        touristService.createAttraction(name, description, city, tagList);
        List<Attraction> attractions = touristService.getAllTouristAttractions();
        model.addAttribute("attractions", attractions);


        return "redirect:/index";
    }

    @PostMapping("/{name}/delete")
    public String deleteAttraction(@RequestParam(value = "name") String name, Model model) {
        Attraction deleteAttraction = touristService.getAttractionByName(name);
        touristService.deleteTouristAttraction(deleteAttraction);
        List<Attraction> attractions = touristService.getAllTouristAttractions();
        model.addAttribute("attractions", attractions);


        return "index";
    }

    @GetMapping("/{name}/update")
    public String updateAttractionGetMethod(@RequestParam(value = "name") String name, Model model) {
        Attraction updateAttraction = touristService.getAttractionByName(name);
        List<String> tagsRepository = touristService.getTags();
        model.addAttribute("tagsRepository", tagsRepository);
        model.addAttribute("attraction", updateAttraction);
        List<String> cities = touristService.getCities();
        model.addAttribute("cities", cities);
        return "updateAttraction";
    }

    @PostMapping("/{name}/saveUpdate")
    public String updateAttractionPostMethod(@RequestParam(value = "newName") String newName,
                                             @RequestParam(value = "description") String description,
                                             @RequestParam(value = "city") String city,
                                             @RequestParam(value = "tags") String tags,
                                             @RequestParam(value = "name") String name, Model model) {
        List<String> tagList = Arrays.asList(tags.split("\\s*,\\s*"));
touristService.updateAttraction(name, newName, description, city, tagList);
        List<Attraction> attractions = touristService.getAllTouristAttractions();
        model.addAttribute("attractions", attractions);


return "attractionList";
    }

}


