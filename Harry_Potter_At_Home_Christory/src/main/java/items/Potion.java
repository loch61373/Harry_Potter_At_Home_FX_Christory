package items;

import play.Wizard;
import play.Display;
import play.Game;
public class Potion {
    private int healAmount;
    static Display display = new Display();

    public Potion(int healAmount) {

        this.healAmount = healAmount;
    }

    public int getHealAmount() {
        return healAmount;
    }

    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }

    public void use(Wizard wizard) {
        wizard.heal(healAmount);
    }

    public static Potion choosePotion() {
        display.printMessage("Choose a potion to use:");
        display.printMessage("1. Small healing potion ");
        display.printMessage("2. Elixir of Invincibility ");

        int choice = Game.readInt("Enter your choice (1-2): ", 2);
        Potion potion;

        switch (choice) {
            case 1:
                potion = new Potion(30);
                break;
            case 2:
                potion = new Potion(50);
                break;
            default:
                throw new IllegalStateException("Invalid potion choice.");
        }

        return potion;
    }
}
