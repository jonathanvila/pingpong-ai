package org.vilojona;

import io.quarkus.logging.Log;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


@QuarkusTest
class GreetingResourceTest {
    @Inject
    CuriousService curiousService;

    @Inject
    WiseService wiseService;

          
    @Test
    @ActivateRequestContext
    void testFXMainControllerInteraction() {

        // Perform interaction and assertions
        var curiousAnswer = curiousService.chat("Barcelona");
        var response = wiseService.chat(curiousAnswer);
        
        Log.infof("Wise service response: %s", response);
        assertTrue(response.contains("Barcelona"), "Response should contain 'Barcelona'");
    }
}
