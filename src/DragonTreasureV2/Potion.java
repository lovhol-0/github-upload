/*
 * Ett textbaserat äventyrsspel
 * Love Holmström, Åke Persson, Herman Ek
 * 2021-01-08
 */
package DragonTreasureV2;

public class Potion extends Item {
    
    private int potionHealth = 10;
    
    public Potion(String name, String description, int potionHealth) {
        super(name, description);
        this.potionHealth = potionHealth;
    }

    public int getPotionHealth() {
        return potionHealth;
    }

    public void setPotionHealth(int potionHealth) {
        this.potionHealth = potionHealth;
    }
    
    public boolean isEquipped(){
        return true;
    }    
    
}
