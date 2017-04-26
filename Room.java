/* Room.java - defines one Room object 
 * Part of SciMaps
 * Written by: Annabel Rothschild
 * Modified by:
 */

public class Room{
  
  String name;
  
  /* Constructor just takes in the name of the room
   */ 
  public Room (String n){
    
    name = n;
    
  }
  
  /* Print room name nicely
   * @ return room name
   */ 
  public String toString(){
    
    String roomInfo = "Room: " + name;
    return roomInfo;
  }
  
  public static void main(String[] args){
    
    Room myRoom = new Room("160A");
    System.out.println(myRoom);
  }
 
  
}