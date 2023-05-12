package play;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        try {
            Image background = new Image(getClass().getResource("/intro.jpg").toExternalForm());
            BackgroundImage backgroundImage = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(500, 500, false, false, false, true));
            root.setBackground(new Background(backgroundImage));
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setOffsetX(2);
        dropShadow.setOffsetY(2);

        Label intro = new Label("Hello young Wizard !");
        intro.setEffect(dropShadow);
        intro.setTextFill(Color.WHITE);
        intro.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Label name = new Label("Please enter your name : ");
        name.setEffect(dropShadow);
        name.setTextFill(Color.WHITE);
        name.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        TextField nameField = new TextField();
        nameField.setStyle("-fx-background-color: #F5F5F5; -fx-text-fill: #4D4D4D; -fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 5px;");
        nameField.setMaxWidth(300);
        
        Button startButton = new Button("START GAME");
        startButton.setEffect(dropShadow);
        startButton.setTextFill(Color.WHITE);
        startButton.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        startButton.setStyle("-fx-background-color: #2b7de5; -fx-text-fill: white;");
        startButton.getStyleClass().add("blue-button");



        root.getChildren().addAll(intro, name, nameField, startButton);
        Scene scene = new Scene(root, 800, 500);

        primaryStage.setScene(scene);
        primaryStage.show();
        startButton.setOnAction(event -> {
            String wizardName = nameField.getText();
            Stage wizardInitStage = new Stage();
            PetChoice PetChoice = new PetChoice();
            PetChoice.start(wizardInitStage, wizardName);
            primaryStage.close();
        });


    }

    public static void main(String[] args) {
        launch(args);
    }
}
