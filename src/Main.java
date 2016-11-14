import java.util.Scanner;

public class Main{
    private Scanner console = new Scanner(System.in);
    public static void main(String[] args){
        System.out.println("Type a number 1 to play, 0 to exit");
        int seed = console.nextInt();
        while(seed != 0){
            playGame();
            System.out.println("Type a number 1 to play again, 0 to exit");
            seed = console.nextInt();
        }
        System.out.println("Thanks for playing");
        console.close();
    }

    public static void playGame(){
        Player player = new Player();
        Map map = new Map();
        while(map.getDungeonRoom(player.getLocationX(), player.getLocationY()) != 3 && player.getHealth() > 0){
            int roomType = map.getDungeonRoom(player.getLocationX(), player.getLocationY());
            switch(roomType){
                case 1:
                    room1(player);
                    break;
                case 2:
                    room2(player);
                    break;
                case 4:
                    room4(player);
                    break;
                case default:
                    roomStart(player);
                    break;
            }
        }
        if(player.getHealth() > 0) {
            System.out.println("THE EXIT IS BEING BLOCKED BY A DRAGON.");
            fight(new Dragon(), player);
        }else{
            System.out.println("GAME OVER");
        }
    }

    public static void roomStart(Player player){
        System.out.println("You are in a room with a door to the north");
        System.out.println("Type a command: ");
        player.command(console.nextLine());
    }

    public static void fight(Monster monster, Player player){
        System.out.println("Type a command: ");
        player.command(console.nextLine());
        monster.turn(player);
        if(player.getHealth() > 0 || monster.getHealth() > 0){
            fight(monster, player);
        }else if(player.getHealth() == 0){
            System.out.println("GAME OVER");
        }else{
            System.out.println("YOU BEAT THE MONSTER!");
        }
    }
}