package items;

import play.Display;

public class Reward extends Item {
    private String reward;
    static Display display = new Display();

    public Reward(String name) {
        super(name); // Call the superclass constructor with the item type
        this.reward = name;
        display.printMessage("Congrats your getting " + reward);
    }

    public String getReward(){
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    @Override
    public String getName() {
        return reward;
    }
}
