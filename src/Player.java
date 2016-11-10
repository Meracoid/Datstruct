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

    public Player(){
        this.health = 10;
        this.damage = 1;
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

}