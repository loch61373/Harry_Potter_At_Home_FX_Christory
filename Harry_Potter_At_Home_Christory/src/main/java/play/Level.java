package play;

import items.Potion;
import items.Spell;
import items.Sword;

import java.util.Optional;

public abstract class Level {
    private String name;
    private final String location;
    private Enemy enemy;
    static Display display = new Display();
    public abstract Wizard getWizard();
    public abstract Spell getSpell();
    public abstract Optional<Sword> getSword();

    public Level(String name, String location, Enemy enemy) {
        this.name = name;
        this.location = location;
        this.enemy = enemy;
    }

    // Getters and setters for the private fields
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public static void battle(Wizard wizard, Enemy enemy, Spell spell, Optional<Sword> sword) {

        while (wizard.getHealthPoints() > 0 && enemy.getHealthPoints() > 0) {
            display.printMessage("Your health: " + wizard.getHealthPoints() + " | Enemy health: " + enemy.getHealthPoints());
            display.printMessage("Choose an action:");
            display.printMessage("1. Attack with " + spell.getName());
            if (wizard.getHealthPoints() > 0) {
                display.printMessage("2. Use a healing potion");
            }
            if (sword.isPresent()) {
                display.printMessage("3. Attack with " + sword.get().getName());
            }

            int maxChoice = (wizard.getHealthPoints() > 0 ? (sword.isPresent() ? 3 : 2) : 1);
            int choice = Game.readInt("Enter your choice (1-" + maxChoice + "): ", maxChoice);

            switch (choice) {
                case 1:
                    // Wizard attacks enemy with spell
                    wizard.attackEnemy(enemy, spell);
                    break;
                case 2:
                    // Wizard uses a healing potion
                    if (wizard.getHealthPoints() > 0) {
                        choosePotion(wizard, enemy, spell);
                    }
                    break;
                case 3:
                    // Wizard attacks with sword
                    if (sword.isPresent()) {
                        Sword gryffindorSword = sword.get();
                        enemy.takeDamage(gryffindorSword.getDamage());
                        display.printMessage("You dealt " + gryffindorSword.getDamage() + " damage to the " + enemy.getName() + ".");
                    }
                    break;
            }

            // Check if the enemy is defeated
            if (enemy.getHealthPoints() <= 0) {
                display.printMessage("You have defeated the " + enemy.getName() + "!");
                break;
            }

            // Enemy attacks the wizard
            wizard.takeDamage(enemy.getDamage());
            display.printMessage("The " + enemy.getName() + " dealt " + enemy.getDamage() + " damage to you.");

            // Check if the wizard is dead
            if (wizard.getHealthPoints() <= 0) {
                display.printMessage("You have been defeated by the " + enemy.getName());
                break;
            }
        }
    }
    public static void choiceIncrease(Wizard wizard, Spell spell) {
        display.printMessage("Choose a spell attribute to increase:");
        display.printMessage("1. Increase spell damage by 10");
        display.printMessage("2. Increase spell accuracy by 5%");

        int choice = Game.readInt("Enter your choice (1-2): ", 2);

        switch (choice) {
            case 1:
                spell.setDamage(spell.getDamage() + 10);
                display.printMessage("Spell damage increased to " + spell.getDamage() + ".");
                break;
            case 2:
                double newAccuracy = Math.min(spell.getAccuracy() + 5, 100);
                spell.setAccuracy(newAccuracy);
                display.printMessage("Spell accuracy increased to " + newAccuracy + "%.");
                break;
        }
    }

    public static void choosePotion(Wizard wizard, Enemy enemy, Spell spell) {
        Potion potion = Potion.choosePotion();
        potion.use(wizard);
        display.printMessage("You used a " + (potion.getHealAmount() == 25 ? "small" : "large") + " healing potion and regained " + potion.getHealAmount() + " HP. Your current HP is " + wizard.getHealthPoints() + ".");
    }

    public static void endingLevel(Wizard wizard, Enemy enemy, int levelId) {
        if (enemy.getHealthPoints() <= 0) {
            display.printMessage("You have successfully completed Level " + levelId + "! Congratulations!");
        } else {
            display.printMessage("You couldn't defeat " + enemy.getName() + " in Level " + levelId + ". Better luck next time!");
        }
    }

    public Display getDisplay() {
        return display;
    }
    public abstract int getLevelNumber();


}