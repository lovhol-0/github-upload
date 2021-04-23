/*
 * Ett textbaserat äventyrsspel
 * Love Holmström, Åke Persson, Herman Ek
 * 2021-01-08
 */
package DragonTreasureV2;

public class DungeonMaster {
    
    static Dungeon dungeon;
    
    public static void main(String[] args) {
        
        dungeon = new Dungeon(); // Skapar objekt dungeon av klassen Dungeon
        dungeon.playGame(); // Anropar metoden playGame i klassen Dungeon
        
    }
    
}
