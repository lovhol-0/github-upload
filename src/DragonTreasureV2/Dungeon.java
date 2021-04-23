/*
 * Ett textbaserat äventyrsspel
 * Love Holmström, Åke Persson, Herman Ek
 * 2021-01-08
 */
package DragonTreasureV2;

import java.util.ArrayList;
import java.util.Scanner;

public class Dungeon {
    
    // Skapar variabler för objekten
    private ArrayList<Room> map;
    private Player player;
    private String playerName;
    private Key key;
    private Sword sword;
    private Potion potion;
    private Treasure treasure;
    private Monster beast;
    private Monster dragon;
    // Skapar och sätter variabler till false
    private boolean swordAlreadyExecuted = false;
    private boolean potionAlreadyExecuted = false;
    private boolean keyAlreadyExecuted = false;
    private boolean treasureAlreadyExecuted = false;
    private boolean beastAlreadyExecuted = false;
    private boolean dragonAlreadyExecuted = false;
    
    public Dungeon() {
        this.map = new ArrayList<Room>(); // Skapar en ArrayList där rummen ska vara
        // Lägger till alla rum i ArrayList
        map.add(new Room("The Hollow Delves", "As you enter the cave the entrance "
                + "collapses behind you.\nThe room is lit by a few candles "
                + "sitting on a table in front of you.", null, null));
        map.add(new Room("Grotto of the Hopeless Legion", "You see a dead body on the floor.", 
                sword, null));
        map.add(new Room("Tunnels of the Ancient Marsh", "You see a bat in the corner", null, beast));
        map.add(new Room("The Foaming Maze", "You see a light flickering on the floor", potion, null));
        map.add(new Room("Lair of the Mystic Basilisk", "It is super dark", key, null));
        map.add(new Room("The Diamond Chambers", "You see victory", treasure, dragon));
        map.add(new Room("freedom", "You found the way out!", null, null));
        // Skapar objekt av klasser och tilldelar till variablerna
        player = new Player(playerName, 10, 1, map.get(0));
        beast = new Monster("Beast", 8, 1, "farlig");
        dragon = new Monster("Dragon", 18, 1, "livsfarlig");
        key = new Key("Key", "opens");
        sword = new Sword("Sword", "increase", 1);
        potion = new Potion("HealthPotion", "heals", 10);
        treasure = new Treasure("Treasure", "gold");
    }
    
