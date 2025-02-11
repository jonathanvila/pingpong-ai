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
import org.eclipse.jdt.annotation.NonNull;
import java.util.List;

@QuarkusMain
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

    public class FPTest {
        public static void main(String[] args) {
            new Thread("name") {
                {
                    setDaemon(true);
                    start();
                }
                @Override	public void run()  {
                    return;
                }
            };
        }
    }


    // "field" is marked "@NonNull" but is not initialized in this constructor
    public class InnerClass {
        //@NonNull String thirdField;

        List<@NonNull String> field;

        public InnerClass() {
            field = List.of("a", "b", "c", null);
        }
    }

}

Analysing 7 files (ID 0e3c73c3-0b30-4997-965e-ef1c84a2e9b4)...
        [2025-02-10T10:07:26.413] [SonarLint Server RPC request executor] INFO sonarlint - Triggering analysis with configuration: [
baseDir: /Users/jonathan.vila/workspace/pingpong-ai
extraProperties: {sonar.java.target=22,
        activeRules: [14 kubernetes, 214 python, 479 java, 46 Web, 14 xml, 109 kotlin, 155 php, 7 terraform, 29 secrets, 7 cloudformation, 26 docker, 28 ruby]
        inputFiles: [
        file:///Users/jonathan.vila/workspace/pingpong-ai/src/main/java/org/vilojona/Main.java (UTF-8)
        file:///Users/jonathan.vila/workspace/pingpong-ai/README.md (UTF-8)
        file:///Users/jonathan.vila/workspace/pingpong-ai/pom.xml (UTF-8)
        file:///Users/jonathan.vila/workspace/pingpong-ai/src/main/java/org/vilojona/CuriousChatResource.java (UTF-8)
        file:///Users/jonathan.vila/workspace/pingpong-ai/src/main/java/org/vilojona/WiseService.java (UTF-8)
        file:///Users/jonathan.vila/workspace/pingpong-ai/src/main/java/org/vilojona/CuriousService.java (UTF-8)
        file:///Users/jonathan.vila/workspace/pingpong-ai/src/test/java/org/vilojona/CuriousChatResourceTest.java (UTF-8) [test]
        ]
        ]

        [2025-02-10T10:07:26.74] [sonarlint-analysis-engine] INFO sonarlint - Index files
        [2025-02-10T10:07:26.741] [Report about progress of file indexation] INFO sonarlint - 7 files indexed
        [2025-02-10T10:07:26.868] [sonarlint-analysis-engine] WARN sonarlint - No workDir in SonarLint
        [2025-02-10T10:07:26.868] [sonarlint-analysis-engine] INFO sonarlint - Configured Java source version (sonar.java.source): 22, preview features enabled (sonar.java.enablePreview): false
        [2025-02-10T10:07:26.876] [sonarlint-analysis-engine] INFO sonarlint - Server-side caching is not enabled. The Java analyzer will not try to leverage data from a previous analysis.
        [2025-02-10T10:07:26.877] [Report about progress of Java AST analyzer] INFO sonarlint - 4 source files to be analyzed
        [2025-02-10T10:07:27.245] [sonarlint-analysis-engine] INFO sonarlint - The Java analyzer cannot skip unchanged files in this context. A full analysis is performed for all files.
        [2025-02-10T10:07:27.833] [Report about progress of Java AST analyzer] INFO sonarlint - 4/4 source files have been analyzed
        [2025-02-10T10:07:27.834] [sonarlint-analysis-engine] INFO sonarlint - Did not optimize analysis for any files, performed a full analysis for all 4 files.
        [2025-02-10T10:07:27.834] [Report about progress of Java AST analyzer] INFO sonarlint - 1 source file to be analyzed
        [2025-02-10T10:07:27.927] [Report about progress of Java AST analyzer] INFO sonarlint - 1/1 source file has been analyzed
        [2025-02-10T10:07:27.927] [sonarlint-analysis-engine] INFO sonarlint - Did not optimize analysis for any files, performed a full analysis for all 1 files.
        [2025-02-10T10:07:27.927] [sonarlint-analysis-engine] INFO sonarlint - No "Generated" source files to scan.
        [2025-02-10T10:07:27.928] [Report about progress of XML Analyzer] INFO sonarlint - 1 source file to be analyzed
        [2025-02-10T10:07:27.932] [Report about progress of XML Analyzer] INFO sonarlint - 1/1 source file has been analyzed
        [2025-02-10T10:07:27.932] [Progress of the Java analysis] INFO sonarlint - 0 source files to be analyzed
        [2025-02-10T10:07:27.932] [Progress of the Java analysis] INFO sonarlint - 0/0 source files have been analyzed
        [2025-02-10T10:07:27.935] [Progress of the Docker analysis] INFO sonarlint - 0 source files to be analyzed
        [2025-02-10T10:07:27.935] [Progress of the Docker analysis] INFO sonarlint - 0/0 source files have been analyzed
        [2025-02-10T10:07:27.935] [sonarlint-analysis-engine] INFO sonarlint - Available processors: 12
        [2025-02-10T10:07:27.935] [sonarlint-analysis-engine] INFO sonarlint - Using 12 threads for analysis.
        [2025-02-10T10:07:27.941] [sonarlint-analysis-engine] INFO sonarlint - Analyzing all except non binary files
        [2025-02-10T10:07:27.943] [Progress of the text and secrets analysis] INFO sonarlint - 7 source files to be analyzed
        [2025-02-10T10:07:27.945] [Progress of the text and secrets analysis] INFO sonarlint - 7/7 source files have been analyzed
        [2025-02-10T10:07:27.946] [sonarlint-analysis-engine] INFO sonarlint - Analysis detected 7 issues and 0 Security Hotspots in 1627ms
