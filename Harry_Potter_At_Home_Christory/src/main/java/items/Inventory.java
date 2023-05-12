package items;

import java.util.ArrayList;


public class Inventory {
    private ArrayList<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    // Add this method to your Inventory class

    public <T> T getItemByType(Class<T> itemType) {
        for (Item item : items) {
            if (itemType.isInstance(item)) {
                return itemType.cast(item);
            }
        }
        return null;
    }
    public int getSize() {
        return items.size();
    }
    public void printInventory() {
        System.out.println("Inventory:");

    }
    public boolean hasItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }


}
