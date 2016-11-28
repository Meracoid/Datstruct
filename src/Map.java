import java.util.Arrays;

public class Map{
    private final int[][] map = {{0,0,3,0,4},{0,0,1,0,1},{2,0,1,0,1},{1,1,1,1,1},{0,0,-1,0,0}};
    private String[] roomStartItems = {"shield", "health-potion", "map"};
    private String[] room2Items = {"health-potion"};
    private String[] room3Items = {"gold"};
    private String[] room4Items = {"sword", "health-potion"};
    public Map(){
        return;
    }

    public int getDungeonRoom(int x, int y){
        if(x<0||y<0||x>=map.length||y>=map.length){ 
            return 0;
        }
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
                String[] nothing = {""};
                return nothing;
        }
    }

    public boolean loseItem(int roomNumber, String item){
        String[] roomItems = this.getRoomItems(roomNumber);
        if(this.hasItem(roomNumber, item)){
            for(int i = 0; i < roomItems.length; i++){
                if(roomItems[i].equals(item)){
                    roomItems[i] = "";
                }
            }
            return true;
        }
        return false;

    }

    public boolean hasItem(int roomNumber, String item){
        String[] roomItems = this.getRoomItems(roomNumber);
        return Arrays.asList(roomItems).contains(item.toLowerCase());
    }

}
