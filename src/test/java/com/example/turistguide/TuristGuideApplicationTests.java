package com.example.turistguide;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("ci")
class TuristGuideApplicationTests {

    @Test
    @Disabled
    void contextLoads() {
    }

}
