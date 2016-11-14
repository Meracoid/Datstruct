import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
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
        while(){

        }

    }
}