    // Metoden där spelet startas
    public void playGame() {
        System.out.println("Welcome to Dragon Treasure\nWrite your name and "
                + "press [Enter] to start a new game..."); 
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        setPlayerName(name);
        System.out.println("Welcome " + getPlayerName() + " to your treasure "
                + "hunt. Beware of the dragon!\nYou are standing outside a "
                + "cave. There is a smell of sulfur coming from the opening\n"
                + "The cave opening is to your east. Write \"e\" to enter the"
                + " cave (or write \"q\" to quit)");
        while (true) {
            String select = input.nextLine();
            switch (select) {
                case "e": { // Ifall spelaren väljer e förflyttas man till rum 1
                    roomOne(); 
                    break;
                }
                case "q": { // Ifall spelaren väljer q avslutas spelet
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default: {
                    System.out.println("Invalid option, try again!");
                    break;
                }
            }
        }
    }
    
    private void roomOne() {
        System.out.println(map.get(0));
        System.out.println("You can go north [n]\nYou can go south [s]");
        while (true) {
            Scanner input = new Scanner(System.in);
            String select = input.nextLine();
            switch (select) {
                case "n": { // Ifall spelaren väljer n förflyttas man till rum 2
                    roomTwo();
                    break;
                }
                case "s": { // Ifall spelaren väljer s förflyttas man till rum 5
                    roomFive();
                    break;
                }
                case "q": { // Ifall spelaren väljer q avslutas spelet
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default: {
                    System.out.println("Invalid option, try again!");
                    break;
                }
            }
        }
    }
    
    private void roomTwo() {
        System.out.println(map.get(1));
        if (!swordAlreadyExecuted) { // Ifall variabeln swordAlreadyExecuted inte är true visas texten
            System.out.println("You see a sword on the floor, you can pick"
                    + " it up [p]");
        }
        System.out.println("You can go south [s]\nYou can go east [e]");
        while (true) {
            Scanner input = new Scanner(System.in);
            String select = input.nextLine();
            switch (select) {
                case "s": { // Ifall spelaren väljer s förflyttas man till rum 1
                    roomOne();
                    break;
                }
                case "e": { // Ifall spelaren väljer e förflyttas man till rum 3
                    roomThree();
                    break;
                }
                case "p": { // Ifall spelaren väljer p kontrolleras det om man tagit svärdet tidigare
                    if (swordAlreadyExecuted){
                        System.out.println("Invalid option, try again!");
                        break; // Break ifall svärdet redan är taget
                    }
                    int newDamage = player.getDamage() + sword.getIncreaseDamage();
                    player.setDamage(newDamage); // Spelaren får ny damage efter man tagit upp svärdet
                    swordAlreadyExecuted = true; // Variabeln ändras till true så att man inte kan ta upp svärdet igen
                    System.out.println("You picked up the sword.");
                    roomTwo(); // Metoden börjar om
                    break;
                }
                case "q": { // Ifall spelaren väljer q avslutas spelet
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default: {
                    System.out.println("Invalid option, try again!");
                    break;
                }
            }
        } 
    }
    
    private void roomThree() {
        if (!beastAlreadyExecuted) { // Kontrollerar om man redan dödat beast, om inte så anropas beastCombat()
            beastCombat();
        }
        System.out.println(map.get(2));
        System.out.println("You can go south [s]\nYou can go west [w]"
                + "\nYou see an exit to the east [e]");
        while (true) {
            Scanner input = new Scanner(System.in);
            String select = input.nextLine();
            switch (select) {
                case "s": { // Ifall spelaren väljer s förflyttas man till rum 4
                    roomFour();
                    break;
                }
                case "w": { // Ifall spelaren väljer w förflyttas man till rum 2
                    roomTwo();
                    break;
                }
                case "e": { // Kontrollerar ifall man tagit skatten, isåfall förflyttas man till rum 7
                    if (treasureAlreadyExecuted) {
                        roomSeven();
                    }
                    System.out.println("You need the treasure to leave!");   
                    break;
                }
                case "q": { // Ifall spelaren väljer q avslutas spelet
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default: {
                    System.out.println("Invalid option, try again!");
                    break;
                }
            }
        }
    }
    
    private void roomFour() {
        System.out.println(map.get(3));
        if (!potionAlreadyExecuted) { // Kontrollerar om man tagit upp potion tidigare, om inte visas texten
            System.out.println("You see a health potion laying on the floor, "
                    + "you can pick it up [p]");
        }
        System.out.println("You can go north [n]\nYou can go west [w]"
                + "\nYou can go east [e]");
        while (true) {
            Scanner input = new Scanner(System.in);
            String select = input.nextLine();
            switch (select) {
                case "n": { // Ifall spelaren väljer n förflyttas man till rum 3
                    roomThree();
                    break;
                }
                case "w": { // Ifall spelaren väljer w förflyttas man till rum 5
                    roomFive();
                    break;
                }
                case "e": { // Ifall spelaren väljer e kontrolleras det om man har tagit upp nyckeln, isåfall förflyttas man till rum 6
                    if (keyAlreadyExecuted) {
                        roomSix();
                    }
                    else {
                        System.out.println("The door is locked!");
                    }
                    break;
                }
                case "p": { // Ifall spelaren väljer p kontrolleras det om man har tagit upp potionen, ifall man har breakas det 
                    if (potionAlreadyExecuted){
                        System.out.println("Invalid option, try again!");
                        break;
                    }
                    potionAlreadyExecuted = true; // Sätts till true så man inte kan ta upp potion igen
                    System.out.println("You picked up the health potion");
                    if (player.getHealthPoints() < 10) { // Kontrollerar ifall man har fullt HP, om man inte har det erbjuds man att dricka potion
                        System.out.println("You have " 
                                + player.getHealthPoints() + " health points "
                                        + "left. Might be a good idea to drink"
                                        + " that health potion [d]");
                        while (true) {
                            String select2 = input.nextLine();
                            switch (select2) { // Switch för att dricka potion eller avsluta
                                case "d": {
                                    player.setHealthPoints(potion.getPotionHealth());
                                    System.out.println("You drink the health potion"
                                            + " and are now at full health");
                                    roomFour(); // Börjar om i samma rum efter man druckit potion
                                    break;
                                }
                                case "q": {
                                    System.out.println("Goodbye!");
                                    System.exit(0);
                                }
                                default: {
                                    System.out.println("Invalid option, try again!");
                                    break;
                                }
                            }
                        }
                    }
                    roomFour(); // Börjar om i samma rum
                    break;
                }
                case "q": {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default: {
                    System.out.println("Invalid option, try again!");
                    break;
                }
            }
        }
    }
    
    private void roomFive() {
        System.out.println(map.get(4));
        if (!keyAlreadyExecuted) { // Kontrollerar ifall man tagit upp nyckeln, om inte visas texten
            System.out.println("You see a key laying on the floor, you can "
                    + "pick it up [p]");
        }
        System.out.println("You can go north [n]\nYou can go east [e]");
        while (true) {
            Scanner input = new Scanner(System.in);
            String select = input.nextLine();
            switch (select) {
                case "n": {
                    roomOne();
                    break;
                }
                case "e": {
                    roomFour();
                    break;
                }
                case "p": { 
                    if (keyAlreadyExecuted) { // Kontrollerar ifall man tagit nyckeln, om man har så breakas det
                        System.out.println("Invalid option, try again!");
                        break;
                    }
                    keyAlreadyExecuted = true; // Sätts till true så man inte ska kunna ta upp nyckeln igen
                    System.out.println("You picked up the key.");
                    roomFive(); // Börjar om i samma rum
                    break;
                }
                case "q": {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default: {
                    System.out.println("Invalid option, try again!");
                    break;
                }
            }
        }
    }
    
    private void roomSix() {
        treasureAlreadyExecuted = true; // Sätts till true så att man inte kan ta upp skatten igen
        if (!dragonAlreadyExecuted) { // Kontrollerar om man redan dödat draken, om inte så anropas metoden dragonCombat
            dragonCombat();
        }
        System.out.println(map.get(5));
        System.out.println("You can go west [w]");
        while (true) {
            Scanner input = new Scanner(System.in);
            String select = input.nextLine();
            switch (select) {
                case "w": { // Om spelaren väljer w förflyttas man till rum 4
                    roomFour(); 
                    break;
                }
                case "q": {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default: {
                    System.out.println("Invalid option, try again!");
                    break;
                }
            }
        } 
    }
    
    private void beastCombat() { // metod för fighten med beast
        int playerHP = player.getHealthPoints();
        int playerDamage = player.getDamage();
        int beastHP = beast.getHealthPoints();
        int beastDamage = beast.getDamage();
        while (playerHP > 0 && beastHP > 0) { // loop som forsätter tills nån dör
            System.out.println("A beast attacks you and does " + beastDamage + " damage");
            playerHP -= beastDamage;
            if (playerHP > 0) {
                System.out.println("You attack the beast and do " + playerDamage + " damage");
                beastHP -= playerDamage;
            }
        }
        if (playerHP == 0) {
            System.out.println("RIP, game over");
            System.exit(0);
        }
        else {
            player.setHealthPoints(playerHP);
            System.out.println("You defeat the beast\nYou have " 
                    + player.getHealthPoints() + " health points left. Will it"
                            + " be enough for a fight with a dragon?");
        }
        beastAlreadyExecuted = true; // ändras till true så att man inte kan slåss beast igen
    }
    
    private void dragonCombat() { // metod för fight med draken
        int playerHP = player.getHealthPoints();
        int playerDamage = player.getDamage();
        int dragonHP = dragon.getHealthPoints();
        int dragonDamage = dragon.getDamage();
        System.out.println(
        "                                                  .~))>>\n"+
        "                                                 .~)>>\n"+
        "                                               .~))))>>>\n"+
        "                                             .~))>>             ___\n"+
        "                                           .~))>>)))>>      .-~))>>\n"+
        "                                         .~)))))>>       .-~))>>)>\n"+
        "                                       .~)))>>))))>>  .-~)>>)>\n"+
        "                   )                 .~))>>))))>>  .-~)))))>>)>\n"+
        "                ( )@@*)             //)>))))))  .-~))))>>)>\n"+
        "              ).@(@@               //))>>))) .-~))>>)))))>>)>\n"+
        "            (( @.@).              //))))) .-~)>>)))))>>)>\n"+
        "          ))  )@@*.@@ )          //)>))) //))))))>>))))>>)>\n"+
        "       ((  ((@@@.@@             |/))))) //)))))>>)))>>)>\n"+
        "      )) @@*. )@@ )   (\\_(\\-\\b  |))>)) //)))>>)))))))>>)>\n"+
        "    (( @@@(.@(@ .    _/`-`  ~|b |>))) //)>>)))))))>>)>\n"+
        "     )* @@@ )@*     (@)  (@) /\\b|))) //))))))>>))))>>\n"+
        "   (( @. )@( @ .   _/  /    /  \\b)) //))>>)))))>>>_._\n"+
        "    )@@ (@@*)@@.  (6///6)- / ^  \\b)//))))))>>)))>>   ~~-.\n"+
        " ( @jgs@@. @@@.*@_ VvvvvV//  ^  \\b/)>>))))>>      _.     `bb\n"+
        " ((@@ @@@*.(@@ . - | o |' \\ (  ^   \\b)))>>        .'       b`,\n"+
        "   ((@@).*@@ )@ )   \\^^^/  ((   ^  ~)_        \\  /           b `,\n"+
        "     (@@. (@@ ).     `-'   (((   ^    `\\ \\ \\ \\ \\|             b  `.\n"+
        "       (*.@*              / ((((        \\| | |  \\       .       b `.\n"+
        "                         / / (((((  \\    \\ /  _.-~\\     Y,      b  ;\n"+
        "                        / / / (((((( \\    \\.-~   _.`\" _.-~`,    b  ;\n"+
        "                       /   /   `(((((()    )    (((((~      `,  b  ;\n"+
        "                     _/  _/      `\"\"\"/   /'                  ; b   ;\n"+
        "                 _.-~_.-~           /  /'                _.'~bb _.'\n"+
        "               ((((~~              / /'              _.'~bb.--~\n"+
        "                                  ((((          __.-~bb.-~\n"+
        "                                              .'  b .~~\n"+
        "                                              :bb ,' \n"+
        "                                              ~~~~\n");  
        System.out.println("An angry dragon appears");
        while (playerHP > 0 && dragonHP > 0) { // loop som fortsätter tills nån dör
            System.out.println("A dragon attacks you and does " + dragonDamage + " damage");
            playerHP -= dragonDamage;
            if (playerHP > 0) {
                System.out.println("You attack the dragon and do " + playerDamage + " damage");
                dragonHP -= playerDamage;
            }
        }
        if (playerHP == 0) {
            System.out.println("RIP, game over");
            System.exit(0);
        }
        else {
            player.setHealthPoints(playerHP);
            System.out.println("You defeat the dragon and collect the treasure."
                    + " Can you escape this dungeon with all your riches?\n"
                    + "You drag the gold out the way you came from.");
        }
        dragonAlreadyExecuted = true; // ändras så man inte kan slåss med draken igen
    }
    
    private void roomSeven() {
        System.out.println(map.get(6));
        System.out.println(
            "                  _.--.\n"+
            "              _.-'_:-'||\n"+
            "          _.-'_.-::::'||\n"+
            "     _.-:'_.-::::::'  ||\n"+
            "   .'`-.-:::::::'     ||\n"+
            "  /.'`;|:::::::'      ||_\n"+
            " ||   ||::::::'      _.;._'-._\n"+
            " ||   ||:::::'   _.-!oo @.!-._'-.\n"+
            " \'.  ||:::::.-!() oo @!()@.-'_.||\n"+
            "   '.'-;|:.-'.&$@.& ()$%-'o.'\\U||\n"+
            "     `>'-.!@%()@'@_%-'_.-o _.|'||\n"+
            "      ||-._'-.@.-'_.-' _.-o  |'||\n"+
            "      ||=[ '-._.-\\U/.-'    o |'||\n"+
            "      || '-.]=|| |'|      o  |'||\n"+
            "      ||      || |'|        _| ';\n"+
            "      ||      || |'|    _.-'_.-'\n"+
            "      |'-._   || |'|_.-'_.-'\n"+
            "      '-._'-.|| |' `_.-'\n"+
            "           '-.||_/.-'\n");
        System.out.println("You leave the dungeon with your riches. "
                + "Congratulations, you won!");
        System.exit(0); // Spelaren vinner och programmet avslutas
    }

    public ArrayList<Room> getMap() {
        return map;
    }

    public void setMap(ArrayList<Room> map) {
        this.map = map;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    
}
