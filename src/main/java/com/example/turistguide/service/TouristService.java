package com.example.turistguide.service;

import com.example.turistguide.model.Attraction;
import com.example.turistguide.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {
    private final TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository){
        this.touristRepository = touristRepository;
    }

    public List<Attraction> getAllTouristAttractions(){
        return touristRepository.getAllTouristAttractions();
    }

    public List<String> getTags(){
        return touristRepository.getTags();
    }

    public List<String> getCities(){
        return touristRepository.getCities();
    }

    public Attraction getAttractionByName(String name){
        return touristRepository.getTouristAttractionByName(name);
    }

    public void deleteTouristAttraction(Attraction attraction){
         touristRepository.deleteTouristAttraction(attraction);
    }

    public void createAttraction(String name, String description, String city, List<String> tags){
        touristRepository.createAttraction(name, description, city, tags);

    }

    public void updateAttraction(String name, String newName, String description, String city, List<String> tags){
        touristRepository.updateAttraction(name, newName, description, city, tags);
    }
}
