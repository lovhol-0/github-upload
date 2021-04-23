/*
 * Ett textbaserat äventyrsspel
 * Love Holmström, Åke Persson, Herman Ek
 * 2021-01-08
 */
package DragonTreasureV2;

public class Room extends Item {
    
    private Item item;
    private Creature creature;
    
    public Room(String name, String description, Item item, Creature creature) {
        super(name, description);
        this.item = new Item("","");
        this.creature = new Creature("",0,0);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }
    
    @Override
    public String toString() {
        return String.format("You are in " + getName() + "\n" 
                + getDescription());
    }
    
}
