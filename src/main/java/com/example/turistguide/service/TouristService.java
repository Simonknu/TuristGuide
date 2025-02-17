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

    public Attraction getAttractionByName(String name){
        return touristRepository.getTouristAttractionByName(name);
    }

    public Attraction addTouristAttraction(Attraction attraction){
        return touristRepository.addTouristAttraction(attraction);
    }
    public Attraction deleteTouristAttraction(Attraction attraction){
        return touristRepository.deleteTouristAttraction(attraction);
    }
}
