package org.vilojona;

import io.quarkus.logging.Log;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertFalse;

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
        // Using llama2 model we can check if the response contains 'Barcelona', but not with tinyllama
        assertFalse(response.isEmpty(), "Response should not be empty");
    }
}
