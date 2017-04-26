/* Room.java - defines one Room object 
 * Part of SciMaps
 * Written by: Annabel Rothschild
 * Modified by:
 */

public class MapsBuilder extends GraphBuilder<Room>{
  
  public Room createOneThing(String s){
    Room r = new Room(s);
    return r;
  }
  
  
}