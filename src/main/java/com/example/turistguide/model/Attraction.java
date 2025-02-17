package com.example.turistguide.model;

public class Attraction {
    private String name;
    private String description;
    private String smallDescription;
    private String imageUrl;



    public Attraction(){

    }

    public Attraction(String name, String description,String smallDescription, String imageUrl){
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.smallDescription = smallDescription;
    }

    public void setSmallDescription(String smallDescription) {
        this.smallDescription = smallDescription;
    }

    public String getSmallDescription() {
        return smallDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
