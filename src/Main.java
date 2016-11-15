import java.util.Scanner;

public class Main{
    private static Scanner console = new Scanner(System.in);
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
            System.out.println(player.getLocationX() + " " + player.getLocationY());
            switch(roomType){
                case 1:
                    room1(player, map);
                    break;
                case 2:
                    room2(player, map);
                    break;
                case 4:
                    room4(player, map);
                    break;
                case -1:
                    roomStart(player, map);
                    break;
            }
        }
        if(player.getHealth() > 0) {
            System.out.println("THE EXIT IS BEING BLOCKED BY A DRAGON.");
            fight(new Dragon(), player, map);
        }else{
            System.out.println("GAME OVER");
        }
    }

    public static void roomStart(Player player, Map map){
        int currentX = player.getLocationX();
        int currentY = player.getLocationY();
        System.out.println("You are in a room with a door to the north");
        while(currentX == player.getLocationX() && currentY == player.getLocationY()) {
            System.out.println("Type a command: ");
            player.command(console.nextLine(), map);
        }
    }

    public static void room1(Player player, Map map){
        int currentX = player.getLocationX();
        int currentY = player.getLocationY();
        System.out.println("You are in a hallway");
        while(currentX == player.getLocationX() && currentY == player.getLocationY()) {
            System.out.println("Type a command: ");
            player.command(console.nextLine(), map);
        }
    }

    public static void room2(Player player, Map map){
        int currentX = player.getLocationX();
        int currentY = player.getLocationY();
        System.out.println("There's mysterious treasure, but a goblin is blocking you");
        fight(new Goblin(), player, map);
        while(currentX == player.getLocationX() && currentY == player.getLocationY() && player.getHealth() != 0) {
            System.out.println("Type a command: ");
            player.command(console.nextLine(), map);
        }
    }

    public static void room4(Player player, Map map){
        int currentX = player.getLocationX();
        int currentY = player.getLocationY();
        System.out.println("It seems there's a sword in the room, but a goblin is guarding it");
        fight(new Goblin(), player, map);
        while(currentX == player.getLocationX() && currentY == player.getLocationY() && player.getHealth() != 0) {
            System.out.println("Type a command: ");
            player.command(console.nextLine(), map);
        }
    }

    public static void fight(Monster monster, Player player, Map map){
        player.setInFight(true);
        System.out.println("Type a command: ");
        player.command(console.nextLine(), map);
        monster.turn(player);
        if(player.getHealth() > 0 || monster.getHealth() > 0){
            fight(monster, player, map);
        }else if(player.getHealth() == 0){
            System.out.println("GAME OVER");
        }else{
            System.out.println("YOU BEAT THE MONSTER!");
            player.setInFight(false);
        }
    }
}