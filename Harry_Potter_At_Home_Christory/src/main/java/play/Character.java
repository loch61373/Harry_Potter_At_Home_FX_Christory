package play;

public abstract class Character {
    private String name;
    private int damage;
    private int healthPoints;
    static Display display = new Display();

    public Character(String name, int damage, int healthPoints) {
        this.name = name;
        this.damage = damage;
        this.healthPoints = healthPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
}
