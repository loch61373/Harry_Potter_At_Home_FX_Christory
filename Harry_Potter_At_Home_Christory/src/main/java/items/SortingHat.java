package items;

import play.Display;

import java.util.Random;

public class SortingHat {
    static Display display = new Display();
    public static House randomHouse() {
        House[] houses = House.values();
        int randomIndex = new Random().nextInt(houses.length);
        House chosenHouse = houses[randomIndex];
        display.printMessage("The Sorting Hat is choosing your house...");
        display.printMessage("Hmm, interesting. Very interesting.");
        display.printMessage("I think I know just the place for you!");
        display.printMessage("Congratulations, " + "you are now a member of " + chosenHouse.getName() + "!");
        return chosenHouse;
    }
}

