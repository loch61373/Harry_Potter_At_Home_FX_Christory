package play;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Winning extends Application {
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        try {
            Image background = new Image(getClass().getResource("/winning.png").toExternalForm());
            BackgroundImage backgroundImage = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(500, 500, false, false, false, true));
            root.setBackground(new Background(backgroundImage));
        } catch (NullPointerException e) {
            System.err.println("Background image not found.");
        }
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setOffsetX(2);
        dropShadow.setOffsetY(2);

        Label winningLabel = new Label("Congratulations! You won the game!");
        Button closeButton = new Button("Close");
        Button restartButton = new Button("Restart");
        winningLabel.setEffect(dropShadow);
        winningLabel.setTextFill(Color.WHITE);

        closeButton.setOnAction(event -> {
            primaryStage.close();
            System.exit(0);
        });

        restartButton.setOnAction(event -> {
            primaryStage.close();
            Main gameGUI = new Main();
            gameGUI.start(new Stage());
        });

        root.getChildren().addAll(winningLabel, closeButton, restartButton);

        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
