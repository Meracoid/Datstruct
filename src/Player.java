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
    private boolean inFight;

    public Player(){
        this.health = 10;
        this.damage = 1;
        this.locationX = 4;
        this.locationY = 2;
        this.addInventory("Wooden Sword");
        this.inFight = false;
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
        String temp = "";
        try {
            for (int i = 0; i < inventory.size(); i++) {
                temp = temp + inventory.get(i) + " ";
            }
        }catch(Exception e){

        }
        return temp;
    }

    public void die(){
        health = 0;
    }

    public void command(String arg, Map map){
        String[] commands = arg.split("\\s+");
        switch (commands[0].toLowerCase()) {
            case "help":
                this.help();
                break;
            case "use":

                break;
            case "health":
                System.out.println(this.getHealth() + "/10");
                break;
            case "grab":
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
                    case "HealthPotion":
                        if(Arrays.asList(map.getRoomItems(map.getDungeonRoom(this.locationX,this.locationY))).contains("HealthPotion")) {
                            System.out.println("A potion capable of healing 2 missing Health");
                        }else{
                            System.out.println("Invalid Item in current Room");
                        }
                        break;
                    case "DragonFlute":
                        if(Arrays.asList(map.getRoomItems(map.getDungeonRoom(this.locationX,this.locationY))).contains("DragonFlute")) {
                            System.out.println("A flute, when used, will make the dragon not attack for a while");
                        }else{
                            System.out.println("Invalid Item in current Room");
                        }
                        break;
                    case "Sword":
                        if(Arrays.asList(map.getRoomItems(map.getDungeonRoom(this.locationX,this.locationY))).contains("Sword")) {
                            System.out.println("A sword that is much better than your own");
                        }else{
                            System.out.println("Invalid Item in current Room");
                        }
                        break;
                    case "Shield":
                        if(Arrays.asList(map.getRoomItems(map.getDungeonRoom(this.locationX,this.locationY))).contains("Shield")) {
                            System.out.println("A shield that will lessen your damage when used");
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
                    case "west":
                        if(this.locationY + 1 < 6 && map.getDungeonRoom(this.locationX, this.locationY + 1) != 0){
                            this.locationY++;
                        }else{
                            System.out.println("There is a wall in that direction, you cannot move that way");
                        }
                        break;
                    case "east":
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
        if(this.locationX + 1 < 6) {
            if (map.getDungeonRoom(this.locationX + 1, this.locationY) != 0) {
                System.out.print("You can move South. ");
            }
        }
        if(this.locationX - 1 > -1) {
            if (map.getDungeonRoom(this.locationX - 1, this.locationY) != 0) {
                System.out.print("You can move North. ");
            }
        }
        if(this.locationY + 1 < 6) {
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
}