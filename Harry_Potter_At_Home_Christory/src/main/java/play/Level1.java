package play;

import items.Inventory;
import items.Reward;
import items.Spell;
import items.Sword;

import java.util.Optional;

public class Level1 extends Level {

    public Level1(Wizard wizard) {
        super("Hogwarts School of Witchcraft and Wizardry", "The dungeon toilets",  new Enemy("Troll", 20, 50));
        this.wizard = wizard;
    }
    public void playLevel(Wizard wizard) {
        display.printMessage("Welcome to " + wizard.getName() + " at " + getLocation() + "!");
        display.printMessage("You are facing a wild " + getEnemy().getName() + " that has appeared!");

        // Perform level-specific gameplay mechanics here
        Spell spell = new Spell("Wingardium Leviosa", 25, 60);
        battle(wizard, getEnemy(), spell, Optional.empty());
        if (wizard.getHealthPoints() > 0) {
            Level.choiceIncrease(wizard, spell);
            if (getEnemy().getHealthPoints() <= 0) {
                Level.endingLevel(wizard, getEnemy(), 1);
                display.anythingToContinue();
                Reward reward = new Reward(" a golden snitch");
                wizard.getInventory().addItem(reward);
                display.printMessage("You have received " + reward.getReward() + " as a reward.");
                Inventory inventory = wizard.getInventory();
                inventory.printInventory();
                display.anythingToContinue();
            }
        }
    }
    private Wizard wizard;

    @Override
    public Wizard getWizard() {
        return wizard;
    }

    @Override
    public Spell getSpell() {
        return new Spell("Wingardium Leviosa", 25, 60);
    }

    @Override
    public Optional<Sword> getSword() {
        return Optional.empty();
    }
    @Override
    public int getLevelNumber(){
        return 1;
    }
}
