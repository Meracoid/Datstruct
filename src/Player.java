import java.util.ArrayList;
import java.util.Arrays;

public class Player{
    /**
     * @author: meracoid
     * @date: 11/10/16
     *
     * health=players health
     * damage=damage a player can do to a monster
     * inventory=an ArrayList of all items in the players inventory
     */
    private int health;
    private int damage;
    private ArrayList<String> inventory = new ArrayList<String>();
    private int locationX;
    private int locationY;
    private boolean hasShield;
    private boolean inFight;

    public Player(){
        this.health = 10;
        this.damage = 1;
        this.locationX = 4;
        this.locationY = 2;
        this.addInventory("wooden-sword");
        this.inFight = false;
        this.hasShield = false;
    }

    public boolean isHasShield() {
        return hasShield;
    }

    public void setHasShield(boolean hasShield) {
        this.hasShield = hasShield;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public void addInventory(String item){
        try {
            inventory.add(item);
        }catch(Exception e){

        }
    }

    public boolean isInFight() {
        return inFight;
    }

    public void setInFight(boolean inFight) {
        this.inFight = inFight;
    }

    public String getInventory() {
        String temp = inventory.get(0);
        try {
            for (int i = 1; i < inventory.size(); i++) {
                temp = temp + ", " + inventory.get(i);
            }
        }catch(Exception e){

        }
        return temp;
    }

    public void die(){
        health = 0;
    }

    public void command(String arg, Map map, Monster monster){
        String[] commands = arg.split("\\s+");
        switch (commands[0].toLowerCase()) {
            case "help":
                this.help();
                break;
            case "use":
                if(this.inventory.contains(commands[1])) {
                    switch (commands[1].toLowerCase()) {
                        case "health-potion":
                            if (this.health <= 7) {
                                this.health += 2;
                                System.out.println("Health raised by 2 points. Current health " + this.getHealth() + "/10");
                                removeItem(commands[1].toLowerCase());
                            } else {
                                this.health = 10;
                                System.out.println("Health maxed out!");
                            }
                            break;
                        case "shield":
                            if(inventory.contains("shield")){
                                hasShield = true;
                                System.out.println("You have equipted the shield");
                            }else{
                                System.out.println("You do not have a shield");
                            }
                            break;
                        case "sword":
                            if (!inFight) {
                                System.out.println("Cannot use out of combat.");
                            }else{
                                this.damage = 3;
                                attack(monster);
                                System.out.println("You dealt 3 damage to the monster");
                                this.damage = 1;
                            }
                            break;
                        case "wooden-sword":
                            if(!inFight){
                                System.out.println("Cannot use out of combat.");
                            }else{
                                this.damage = 2;
                                attack(monster);
                                System.out.println("You dealt 2 damage to the monster");
                                this.damage = 1;
                            }
                            break;
                        case "map":
                            showLocation(map);
                            break;
                        case "gold":
                            System.out.println("Has no effect");
                            break;

                    }
                }else{
                    System.out.println("Item not in your inventory.");
                }
                break;
            case "health":
                System.out.println(this.getHealth() + "/10");
                break;
            case "grab":
                if(map.hasItem(map.getDungeonRoom(this.locationX, this.locationY), commands[1])){
                    map.loseItem(map.getDungeonRoom(this.locationX, this.locationY), commands[1]);
                    this.addInventory(commands[1]);
                    System.out.println("You added " + commands[1] + ". Current Inventory: " + this.getInventory());
                }else{
                    System.out.println("Item is not in the room.");
                }
                break;
            case "examine":
                switch(commands[1].toLowerCase()){
                    case "room":
                        switch (map.getDungeonRoom(this.locationX, this.locationY)){
                            case 1:
                                System.out.print("You are in a hallway. ");
                                canMoveDirection(map);
                                break;
                            case 2:
                                System.out.print("You are in a room.");
                                canMoveDirection(map);
                                System.out.print("The following items are in the room: ");
                                break;
                            case 3:
                                System.out.print("You are in a room. ");
                                canMoveDirection(map);
                                System.out.print("The following items are in the room: ");
                                break;
                            case 4:
                                System.out.println("You are in a room. ");
                                canMoveDirection(map);
                                System.out.print("The following items are in the room: ");
                                break;
                            case -1:
                                System.out.println("You are in a room. ");
                                canMoveDirection(map);
                                System.out.print("The following items are in the room: ");
                                break;
                        }
                        System.out.println(Arrays.asList(map.getRoomItems(map.getDungeonRoom(this.locationX, this.locationY))).toString());
                        break;
                    case "health-potion":
                        if(Arrays.asList(map.getRoomItems(map.getDungeonRoom(this.locationX,this.locationY))).contains("health-potion")) {
                            System.out.println("A potion capable of healing 2 missing Health");
                        }else{
                            System.out.println("Invalid Item in current Room");
                        }
                        break;
                    case "sword":
                        if(Arrays.asList(map.getRoomItems(map.getDungeonRoom(this.locationX,this.locationY))).contains("sword")) {
                            System.out.println("A sword that is much better than your own");
                        }else{
                            System.out.println("Invalid Item in current Room");
                        }
                        break;
                    case "shield":
                        if(Arrays.asList(map.getRoomItems(map.getDungeonRoom(this.locationX,this.locationY))).contains("shield")) {
                            System.out.println("A shield that will lessen your damage when used");
                        }else{
                            System.out.println("Invalid Item in current Room");
                        }
                        break;
                    case "map":
                        if(Arrays.asList(map.getRoomItems(map.getDungeonRoom(this.locationX,this.locationY))).contains("map")) {
                            System.out.println("A map of the area. Also shows your location");
                        }else{
                            System.out.println("Invalid Item in current Room");
                        }
                        break;
                    default:
                        System.out.println("Not able to examine");
                }
                break;
            case "inventory":
                System.out.println(getInventory());
                break;
            case "move":
                if(this.inFight){
                   System.out.println("You can't leave if you are in a fight, coward");
                   break;
                }
                switch(commands[1].toLowerCase()){
                    case "south":
                        if(this.locationX + 1 < 6 && map.getDungeonRoom(this.locationX + 1, this.locationY) != 0){
                            this.locationX++;
                        }else{
                            System.out.println("There is a wall in that direction, you cannot move that way");
                        }
                        break;
                    case "north":
                        if(this.locationX - 1 > -1 && map.getDungeonRoom(this.locationX - 1, this.locationY) != 0){
                            this.locationX--;
                        }else{
                            System.out.println("There is a wall in that direction, you cannot move that way");
                        }
                        break;
                    case "east":
                        if(this.locationY + 1 < 6 && map.getDungeonRoom(this.locationX, this.locationY + 1) != 0){
                            this.locationY++;
                        }else{
                            System.out.println("There is a wall in that direction, you cannot move that way");
                        }
                        break;
                    case "west":
                        if(this.locationY - 1 > -1 && map.getDungeonRoom(this.locationX, this.locationY - 1) != 0){
                            this.locationY--;
                        }else{
                            System.out.println("There is a wall in that direction, you cannot move that way");
                        }
                        break;
                    default:
                        System.out.println("Invalid direction, please use a cardinal direction");
                        break;
                }
                break;
            default:
                System.out.println("Not a command, please use \"help\" to get list of commands");
                break;
        }
    }

    void help(){
        System.out.println("help - lists all commands \n " +
                "use {{Object}} - uses an object \n" +
                "health - shows the player's current health \n" +
                "grab {{Object}} - adds Object to inventory \n" +
                "inventory - shows your current inventory \n" +
                "examine {{Object}} - gives description of item \n" +
                "examine room - shows what is currently in the room \n" +
                "move {{Direction}} - moves in a cardinal direction");
    }

    void canMoveDirection(Map map){
        if(this.locationX + 1 < 5) {
            if (map.getDungeonRoom(this.locationX + 1, this.locationY) != 0) {
                System.out.print("You can move South. ");
            }
        }
        if(this.locationX - 1 > -1) {
            if (map.getDungeonRoom(this.locationX - 1, this.locationY) != 0) {
                System.out.print("You can move North. ");
            }
        }
        if(this.locationY + 1 < 5) {
            if (map.getDungeonRoom(this.locationX, this.locationY + 1) != 0) {
                System.out.print("You can move West. ");
            }
        }
        if(this.locationY - 1 > -1) {
            if (map.getDungeonRoom(this.locationX, this.locationY - 1) != 0) {
                System.out.print("You can move East. ");
            }
        }
    }

    boolean removeItem(String item){
        if(this.inventory.contains(item)){
            for(int i = 0; i < this.inventory.size() - 1; i++){
                if(inventory.get(i).equals(item)){
                    inventory.remove(i);
                    System.out.println("You used your " + item + ".");
                    return true;
                }
            }
        }
        return false;
    }

    void attack(Monster monster){
        if(monster.getHealth() <= this.damage){
            monster.setHealth(0);
        }else{
            monster.setHealth(monster.getHealth() - this.damage);
        }
    }

    void showLocation(Map map){
        System.out.println("KEY: + is hallway, M is a monster room, E is an exit, S is the starting room," +
                " * is an inaccessible area. You are at X.");
        for(int i = 0; i <= 4; i++){
            System.out.print("[");
            for (int j = 0; j <= 4; j++){
                if(this.locationX == i && this.locationY == j){
                    System.out.print("X");
                }else{
                    switch(map.getDungeonRoom(i,j)){
                        case 0:
                            System.out.print("*");
                            break;
                        case 1:
                            System.out.print("+");
                            break;
                        case 2:
                            System.out.print("M");
                            break;
                        case 3:
                            System.out.print("E");
                            break;
                        case 4:
                            System.out.print("M");
                            break;
                        case -1:
                            System.out.print("S");
                            break;
                    }
                }
            }
            System.out.println("]");
        }
    }
}
