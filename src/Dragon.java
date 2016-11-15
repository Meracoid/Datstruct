public class Dragon extends Monster{
    /**
     * @author: meracoid
     * @date: 11/10/16
     */
    public Dragon(){
        super.setHealth(10);
        super.setWait(5);
        super.setWaitCounter(2);
        super.setDamage(5);
    }

    public void attack(Player player){
        if(player.getHealth() <= 5){
            player.die();
        }else{
            player.setHealth(player.getHealth() - 5);
        }
    }

    public void turn(Player player){
        if(super.getWait() == 5){
            this.attack(player);
            super.setWaitCounter(0);
        }else{
            super.setWaitCounter(super.getWaitCounter() + 1);
        }
    }
}