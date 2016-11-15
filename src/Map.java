public class Map{
    private final int[][] map = {{0,0,3,0,4},{0,0,1,0,1},{2,0,1,0,1},{1,1,1,1,1},{0,0,-1,0,0}};
    private String[] roomStartItems = {"Shield", "HealthPotion", "Compass"};
    private String[] room2Items = {"HealthPotion", "DragonFlute"};
    private String[] room3Items = {"Gold"};
    private String[] room4Items = {"Sword", "HealthPotion"};
    public Map(){
        return;
    }

    public int getDungeonRoom(int x, int y){
        return map[x][y];
    }

    public String[] getRoomItems(int roomNumber){
        switch(roomNumber){
            case -1:
                return roomStartItems;
            case 2:
                return room2Items;
            case 3:
                return room3Items;
            case 4:
                return room4Items;
            default:
                System.out.println("You are in a hallway, no items in the hallway");
                return null;
        }
    }

}