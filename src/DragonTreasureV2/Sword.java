/*
 * Ett textbaserat äventyrsspel
 * Love Holmström, Åke Persson, Herman Ek
 * 2021-01-08
 */
package DragonTreasureV2;

public class Sword extends Item {
    
    private int increaseDamage = 1;
    
    public Sword(String name, String description, int increaseDamage) {
        super(name, description);
        this.increaseDamage = increaseDamage;
    }

    public int getIncreaseDamage() {
        return increaseDamage;
    }

    public void setIncreaseDamage(int increaseDamage) {
        this.increaseDamage = increaseDamage;
    }
    
    public boolean isEquipped(){
        return true;
    }    
    
}
