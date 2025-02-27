package com.example.turistguide.model;

import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.List;

public class Attraction {
    private String name;
    private String description;
    private List<String> tags;



    public Attraction(String name, String description, List<String> tags){
        this.name = name;
        this.description = description;
        this.tags = new ArrayList<>(tags);

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
