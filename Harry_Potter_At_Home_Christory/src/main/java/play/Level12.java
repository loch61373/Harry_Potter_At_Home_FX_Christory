package play;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Level12 {

    private Wizard wizard;
    private Level1 level1;

    public Level12(Wizard wizard) {
        this.wizard = wizard;
        this.level1 = new Level1(wizard); // Pass the wizard object when creating a new Level1 object
    }

    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        try {
            Image background = new Image(getClass().getResource("/trollbattle.png").toExternalForm());
            BackgroundImage backgroundImage = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(500, 500, false, false, false, true));
            root.setBackground(new Background(backgroundImage));
        } catch (NullPointerException e) {
            System.err.println("Background image not found.");
        }
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setOffsetX(2);
        dropShadow.setOffsetY(2);

        Label intro = new Label("Welcome " + wizard.getName() + " at " + level1.getLocation() + "!");
        intro.setEffect(dropShadow);
        intro.setTextFill(Color.WHITE);
        intro.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Label enemyLabel = new Label("Here is your first ennemy , " + level1.getEnemy().getName() + " !");
        enemyLabel.setEffect(dropShadow);
        enemyLabel.setTextFill(Color.WHITE);
        enemyLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Button startBattleButton = new Button("START BATTLE");
        startBattleButton.setEffect(dropShadow);
        startBattleButton.setTextFill(Color.WHITE);
        startBattleButton.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        startBattleButton.setStyle("-fx-background-color: #2b7de5; -fx-text-fill: white;");
        startBattleButton.getStyleClass().add("blue-button");
        startBattleButton.setOnAction(event -> {
            primaryStage.close();
            Level level = new Level1(wizard); // Pass the wizard object when creating a new Level1 object

        });

        root.getChildren().addAll(intro, enemyLabel, startBattleButton);

        Scene scene = new Scene(root, 800, 500);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
