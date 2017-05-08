/* CalculateDirections.java
 * Written By: Meha Ahluwalia
 * Modified By:
 */

/*****************************************************************************
  * Converts output from Dijkstra's algorithm into text directions.
  *****************************************************************************/
import java.util.List;

public class CalculateDirections {
  
  /** Basic constructor creates an instance of CalculateDirections*/ 
  public CalculateDirections() {
  }
  
  /** 
   * Takes three parameters: a AdjListsGraph<Room> object, ArcInformation matrix,
   * and a List<Room> object, presumably the path output from Dijkstra's.
   * 
   * Takes consecutive pairs of Rooms from path, translates into arcs, then 
   * finds and retrieves corresponding text directions for each arc. 
   * 
   * @ returns a String: text directions to be presented to user. 
   */
  public String getTextDirections(AdjListsGraph<Room> roomsGraph, ArcInformation info, List<Room> path){
    //text to be returned, add greeting
    String pathToText = "Here are your directions: "; 
    //start numbering directions at 1
    int number = 1;
    //keep track of last direction reported
    String lastDirection = "";
    
    //loop through all the pairs of Rooms (arcs) in the path
    for (int i = 0; i<path.size()-1; i++) {
      int arcStartIndex = roomsGraph.getIndex(path.get(i)); //start of arc Room
      int arcEndIndex = roomsGraph.getIndex(path.get(i+1)); //end of arc Room
      //retrieve arc with start & end from ArcInformation matrix
      Arc arc = info.get(arcStartIndex, arcEndIndex); 
      String newDirection = arc.getDirections(); //corresponding text directions from arc
      
      //if last  direction was "continue," don't print new direction if also "continue" 
      if ((lastDirection.equals("continue")) && (newDirection.equals("continue"))) {
        continue; 
      }
      //new direction
      else {
        String text = "\n" + number + ". " + arc.getDirections();  //format new direction
        pathToText += text; //add new direction to text
        number++; //incriment number of directions for next loop
        lastDirection = newDirection; //set new direction as last direction for next loop
      }
    }
    
    //add closing statement 
    pathToText+= "\nThanks for using Sci-Maps!";
    return pathToText; 
  }
  
  
  /** Main method for testing.*/
  public static void main (String args[]) {
    AdjListsGraph<Room> roomGraph = new AdjListsGraph<Room>();
    //create some Rooms
    Room r1 = new Room("160A");
    Room r2 = new Room("160B");
    Room r3 = new Room("170");
    Room r4 = new Room("210");
    Room r5 = new Room("180");
    Room r6 = new Room("190");
    Room r7 = new Room("01");
    Room r8 = new Room("220");
    Room r9 = new Room("240");
    
    //add Rooms to graph
    roomGraph.addVertex(r1);
    roomGraph.addVertex(r2);
    roomGraph.addVertex(r3);
    roomGraph.addVertex(r4);
    roomGraph.addVertex(r5);
    roomGraph.addVertex(r6);
    roomGraph.addVertex(r7);
    roomGraph.addVertex(r8);
    roomGraph.addVertex(r9);
    
    //add Arcs between Rooms 
    roomGraph.addArc(r1, r2, "straight", 1);
    roomGraph.addArc(r2, r1, "straight", 1);
    roomGraph.addArc(r2, r3, "straight", 2);
    roomGraph.addArc(r3, r2, "straight", 2);
    roomGraph.addArc(r3, r7, "straight", 6);
    roomGraph.addArc(r7, r3, "straight", 6);
    roomGraph.addArc(r4, r7, "straight", 2);
    roomGraph.addArc(r7, r4, "straight", 2);
    roomGraph.addArc(r4, r9, "straight", 1);
    roomGraph.addArc(r9, r4, "straight", 1);
    roomGraph.addArc(r4, r8, "straight", 4);
    roomGraph.addArc(r8, r4, "straight", 4);
    roomGraph.addArc(r8, r9, "straight", 1);
    roomGraph.addArc(r9, r8, "straight", 1);
    roomGraph.addArc(r6, r3, "straight", 3);
    roomGraph.addArc(r3, r6, "straight", 3);
    roomGraph.addArc(r5, r6, "straight", 4);
    roomGraph.addArc(r6, r5, "straight", 4);
    
    //create new ArcInformation matrix
    ArcInformation info = new ArcInformation(20,20); 
    //add Arc information to matrix
    info.addInformation(roomGraph.getIndex(r1), roomGraph.getIndex(r2), "go", 1);
    info.addInformation(roomGraph.getIndex(r2), roomGraph.getIndex(r1), "continue", 1);
    info.addInformation(roomGraph.getIndex(r2), roomGraph.getIndex(r3), "continue", 2);
    info.addInformation(roomGraph.getIndex(r3), roomGraph.getIndex(r2), "left", 2);
    info.addInformation(roomGraph.getIndex(r3), roomGraph.getIndex(r7), "continue", 6);
    info.addInformation(roomGraph.getIndex(r7), roomGraph.getIndex(r3), "left", 6);
    info.addInformation(roomGraph.getIndex(r4), roomGraph.getIndex(r7), "continue", 2);
    info.addInformation(roomGraph.getIndex(r7), roomGraph.getIndex(r4), "right", 2);
    info.addInformation(roomGraph.getIndex(r4), roomGraph.getIndex(r9), "continue", 1);
    info.addInformation(roomGraph.getIndex(r9), roomGraph.getIndex(r4), "continue", 1);
    info.addInformation(roomGraph.getIndex(r4), roomGraph.getIndex(r8), "continue", 4);
    info.addInformation(roomGraph.getIndex(r8), roomGraph.getIndex(r4), "continue", 4);
    info.addInformation(roomGraph.getIndex(r8), roomGraph.getIndex(r9), "continue", 1);
    info.addInformation(roomGraph.getIndex(r9), roomGraph.getIndex(r8), "continue", 1);
    info.addInformation(roomGraph.getIndex(r6), roomGraph.getIndex(r3), "continue", 3);
    info.addInformation(roomGraph.getIndex(r3), roomGraph.getIndex(r6), "continue", 3);
    info.addInformation(roomGraph.getIndex(r5), roomGraph.getIndex(r6), "continue", 4);
    info.addInformation(roomGraph.getIndex(r6), roomGraph.getIndex(r5), "continue", 4);
    
    //run Dijkstra's 
    List<Room> path = Dijkstra.computePaths(roomGraph, r1, r4);
    System.out.println("Path: " + path); //print out path
    
    //create new Calculate Directions object
    CalculateDirections calculator = new CalculateDirections();
    //print out text directions
    System.out.println(calculator.getTextDirections(roomGraph, info, path));
    
  }
}