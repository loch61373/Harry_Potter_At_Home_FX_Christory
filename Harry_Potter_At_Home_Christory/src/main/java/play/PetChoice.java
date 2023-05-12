package play;

import items.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Random;

public class PetChoice {

    public void start(Stage primaryStage, String wizardName) {
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

        Label introtoo = new Label("Your profile has been created !");
        introtoo.setEffect(dropShadow);
        introtoo.setTextFill(Color.WHITE);
        introtoo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        Label pet = new Label("Now, what type of pet do you want ?");
        pet.setEffect(dropShadow);
        pet.setTextFill(Color.WHITE);
        pet.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        ComboBox<Pet> petComboBox = new ComboBox<>();
        petComboBox.getItems().addAll(Pet.values());
        Button initButton = new Button("NEXT");
        initButton.setEffect(dropShadow);
        initButton.setTextFill(Color.WHITE);
        initButton.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        initButton.setStyle("-fx-background-color: #2b7de5; -fx-text-fill: white;");
        initButton.getStyleClass().add("blue-button");

        root.getChildren().addAll(introtoo, pet, petComboBox, initButton);

        Scene scene = new Scene(root, 800, 500);

        primaryStage.setScene(scene);
        primaryStage.show();
        initButton.setOnAction(event -> {
            Pet chosenPet = petComboBox.getValue();
            if (chosenPet != null) {
                Wizard wizard = initWizard(wizardName, chosenPet);
                Stage wizardStage = new Stage();
                IntroEnd wizardGUI = new IntroEnd(wizard);
                wizardGUI.start(wizardStage);
                primaryStage.close();
            } else {

                Label errorLabel = new Label("Perfect, now choose your pet !");
                errorLabel.setEffect(dropShadow);
                errorLabel.setTextFill(Color.WHITE);
                root.getChildren().add(errorLabel);
            }
        });
    }

    private Wizard initWizard(String name, Pet chosenPet) {
        House house = SortingHat.randomHouse();
        Core[] cores = Core.values();
        int randomCoreIndex = new Random().nextInt(cores.length);
        Core randomCore = cores[randomCoreIndex];
        int randomSize = new Random().nextInt(15) + 5;
        Wand wand = new Wand(randomCore, randomSize);
        int healthPoints = 100;

        Wizard wizard = new Wizard(healthPoints, name, house, wand, chosenPet);
        return wizard;
    }
}
