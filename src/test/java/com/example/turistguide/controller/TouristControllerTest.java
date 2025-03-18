package com.example.turistguide.controller;

import com.example.turistguide.model.Attraction;
import com.example.turistguide.service.TouristService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TouristController.class)
class TouristControllerTest {


    private List<Attraction> mockAttractionList;


    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TouristService touristService;


    @BeforeEach
    void setUp() {
        Attraction attraction1 = new Attraction("name", "description", "city2", List.of("tag1", "tag2"));
        Attraction attraction2 = new Attraction("name1", "des1", "city1", List.of("tag3", "tag4"));
        mockAttractionList = List.of(attraction1, attraction2);

    }

    @AfterEach
    void tearDown(){

    }

    @Test
    void  getAttractionsList() throws Exception {
        when(touristService.getAllTouristAttractions()).thenReturn(mockAttractionList);
        mockMvc.perform(get("/attractionList"))
                .andExpect(status().isOk())
                .andExpect(view().name("attractionList"))
                .andExpect(model().attributeExists("attractions"))
                .andExpect(model().attribute("attractions", mockAttractionList));

        verify(touristService, times(1)).getAllTouristAttractions();

    }

    @Test
    void testAddPost() throws Exception {
        mockMvc.perform(post("/addPost")
                    .param("name", "name1")
                    .param("description", "des1")
                    .param("city", "city1")
                    .param("tags", "tag1,tag2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));

        verify(touristService, times(1))
                .createAttraction(eq("name1"), eq("des1"), eq("city1"), eq(List.of("tag1", "tag2")));
    }
}