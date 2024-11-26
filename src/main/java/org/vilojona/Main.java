package org.vilojona;

import io.quarkiverse.fx.FxApplication;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main implements QuarkusApplication {
    private static final String SERVER_URL = "http://localhost:8080/chat";

    @Override
    public int run(final String... args) {
        Application.launch(FxApplication.class, args);
        return 0;
    }

    //@Override
    public void start(Stage primaryStage) {
        // Create UI components
        TextField questionField = new TextField();
        questionField.setPromptText("Enter your question here...");
        
        Button submitButton = new Button("Submit");
        
        ListView<String> chatView = new ListView<>();
        
        // Handle button click
        submitButton.setOnAction(e -> {
            String question = questionField.getText();
            if (!question.isEmpty()) {
                chatView.getItems().add("You: " + question);
                String answer = sendQuestionToServer(question);
                chatView.getItems().add("Bot: " + answer);
                questionField.clear();
            }
        });
        
        // Layout
        VBox layout = new VBox(10, questionField, submitButton, chatView);
        
        // Scene
        Scene scene = new Scene(layout, 400, 300);
        
        // Stage
        primaryStage.setTitle("Chat Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String sendQuestionToServer(String question) {
        Client client = ClientBuilder.newClient();
        Response response = client.target(SERVER_URL)
                .request(MediaType.TEXT_PLAIN)
                .post(Entity.text(question));
        return response.readEntity(String.class);
    }
}