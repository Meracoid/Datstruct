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
    private boolean reducedDamage;

    public Monster(){

    }

    public boolean isReducedDamage() {
        return reducedDamage;
    }

    public void setReducedDamage(boolean reducedDamage) {
        this.reducedDamage = reducedDamage;
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

    public void attack(Player player){
        if(player.isHasShield() && !this.reducedDamage){
            this.damage -= 2;
            if(this.damage <= 0){
                this.damage = 1;
            }
            this.reducedDamage = true;
        }
        if(player.getHealth() <= this.getDamage()){
            System.out.println("The monster deals fatal damage.");
            player.die();
        }else{
            System.out.println("The monster deals " + this.getDamage() + ".");
            player.setHealth(player.getHealth() - this.getDamage());
            System.out.println("Current health: " + player.getHealth() + "/10.");
        }
    }

    public void turn(Player player){
        if(this.getWait() == this.getWaitCounter()){
            this.attack(player);
            this.setWaitCounter(0);
        }else{
            this.setWaitCounter(this.getWaitCounter() + 1);
            System.out.println("The monster is waiting");
        }
    }
}