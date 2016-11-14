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
    private int[5][5] location;

    public Player(){
        this.health = 10;
        this.damage = 1;

    }

    public int[][] getLocation() {
        for(int i = 0; i < location.length; i++){
            for (int j = 0; j < location[].length; j++){
                if(location[i][j] == 1){
                    return ;
                }
            }
        }
    }

    public void setLocation(int[][] location) {
        this.location = location;
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

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public int getHealth() {
        return health;
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
            case "move":
                break;
            case default:
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
                "move {{Direction}} - moves in a cardinal direction");
    }

}