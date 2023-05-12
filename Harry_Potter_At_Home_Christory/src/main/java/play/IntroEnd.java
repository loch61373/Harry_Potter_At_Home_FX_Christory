package play;

import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.effect.DropShadow;


public class IntroEnd {

    private Wizard wizard;

    public IntroEnd(Wizard wizard) {
        this.wizard = wizard;
    }

    public void start(Stage primaryStage) {
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);

        try {
            Image background = new Image(getClass().getResource("/intro2.png").toExternalForm());
            BackgroundImage backgroundImage = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(500, 500, false, false, false, true));
            root.setBackground(new Background(backgroundImage));
        } catch (NullPointerException e) {
            System.err.println("error: .");
        }

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setOffsetX(2);
        dropShadow.setOffsetY(2);

        Label sortingHatLabel = new Label("Interesting ! Now, let's designate your house and your wand !");
        sortingHatLabel.setEffect(dropShadow);
        sortingHatLabel.setTextFill(Color.WHITE);
        sortingHatLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Label nameLabel = new Label("Name: " + wizard.getName());
        nameLabel.setEffect(dropShadow);
        nameLabel.setTextFill(Color.WHITE);
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        Label houseLabel = new Label("House: " + wizard.getHouse());
        houseLabel.setEffect(dropShadow);
        houseLabel.setTextFill(Color.WHITE);
        houseLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        Label wandLabel = new Label("Wand: " + wizard.getWand().getSize() + " inch, " + wizard.getWand().getCore().getName() + " core");
        wandLabel.setEffect(dropShadow);
        wandLabel.setTextFill(Color.WHITE);
        wandLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        Label petLabel = new Label("Pet: " + wizard.getPet());
        petLabel.setEffect(dropShadow);
        petLabel.setTextFill(Color.WHITE);
        petLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));


        nameLabel.setVisible(false);
        houseLabel.setVisible(false);
        wandLabel.setVisible(false);
        petLabel.setVisible(false);

        Button startPlayingButton = new Button("START PLAYING");
        startPlayingButton.setEffect(dropShadow);
        startPlayingButton.setTextFill(Color.WHITE);
        startPlayingButton.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        startPlayingButton.setStyle("-fx-background-color: #2b7de5; -fx-text-fill: white;");
        startPlayingButton.getStyleClass().add("blue-button");
        startPlayingButton.setVisible(false);
        startPlayingButton.setOnAction(event -> {
            primaryStage.hide(); // Hide the current window

            // Create a new instance of Level1GUI and show it
            Level12 level1GUI = new Level12(wizard);
            level1GUI.start(new Stage());
        });

        root.getChildren().addAll(sortingHatLabel, nameLabel, houseLabel, wandLabel, petLabel, startPlayingButton);

        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        // Show the wizard's information after 5 seconds
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            sortingHatLabel.setVisible(false);
            nameLabel.setVisible(true);
            houseLabel.setVisible(true);
            wandLabel.setVisible(true);
            petLabel.setVisible(true);
            startPlayingButton.setVisible(true);
        });
        pause.play();
    }
}
