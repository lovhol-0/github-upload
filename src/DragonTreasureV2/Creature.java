/*
 * Ett textbaserat äventyrsspel
 * Love Holmström, Åke Persson, Herman Ek
 * 2021-01-08
 */
package DragonTreasureV2;

public class Creature {
    
    private String name;
    private int healthPoints;
    private int damage;
    
    public Creature(String name, int healthPoints, int damage) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
    
}
