public class Goblin extends Monster{
    /**
     * @author: meracoid
     * @date: 11/10/16
     */
    public Goblin(){
        this.health = 5;
        this.wait = 2;
        this.damage = 1;
        this.waitCounter = 0;
    }

    public void attack(Player player){
        if(player.getHealth() <= 2){
            player.die();
        }else{
            player.setHealth(player.getHealth() - 2);
        }
    }

    public void turn(Player player){
        if(super.getWait() == super.getWaitCounter()){
            this.attack(player);
            super.setWaitCounter(0);
        }else{
            super.setWaitCoutner(super.getWaitCounter() + 1);
        }
    }
}