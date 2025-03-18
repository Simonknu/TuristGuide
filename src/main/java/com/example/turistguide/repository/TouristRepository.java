package com.example.turistguide.repository;


import com.example.turistguide.model.Attraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private List<Attraction> attractions;
    private List<String> cities;
    private List<String> tags;


    public TouristRepository() {
        this.attractions = attractions();
        this.cities = cities();
        this.tags = tags();

    }

    public List<Attraction> attractions() {
        Attraction copenhagenZoo = new Attraction("Copenhagen Zoo", "Zoo", "Copenhagen", List.of("Nature"));
        Attraction blaPlanet = new Attraction("Den Bla Planet", "Aquarium", "Copenhagen", List.of("Nautre"));
        Attraction amalienBorg = new Attraction("Amalien Borg", "Castle", "Copenhagen", List.of("Free"));
        return new ArrayList<>(List.of(
                copenhagenZoo,
                blaPlanet,
                amalienBorg
        ));
    }

    public List<String> cities() {
        return new ArrayList<>(List.of("Copenhagen", "Aarhus", "Odense", "Aalborg"));
    }

    public List<String> tags() {
        return new ArrayList<>(List.of("Kids Friendly", "Free", "Museum", "Monument", "Art", "Nature"));
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public List<String> getCities() {
        return cities;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Attraction> getAllTouristAttractions() {
        return new ArrayList<>(attractions);
    }


    public Attraction getTouristAttractionByName(String name) {
        for (Attraction ta : attractions) {
            if (ta.getName().equalsIgnoreCase(name)) {
                return ta;
            }
        }
        return null;
    }

    public Attraction addTouristAttraction(Attraction attraction) {
        attractions.add(attraction);
        return attraction;
    }

    public void deleteTouristAttraction(Attraction attraction) {
        attractions.remove(attraction);
    }

    public void createAttraction(String name, String description, String city, List<String> tags) {
        Attraction newAttraction = new Attraction(name, description, city, tags);
        attractions.add(newAttraction);
    }

    public void updateAttraction(String name, String newName, String description, String city, List<String> tags) {
        getTouristAttractionByName(name).setName(newName);
        getTouristAttractionByName(name).setDescription(description);
        getTouristAttractionByName(name).setTags(tags);
        getTouristAttractionByName(name).setCity(city);
    }
}
