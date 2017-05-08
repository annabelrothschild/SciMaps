/* Room.java - defines one Room object 
 * Part of SciMaps
 * Written by: Annabel Rothschild
 * Modified by:
 */

public class Room implements Comparable<Room> {
  
  String name;
  // Added by Meha 5/7 for use in Dijkstra.java
  public double minDistance = Double.POSITIVE_INFINITY; //initial minimum distance to this Room is infinity (use in Dijkstra)
  public Room previous; //in shortest path, another Room that immediately preceeds current Room (use in Dijkstra)
  
  /* Constructor just takes in the name of the room
   */ 
  public Room (String n){
    
    name = n;
    
  }
  
  /* Get name of room
   * @ return name of room
   */
  public String getName() {
    return name; 
  }
  
  /* Print room name nicely
   * @ return room name
   */ 
  public String toString(){
    
    //String roomInfo = "Room: " + name;
    
    //Modified by Meha 5/7 for cleaner path translation Dijkstra-->CalculateDirections
    String roomInfo = name;
    return roomInfo;
  }
  
  /* Compares two Room objects by their minDistances
   * Added by Meha 5/7 to compare paths for use in Dijkstra.java
   * @ return an integer: 1 if greater than, 0 if equal, -1 if less than
   */ 
  public int compareTo(Room other)
    {
        return Double.compare(minDistance, other.minDistance);
    }
  
  /* Main method for testing */ 
  public static void main(String[] args){
    
    //Room myRoom = new Room("160A");
    //System.out.println(myRoom);
  }
 
  
}
