package org.vilojona;

import io.quarkus.logging.Log;
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
        
        Log.infof("Wise service response: %s", response);
        assertTrue(response.contains("Barcelona"), "Response should contain 'Barcelona'");
    }
}
