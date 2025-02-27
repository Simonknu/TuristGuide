package com.example.turistguide.repository;


import com.example.turistguide.model.Attraction;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private final List<Attraction> attractions;





    public TouristRepository(){
        attractions = attractions();

    }

    public List<Attraction> attractions(){
        Attraction copenhagenZoo = new Attraction("Copenhagen Zoo", "Zoo", "Copenhagen", List.of("Nature"));
        Attraction blaPlanet = new Attraction("Den Bla Planet", "Aquarium", "Copenhagen", List.of("Nautre"));
        Attraction amalienBorg = new Attraction("Amalien Borg", "Castle", "Copenhagen",  List.of("Free"));
        return new ArrayList<>(List.of(
                copenhagenZoo,
                blaPlanet,
                amalienBorg
        ));
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

    public void createAttraction(String name, String description, String city, List<String> tags){
        Attraction newAttraction = new Attraction(name, description, city, tags);
        attractions.add(newAttraction);
    }
}
