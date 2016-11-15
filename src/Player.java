import java.util.ArrayList;

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
    private ArrayList<String> inventory;
    private int locationX;
    private int locationY;

    public Player(){
        this.health = 10;
        this.damage = 1;
        this.locationX = 2;
        this.locationY = 4;
        this.addInventory("Wooden Sword");
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
        inventory.add(item);
    }

    public String getInventory() {
        String temp = "";
        for (int i = 0; i < inventory.size(); i++){
            temp = temp + inventory.get(i) + " ";
        }
        return temp;
    }

    public void die(){
        health = 0;
    }

    public void command(String arg){
        String[] commands = arg.split("\\s+");
        switch (commands[0].toLowerCase()) {
            case "help":
                this.help();
                break;
            case "use":
                break;
            case "health":
                this.getHealth();
                break;
            case "grab":
                break;
            case "examine":
                break;
            case "inventory":
                System.out.println(getInventory());
                break;
            case "move":
                switch(commands[1].toLowerCase()){
                    case "north":
                        if(this.locationX + 1 < 5){
                            this.locationX++;
                        }else{
                            System.out.println("There is a wall in that direction, you cannot move that way");
                        }
                        break;
                    case "south":
                        if(this.locationX - 1 > 0){
                            this.locationX--;
                        }else{
                            System.out.println("There is a wall in that direction, you cannot move that way");
                        }
                        break;
                    case "east":
                        if(this.locationY + 1 < 5){
                            this.locationY++;
                        }else{
                            System.out.println("There is a wall in that direction, you cannot move that way");
                        }
                        break;
                    case "west":
                        if(this.locationY - 1 > 0){
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
                "examine {{Object}} - gives description of item \n" +
                "examine room - shows what is currently in the room \n" +
                "move {{Direction}} - moves in a cardinal direction");
    }
}