package org.vilojona;

import io.quarkiverse.fx.views.FxView;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@FxView("app")
@Dependent
public class FXMainController {

    @FXML
    private VBox chatBox;    
    
    @FXML
    private HBox hBox;

    @FXML
    private TextField messageField;
    
    @FXML
    private TextField numberField;

    @FXML
    private ScrollPane chatScrollPane;

    @FXML
    Parent root;

    @Inject
    CuriousService curiousService;    
    
    @Inject
    WiseService wiseService;

    @FXML
    public void initialize() {
        var stage = new Stage();
        var scene = new Scene(this.root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleSendAction() {
        var message = messageField.getText();
        var number = numberField.getText();
        if (!message.isEmpty() && !number.isEmpty()) {
            int times = Integer.parseInt(number);
            while (times-- > 0) {
                message = interaction(message);
            }
        }
    }
    public String interaction(String message) {
        var sendCurious = sendCurious(message);
        System.out.println("Message to curious sent");

        System.out.println("Message to wise sent");
        return sendWise(sendCurious);
    }

    @FXML
    private void handleClickMeAction(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleSendAction();
        }
    }

    private void showMessage(String message, boolean isQuestion) {
        var messageContainer = new HBox();

        var region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        region.setPrefWidth(20);
        messageContainer.getChildren().add(region);

        messageContainer.setAlignment(isQuestion ? Pos.TOP_LEFT : Pos.TOP_RIGHT);
        messageContainer.setPadding(new Insets(5));
        messageContainer.setMaxWidth(chatBox.getWidth() - 20);
        
        String color = isQuestion ? "lightgreen" : "yellow";
        messageContainer.setStyle("-fx-background-color: " + color + "; -fx-background-radius: 10;");

        var messageText = new Label(message);
        messageText.setWrapText(true);
        messageContainer.getChildren().add(messageText);

        chatBox.getChildren().add(messageContainer);
        messageField.clear();
        chatScrollPane.setVvalue(1.0); // Scroll to the bottom
    }
    
    @ActivateRequestContext
    public String sendCurious(String topic) {
        var question = curiousService.chat(topic);
        showMessage(question, true);
        return question;
    }

    @ActivateRequestContext
    public String sendWise(String topic) {
        var response = wiseService.chat(topic);
        showMessage(response, false);
        return response;
    }


}
