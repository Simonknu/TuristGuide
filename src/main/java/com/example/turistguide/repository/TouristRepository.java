package com.example.turistguide.repository;


import com.example.turistguide.model.Attraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private final List<Attraction> attractions;





    public TouristRepository(){
        attractions = new ArrayList<>();
        Attraction greatWallOfChina = new Attraction("Copenhagen Zoo","Big description", "Biggest handmade structure in history", "CopenhagenZoo.jpg");
        Attraction eiffelTower = new Attraction("Den Blå Planet","adwkjbakwjbd", "Big tower in Paris", "DenBlaPlanet.jpg");
        Attraction tajMahal = new Attraction("Amalienborg Palace","akhdawoihdawoinb", "Big monument in India", "AmalienBorg.jpg");
        attractions.add(greatWallOfChina);
        attractions.add(eiffelTower);
        attractions.add(tajMahal);

    }


    public List<Attraction> getAllTouristAttractions() {
        return new ArrayList<> (attractions);
    }


    public Attraction getTouristAttractionByName(String name){
        for(Attraction ta : attractions){
            if (ta.getName().equalsIgnoreCase(name)){
                return ta;
            }
        }
        return null;
    }

    public Attraction addTouristAttraction(Attraction attraction){
        attractions.add(attraction);
        return attraction;
    }

    public Attraction deleteTouristAttraction(Attraction attraction){
        attractions.remove(attraction);
        return attraction;
    }
}
