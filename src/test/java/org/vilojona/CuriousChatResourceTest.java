package org.vilojona;

import io.quarkus.logging.Log;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;

import org.junit.jupiter.api.Test;

// In order to test in Mac OS, we need to have the Ollama service running
// There's a docker problem with MacOS, so we need to run the service in the host
@QuarkusTest
class CuriousChatResourceTest {
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
        // Using llama2 model we can check if the response contains 'Barcelona', but not
        // with tinyllama
        assertFalse(response.isEmpty(), "Response should not be empty");
    }

    @Test
    @ActivateRequestContext
    void testChatEndpoint() {
        given()
                .when()
                .body("Barcelona")
                .contentType(ContentType.TEXT)
                .post("/chat/3")
                .then()
                .statusCode(200)
                .contentType(ContentType.TEXT)
                .body(not(empty()))
                .body(org.hamcrest.Matchers.stringContainsInOrder("Question:", "Answer:", "Question:", "Answer:",
                        "Question:", "Answer:"));

    }

    @Test
    public void dummy() {
        int a = 1;
        int b = 2;
        int c = 3;
        int d = 4;
        int f = 5;
        int g = 6;
        int h = 7;
        int i = 8;
        int j = 9;
        int k = 10;
        int l = 11;
        int e = a + b + c + d + f + g + h + i + j + k + l;
        System.out.println(e);
    }
}
