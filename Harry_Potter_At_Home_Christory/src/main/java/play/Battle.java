package play;

import items.Potion;
import items.Spell;
import items.Sword;
import javafx.animation.PauseTransition;
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
import javafx.util.Duration;

import java.util.Optional;
import java.util.Random;

public class Battle {
    private Button nextLevelButton;
    private Label missedSpellLabel;

    private Level level;
    private Wizard wizard;
    private Enemy enemy;
    private Spell spell;
    private Optional<Sword> sword;

    public Battle(Level level) {
        this.level = level;
        this.wizard = level.getWizard();
        this.enemy = level.getEnemy();
        this.spell = level.getSpell();
        this.sword = level.getSword();
        nextLevelButton = new Button("Next Level");
        nextLevelButton.setVisible(false);
        missedSpellLabel=new Label("You missed the spell!");
        missedSpellLabel.setVisible(false);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setOffsetX(2);
        dropShadow.setOffsetY(2);

        missedSpellLabel.setEffect(dropShadow);
        missedSpellLabel.setTextFill(Color.WHITE);

    }

    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        try {
            Image background = new Image(getClass().getResource("/battle.jpeg").toExternalForm());
            BackgroundImage backgroundImage = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(500, 500, false, false, false, true));
            root.setBackground(new Background(backgroundImage));
        } catch (NullPointerException e) {
            System.err.println("Background image not found.");
        }
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setOffsetX(2);
        dropShadow.setOffsetY(2);

        Label InvicibilityElixir = new Label("You used the Elixir of Invincibility, bad luck your enemy also benefits from it! You both gain some HP !");
        InvicibilityElixir.setVisible(false);
        InvicibilityElixir.setEffect(dropShadow);
        InvicibilityElixir.setTextFill(Color.WHITE);
        InvicibilityElixir.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        Label wizardHealthLabel = new Label("Your health: " + wizard.getHealthPoints());
        Label enemyHealthLabel = new Label("Enemy health: " + enemy.getHealthPoints());
        wizardHealthLabel.setEffect(dropShadow);
        wizardHealthLabel.setTextFill(Color.WHITE);
        wizardHealthLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        enemyHealthLabel.setEffect(dropShadow);
        enemyHealthLabel.setTextFill(Color.WHITE);
        enemyHealthLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        Button attackSpellButton = new Button("Attack with " + spell.getName());
        attackSpellButton.setEffect(dropShadow);
        attackSpellButton.setTextFill(Color.WHITE);
        attackSpellButton.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        attackSpellButton.setStyle("-fx-background-color: #2b7de5; -fx-text-fill: white;");
        attackSpellButton.getStyleClass().add("blue-button");
        Label messageLabel = new Label();
        messageLabel.setVisible(false);

        attackSpellButton.setOnAction(event -> {
            attackEnemy(primaryStage, messageLabel);
            updateLabels(wizardHealthLabel, enemyHealthLabel);
        });


        Button usePotionButton = new Button("Use a healing potion");
        Button smallHealingPotionButton = new Button("Small healing potion");
        Button elixirOfInvincibilityButton = new Button("Elixir of Invincibility");
        smallHealingPotionButton.setVisible(false);
        usePotionButton.setEffect(dropShadow);
        usePotionButton.setTextFill(Color.WHITE);
        usePotionButton.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        usePotionButton.setStyle("-fx-background-color: #2b7de5; -fx-text-fill: white;");
        usePotionButton.getStyleClass().add("blue-button");

        smallHealingPotionButton.setEffect(dropShadow);
        smallHealingPotionButton.setTextFill(Color.WHITE);
        smallHealingPotionButton.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        smallHealingPotionButton.setStyle("-fx-background-color: #2b7de5; -fx-text-fill: white;");
        smallHealingPotionButton.getStyleClass().add("blue-button");

        elixirOfInvincibilityButton.setEffect(dropShadow);
        elixirOfInvincibilityButton.setTextFill(Color.WHITE);
        elixirOfInvincibilityButton.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        elixirOfInvincibilityButton.setStyle("-fx-background-color: #2b7de5; -fx-text-fill: white;");
        elixirOfInvincibilityButton.getStyleClass().add("blue-button");
        elixirOfInvincibilityButton.setVisible(false);

        usePotionButton.setOnAction(event -> {
            smallHealingPotionButton.setVisible(true);
            elixirOfInvincibilityButton.setVisible(true);
        });
        smallHealingPotionButton.setOnAction(event -> {
            Potion potion = new Potion(30);
            potion.use(wizard);
            updateLabels(wizardHealthLabel, enemyHealthLabel);
            level.getDisplay().printMessage("You used a small healing potion and regained " + potion.getHealAmount() + " HP. Your current HP is " + wizard.getHealthPoints() + ".");
            smallHealingPotionButton.setVisible(false);
            elixirOfInvincibilityButton.setVisible(false);
        });

        elixirOfInvincibilityButton.setOnAction(event -> {

                Potion potion = new Potion(50);
                enemy.heal(50);
                potion.use(wizard);
                enemyAttackWizard();
                updateLabels(wizardHealthLabel, enemyHealthLabel);
                InvicibilityElixir.setVisible(true); // Make the label visible
                // Hide the label after 3 seconds
                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> InvicibilityElixir.setVisible(false));
                pause.play();

            smallHealingPotionButton.setVisible(false);
            elixirOfInvincibilityButton.setVisible(false);
        });


        Button attackSwordButton = new Button("Attack with " + (sword.isPresent() ? sword.get().getName() : "Sword"));
