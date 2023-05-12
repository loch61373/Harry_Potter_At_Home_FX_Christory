package items;

import play.Display;

public abstract class Item {
    private String name;
    static Display display = new Display();


    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
