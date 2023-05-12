package play;

import items.*;

import java.util.Random;

public class Wizard extends Character {
    private final House house;
    private Wand wand;
    private Pet pet;
    private int maxHealthPoints;
    private int HP;
    private Inventory inventory;
    static Display display = new Display();

    public Wizard(int HP, String name, House house, Wand wand, Pet pet) {
        super(name,40, 100);
        this.HP = HP;
        this.house = house;
        this.wand = wand;
        this.pet = pet;
        this.maxHealthPoints = 100;
        this.inventory = new Inventory();
        initHouseItems();
    }
    public Inventory getInventory() {
        return inventory;
    }

    private void initHouseItems() {
        switch (house.getName()) {
            case "Gryffindor":
                Sword sword = new Sword("Gryffindor Sword", 100);
                inventory.addItem(sword);
                break;

        }
    }
    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void attackEnemy(Enemy enemy, Spell spell) {
        Random random = new Random();
        double accuracy = spell.getAccuracy();
        double damage = spell.getDamage();

        // Apply house-specific effects
        switch (house.getName()) {
            case "Slytherin":
                damage *= 1.2; // Spells deal 20% more damage for Slytherin wizards
                break;
            case "Gryffindor":
                maxHealthPoints = 120; // Gryffindor wizards have 20 more max health points
                break;
            case "Hufflepuff":
                heal(10); // Hufflepuff wizards heal for an additional 10 HP when using a potion
                break;
            case "Ravenclaw":
                accuracy *= 1.1; // Spells are 10% more accurate for Ravenclaw wizards
                break;
        }
        if (random.nextDouble() * 100 <= accuracy) {
            enemy.takeDamage((int) damage);
            display.printMessage("You cast " + spell.getName() + " and dealt " + damage + " damage to " + enemy.getUsername() + ".");
        } else {
            display.printMessage("You missed the spell!");
        }
    }
    public void heal(int healAmount) {
        setHealthPoints(Math.min(getHealthPoints() + healAmount, maxHealthPoints));

    }
    public void takeDamage(int damage) {
        int finalDamage = damage;
        switch (house.getName()) {
            case "Gryffindor":
                finalDamage = (int) (damage * 0.8);
                display.printMessage("Because you're in Griffondor, you're damage is decreased by a factor of 0.8");
                break;
            default:
                break;
        }
        setHealthPoints(Math.max(getHealthPoints() - finalDamage, 0));
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public House getHouse() {
        return house;
    }
    public Wand getWand() {
        return wand;
    }
    public Pet getPet() {
        return pet;
    }
}
