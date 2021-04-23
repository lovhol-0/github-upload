/*
 * Ett textbaserat äventyrsspel
 * Love Holmström, Åke Persson, Herman Ek
 * 2021-01-08
 */
package DragonTreasureV2;

public class Key extends Item {
    
    public Key(String name, String description) {
        super(name, description);
    }
    
    public boolean isEquipped(){
        return true;
    }    
    
}
