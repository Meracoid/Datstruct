public class Monster{
    /**
     * @author: meracoid
     * @date: 11/10/16
     * health= monster's health
     * damage= damage monster can deal
     * wait= how long it waits between each attack
     * waitCounter= an int which will keep count of turns passed since last attack
     */
    private int health;
    private int damage;
    private int wait;
    private int waitCounter;

    public Monster(){

    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public int getWait() {
        return wait;
    }

    public int getWaitCounter() {
        return waitCounter;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public void setWaitCounter(int waitCounter) {
        this.waitCounter = waitCounter;
    }
}