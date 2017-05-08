/* Dijkstra.java
 * Written By: Meha Ahluwalia
 * Modified By:
 */

/****************************************************************************************
  * Implements Dijkstra's algorithm to calculate the shortest path between Room 
  * objects of an AdjListGraph. 
  * 
  * Used the following resources for research: 
  *   1. MIT Math Presentation by Melissa Yan
  *      http://math.mit.edu/~rothvoss/18.304.3PM/Presentations/1-Melissa.pdf
  *   2. Cormen, Leiserson, Rivest, and Stein's "Introduction to Algorithms" textbook
  *      Third Edition- Sections 24.3 and 24.4
  *   3. Geeks For Geeks' Greedy Algorithms Set 7 Discussion
  *      www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
  * 
  * Modeled our implementation off of below & modified for our project purposes: 
  *   Github user breezedu's Open-Source project code
  *   https//github.com/breezedu/algorithmsDesignAnalysis/blob/master/Dijkstra.java
****************************************************************************************/

import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Dijkstra {
  
  /** Computes the shortest paths from given origin Room to all other Rooms.
    * Takes 2 parameters: the AdjListsGraph<Room> object & the origin Room.
    * @return a List of Rooms objects that form the desired path. */
  public static List<Room> computePaths(AdjListsGraph<Room> rooms, Room origin, Room destination) {
    origin.minDistance = 0.; //initially set minimum distance as 0 
    //create PriorityQueue to store all Rooms and explore sucessors/paths 
    PriorityQueue<Room> roomsQueue = new PriorityQueue<Room>();
    roomsQueue.add(origin); //add origin Room to Queue
    
    //look for alt, shorter paths until no more Rooms left in Queue
    while (!roomsQueue.isEmpty()) {
      //remove head of Queue 
      Room headRoom = roomsQueue.poll(); //design: use poll() to avoid ex
      //get of successors
      LinkedList<Arc> successors = rooms.getSuccessors(headRoom);
      // visit each sucessor
      for (Arc arc: successors) //all of the Room's successors
      {
        int newRoomIndex = arc.getEndVertex(); //arc destination Room's index    
        Room newRoom = rooms.getVertex(newRoomIndex); //get Room from index
        double weight = arc.getSeconds(); //get Arc weight (time) 
        
        //add Arc weight to cumulative distance
        double distanceThroughHeadRoom = headRoom.minDistance + weight; 
        
        //if cumulative distance is less than current minDistance, set as new minDistance
        if (distanceThroughHeadRoom < newRoom.minDistance) {
          roomsQueue.remove(newRoom);
          newRoom.minDistance = distanceThroughHeadRoom;
          newRoom.previous = headRoom;
          roomsQueue.add(newRoom);
        }
      }
    }
    
    //isolate the shortest path to destination Room
    List<Room> path = new ArrayList<Room>(); //create List path to be returned 
    //add each Room to path List, starting with destination
    for (Room room = destination; room != null; room = room.previous)
      path.add(room); 
    //reverse path order so origin --> destination 
    Collections.reverse(path);
    return path;
  }
  
  /** Main method for testing. */
  public static void main(String[] args)
  {
    //AdjListsGraph<Room> roomGraph = new AdjListsGraph<Room>();
    
    //create some Rooms
    //Room r1 = new Room("160A");
    //Room r2 = new Room("160B");
    //Room r3 = new Room("170");
    //Room r4 = new Room("210");
    //Room r5 = new Room("180");
    //Room r6 = new Room("190");
    //Room r7 = new Room("01");
    //Room r8 = new Room("220");
    //Room r9 = new Room("240");
   
    //add Rooms to graph
    //roomGraph.addVertex(r1);
    //roomGraph.addVertex(r2);
    //roomGraph.addVertex(r3);
    //roomGraph.addVertex(r4);
    //roomGraph.addVertex(r5);
    //roomGraph.addVertex(r6);
    //roomGraph.addVertex(r7);
    //roomGraph.addVertex(r8);
    //roomGraph.addVertex(r9);
    
    //add Arcs to Graph
    //roomGraph.addArc(r1, r2, "straight", 1);
    //roomGraph.addArc(r2, r1, "straight", 1);
    //roomGraph.addArc(r2, r3, "straight", 2);
    //roomGraph.addArc(r3, r2, "straight", 2);
    //roomGraph.addArc(r3, r7, "straight", 6);
    //roomGraph.addArc(r7, r3, "straight", 6);
    //roomGraph.addArc(r4, r7, "straight", 2);
    //roomGraph.addArc(r7, r4, "straight", 2);
    //roomGraph.addArc(r4, r9, "straight", 1);
    //roomGraph.addArc(r9, r4, "straight", 1);
    //roomGraph.addArc(r4, r8, "straight", 4);
    //roomGraph.addArc(r8, r4, "straight", 4);
    //roomGraph.addArc(r8, r9, "straight", 1);
    //roomGraph.addArc(r9, r8, "straight", 1);
    //roomGraph.addArc(r6, r3, "straight", 3);
    //roomGraph.addArc(r3, r6, "straight", 3);
    //roomGraph.addArc(r5, r6, "straight", 4);
    //roomGraph.addArc(r6, r5, "straight", 4);
   
    //run Dijkstra!
    //List<Room> path = Dijkstra.computePaths(roomGraph, r1, r4);
    //System.out.println("Path: " + path);


  }
}