// Show "use sword" button only for Gryffindor wizards in level 2
        attackSwordButton.setVisible(wizard.getHouse().equals("Gryffindor") && level.getLevelNumber() == 2);
        attackSwordButton.setOnAction(event -> {
            // Wizard attacks with sword
            if (wizard.getHouse().equals("Gryffindor")) {
                enemy.setHealthPoints(0);
                enemyDefeated(primaryStage);
            } else {
                if (sword.isPresent()) {
                    Sword gryffindorSword = sword.get();
                    enemy.takeDamage(gryffindorSword.getDamage());
                }
            }
            updateLabels(wizardHealthLabel, enemyHealthLabel);
        });

        root.getChildren().addAll(enemyHealthLabel, wizardHealthLabel, attackSpellButton, usePotionButton, smallHealingPotionButton, elixirOfInvincibilityButton, attackSwordButton, missedSpellLabel, messageLabel);

        Scene scene = new Scene(root, 800, 500);

        primaryStage.setScene(scene);
        primaryStage.show();
        nextLevelButton.setOnAction(event -> {
            primaryStage.close();
            int currentLevelNumber = level.getLevelNumber();
            Stage nextLevelStage = new Stage();

            switch (currentLevelNumber) {

            }
            primaryStage.close();
        });
    }
    private void enemyAttackWizard() {
        wizard.takeDamage(enemy.getDamage());
        level.getDisplay().printMessage("The " + enemy.getName() + " dealt " + enemy.getDamage() + " damage to you.");

        if (wizard.getHealthPoints() <= 0) {
            Stage gameOverStage = new Stage();
            new GameOver().start(gameOverStage);
        }
    }
    private void updateLabels(Label wizardHealthLabel, Label enemyHealthLabel) {
        wizardHealthLabel.setText("Your health: " + wizard.getHealthPoints());
        enemyHealthLabel.setText("Enemy health: " + enemy.getHealthPoints());
    }

    public void attackEnemy(Stage primaryStage, Label messageLabel) {
        Wizard wizard = level.getWizard();
        Enemy enemy = level.getEnemy();
        Spell spell = level.getSpell();

        Random random = new Random();
        int chance = random.nextInt(100) + 1;

        if (chance <= spell.getAccuracy()) {
            // Successful attack
            enemy.takeDamage((int) spell.getDamage());

            messageLabel.setText("You dealt " + spell.getDamage() + " damage to " + enemy.getName() + "!");
            messageLabel.setVisible(true);

            DropShadow dropShadow = new DropShadow();
            dropShadow.setColor(Color.BLACK);
            dropShadow.setOffsetX(2);
            dropShadow.setOffsetY(2);

            messageLabel.setEffect(dropShadow);
            messageLabel.setTextFill(Color.WHITE);

            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> messageLabel.setVisible(false));
            pause.play();

            if (enemy.getHealthPoints() <= 0) {
                // Enemy is defeated
                enemyDefeated(primaryStage);
            } else {
                // Enemy attacks the wizard
                wizard.takeDamage(enemy.getDamage());
                messageLabel.setText("The " + enemy.getName() + " dealt " + enemy.getDamage() + " damage to you.");
                messageLabel.setVisible(true);
                PauseTransition pause1 = new PauseTransition(Duration.seconds(3));
                pause1.setOnFinished(event -> messageLabel.setVisible(false));
                pause1.play();


                // Check if the wizard is dead
                if (wizard.getHealthPoints() <= 0) {
                    primaryStage.close();
                    new GameOver().start(new Stage());
                }
            }
        } else {
            missedSpellLabel.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> missedSpellLabel.setVisible(false));
            pause.play();
            // Enemy attacks the wizard even when the spell is missed
            enemyAttackWizard();
        }
        }


    public void enemyDefeated(Stage primaryStage) {

        if (level.getLevelNumber() == 7) {
            primaryStage.close();
            new Winning().start(new Stage());
        } else {
            Label victoryLabel = new Label("You won this battle! Let's go to the next level!");
            DropShadow dropShadow = new DropShadow();
            dropShadow.setColor(Color.BLACK);
            dropShadow.setOffsetX(2);
            dropShadow.setOffsetY(2);

            victoryLabel.setEffect(dropShadow);
            victoryLabel.setTextFill(Color.WHITE);

            Button nextLevelButton = new Button("Next Level");

            nextLevelButton.setOnAction(event -> {
                primaryStage.close();
                int currentLevelNumber = level.getLevelNumber();
                Stage nextLevelStage = new Stage();

                switch (currentLevelNumber) {

                }
            });

            VBox root = (VBox) primaryStage.getScene().getRoot();
            root.getChildren().addAll(victoryLabel, nextLevelButton);
        }
    }
}