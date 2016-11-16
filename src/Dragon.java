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
        super.setReducedDamage(false);
    }
}