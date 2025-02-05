package com.example.turistguide.repository;


import com.example.turistguide.model.Attraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private final List<Attraction> attractions = new ArrayList<>();





    public TouristRepository(){
        Attraction greatWallOfChina = new Attraction("Great Wall of China", "Biggest handmade structure in history");
        Attraction eiffelTower = new Attraction("Eiffel Tower", "Big tower in Paris");
        Attraction example = new Attraction("example", "example");
        attractions.add(greatWallOfChina);
        attractions.add(eiffelTower);
        attractions.add(example);

    }


    public List<Attraction> getAllTouristAttractions() {
        return attractions;
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
}
