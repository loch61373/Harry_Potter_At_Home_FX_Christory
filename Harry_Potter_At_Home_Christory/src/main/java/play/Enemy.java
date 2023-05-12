package play;

public class Enemy extends Character {
    private int maxHealthPoints;
    public Enemy(String username, int damage, int healthPoints) {
        super(username, damage, healthPoints);
        setDamage(damage);
        this.maxHealthPoints = healthPoints;
    }
    public String getUsername() {
        return this.getName();
    }

    public void takeDamage(double damage) {
        setHealthPoints((int)(getHealthPoints() - damage));
        if (getHealthPoints() < 0) {
            setHealthPoints(0);
        }
    }
    public void heal(int healAmount) {
        int newHealthPoints = getHealthPoints() + healAmount;
        // Ensure the new health value doesn't exceed the maximum health points
        if (newHealthPoints > maxHealthPoints) {
            newHealthPoints = maxHealthPoints;
        }
        setHealthPoints(newHealthPoints);
    }
}