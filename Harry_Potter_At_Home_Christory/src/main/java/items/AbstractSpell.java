package items;

public abstract class AbstractSpell {
    private String name;
    private double damage;
    private double accuracy;

    public AbstractSpell(String name, double damage, double accuracy) {
        this.name = name;
        this.damage = damage;
        this.accuracy = accuracy;
    }

    public String getName() {
        return name;
    }

    public double getDamage() {
        return damage;
    }

    public double getAccuracy() {
        return accuracy;
    }
    public void setDamage(double damage) {
        this.damage = damage;
    }
    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }


}
