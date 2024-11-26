package org.vilojona;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


@QuarkusTest
class GreetingResourceTest {
           
    @Inject
    private PingPongService pingPongService;

    @Test
    void testFXMainControllerInteraction() {
        // Perform interaction and assertions
        String response = pingPongService.interaction("Barcelona");
        assertTrue(response.contains("Barcelona"), "Response should contain 'Barcelona'");
    }
}
