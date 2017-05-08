/* Room.java - defines one Room object 
 * Part of SciMaps
 * Written by: Annabel Rothschild
 * Modified by:
 */

public class Room implements Comparable<Room> {
  
  String name;
  public double minDistance = Double.POSITIVE_INFINITY; 
  public Room previous; 
  
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
    
//    String roomInfo = "Room: " + name; //MEHA MODIFY
    String roomInfo = name;
    return roomInfo;
  }
  
  public int compareTo(Room other)
    {
        return Double.compare(minDistance, other.minDistance);
    }
  
  public static void main(String[] args){
    
    Room myRoom = new Room("160A");
    System.out.println(myRoom);
  }
 
  
}