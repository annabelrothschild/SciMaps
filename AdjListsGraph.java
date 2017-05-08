/* AdjListsGraph.java    
 * Written By: Annabel Rothschild
 * Modified By:
 * Part of SciMaps - Creates main graph to be used by Dijkstra's
 */

import java.util.*;
import java.io.*;

/* TOP OF FILE NOTES:
 * - NO LONGER IMPLEMENTS GRAPH.JAVA BECAUSE WE HAVE SO MANY SPECIFICS
 * - 2D IS HARDCODED IN DEFAULT, IS REPLACED WHEN LOADED FROM TGF
 */ 

public class AdjListsGraph<T>{
  
  private Vector<Room> verticies;
  private ArcInformation arcs;
  
  public AdjListsGraph(){
    
    verticies = new Vector<Room>();
    arcs = new ArcInformation(50,50); //Changed by Meha 5/7 to account for big test graph size
                                      //Note: This only matters for our manually-created graphs. 
                                      //Our program exclusively uses the MapsBuilder, which automatically
                                      //sets the correct size. 
    
  }
  
  /* Initialize arcs to hold space for every possible arc - totalNumVerticies x totalNumVerticies
   */
  public void initializeArcs(){
    
    arcs = new ArcInformation(verticies.size(), verticies.size());
    
  }
  
  /* Get a particular vertex given just its name
   * @ param room number
   * @ return Room object or null if no Room with that name exists
   */ 
  public Room getRoom(String name){
    for (int r = 0; r < verticies.size() ; r ++){   
      Room aRoom = (Room) verticies.get(r);
      if (aRoom.getName().equals(name)){
        return aRoom;
      }
    }
    return null;
  }
  
  /* Check if graph is empty
   * @ return whether or not graph is empty
   */ 
  public boolean isEmpty(){
    return (verticies.size() == 0);
  }
  
  /* Get the total number of verticies in the graph
   * @return size of verticies Vector
   */ 
  public int getNumVertices(){
    return verticies.size();
  }
  
  /* Get the total number of arcs in the graph
   * @return number of arcs
   */ 
  public int getNumArcs(){
    int totalArcs = 0;
    //need to check for size of each LinkedList in arcs Vector, add size to total
    for (int r = 0; r < arcs.numRows(); r++){
      for (int c = 0; c < arcs.numColumns(r); c++){
        if (!(arcs.get(r,c) == null)){
          totalArcs ++;
        }
      }
    }
    return totalArcs;
  }
  
  /* Check if an arc exists between two verticies - assumes that edge must be directed so can 
   * only go in one direction. Returns true only if an edge exists between two the given verticies
   * in only one direction.
   * @ return whether or not an arc exists between two given verticies
   */ 
  public boolean isArc (Room vertex1, Room vertex2){
    return (!(arcs.get(getIndex(vertex1),getIndex(vertex2)) == null) &&
  (arcs.get(getIndex(vertex2),getIndex(vertex1)) == null));
  }
  
  /* Add arc to graph assuming that both verticies are in the graph and that arc-to-be-added doesn't
   * already exist in the graph. Overrides a pre-existing arc in event of collision.
   * @ return void
   */ 
  public void addArc (Room vertex1, Room vertex2, String direction, int distance){
    if (verticies.contains(vertex1) && verticies.contains(vertex2)){
      arcs.addInformation(getIndex(vertex1),getIndex(vertex2),direction,distance);
    }
  }
  
  /* Check if an edge exists between two verticies - meaning there are two reciprocal arcs
   * @ return whether or not check exists
   */ 
  public boolean isEdge (Room vertex1, Room vertex2){
    return (!(arcs.get(getIndex(vertex1),getIndex(vertex2)) == null));
  } 
  
  /* Remove vertex from verticies along with removing all edges that point from, or too, that vertex. 
   * First, remove (set to null) all arcs that start at vertex-to-be-removed. Then do the same for all
   * arcs pointing to to-be-removed vertex. Finally, remove the vertex itself.
   * @ return void
   */ 
  public void removeVertex (Room vertex){
    //set all arcs originating from that vertex to null
    for (int i = 0; i < arcs.numRows() ; i ++ ){    
      if (i==getIndex(vertex)){
        arcs.removeFromVertex(getIndex(vertex));
      }
    for (int k = 0; k < arcs.numColumns(i); k ++){
      if (!(arcs.get(i,k) == null)){
        if (arcs.get(i,k).getEndVertex() == getIndex(vertex)){
          System.out.println("wants to remove " + i + ", " + k);
          arcs.removeArc(i,k);
        }
      }
    }
    }
    verticies.remove(vertex);
  }
  
  /* Remove an arc by going through each arc in LinkedList corresponding to first vertex
   * and checking if it goes to the second vertex and if so remove that arc
   * @ return void
   */ 
  public void removeArc (Room vertex1, Room vertex2){
    arcs.removeArc(getIndex(vertex1),getIndex(vertex2));
  }
  
  /* Add a vertex, if not already in verticies, by adding it to verticies vector and creating
   * new LinkedList element in arcs
   * @ return void
   */ 
  public void addVertex(Room vertex){
    if (!verticies.contains(vertex)){
      verticies.add(vertex);
    }
  }
  
  /* Remove edge by removing both of the reciprocal arcs assuming and edge exists between the two verticies
   * @return void
   */ 
  public void removeEdge (Room vertex1, Room vertex2){
    arcs.removeArc(getIndex(vertex1), getIndex(vertex2));
    arcs.removeArc(getIndex(vertex2), getIndex(vertex1));
  }
  
  /* Check for successors to given vertex by returning corresponding LinkedList of arcs that begin at given
   * Room. Assumes a vertex can be its own successor because of slings.
   * @return LinkedList<T> of sucessors
   */ 
  public LinkedList<Arc> getSuccessors(Room vertex){
    LinkedList<Arc> kids = new LinkedList<Arc>();
    //check each member of the header row - 
    for (int k = 0; k < arcs.numColumns(getIndex(vertex)); k++){
      if (!(arcs.get(getIndex(vertex),k) == null)){
        kids.add(arcs.get(getIndex(vertex),k));
      }
    }
    return kids;
  }
  /* Check for successors to given vertex by returning corresponding LinkedList of rooms with an arc from
   * the given vertex. Assumes a vertex can be its own successor because of slings.
   * @return LinkedList<T> of sucessors
   */ 
  public LinkedList<Room> getChildRooms(Room vertex){
    LinkedList<Room> kids = new LinkedList<Room>();
    //check each member of the header row - 
    for (int k = 0; k < arcs.numColumns(getIndex(vertex)); k++){
      if (!(arcs.get(getIndex(vertex),k) == null)){
        Room oneChild = (Room) verticies.get(arcs.get(getIndex(vertex),k).getEndVertex());
        kids.add(oneChild);
      }
    }
    return kids;
  }
  /* Check for parent rooms to given vertex by returning corresponding LinkedList of rooms with an arc from
   * the given vertex. Assumes a vertex can be its own successor because of slings.
   * @return LinkedList<T> of sucessors
   */ 
  public LinkedList<Room> getParentRooms(Room vertex){
    LinkedList<Room> parents = new LinkedList<Room>();
    //check each member of the header row - 
    for (int k = 0; k < arcs.numRows(); k++){
      for (int j = 0; j < arcs.numColumns(getIndex(vertex)); j++){
        if (!(arcs.get(k,j) == null)){ //if there is an arc
          //if the end vertex is the same as the one we want
          if (arcs.get(k,j).getEndVertex() == getIndex(vertex)){
            parents.add((Room)verticies.get(k));
          }
        }
      }
    }
    return parents;
  }
  
  /* Check for predecessors to given vertex by checking all LinkedLists in arcs to add any arc that
   * points to given vertex to new LinkedList of predecessors.
   * @return LinkedList<T> of predecessors
   */ 
  public LinkedList<Arc> getPredecessors(Room vertex){ //can do w/o nested for loop?
    LinkedList<Arc> preds = new LinkedList<Arc>();
    for (int k = 1; k < arcs.numRows(); k++){
      for (int j = 1; j < arcs.numColumns(getIndex(vertex)); j++){
        if (!(arcs.get(k,j) == null)){
        if (arcs.get(k,j).getEndVertex() == getIndex(vertex)){
          preds.add(arcs.get(k,j));
        }
        }
      }
    }
      return preds;
    }
  
  /* Save graph to tgf format by creating new file. Write to that file by creating one string of all verticies
   * and arcs.
   * @parameter tgf file name
   * @return void
   */ 
  public void saveToTGF(String tgf_file_name){
    try{
      PrintWriter writer = new PrintWriter(new FileWriter(tgf_file_name + ".tgf"));
      String result = "";
      //handle each vertex in verticies
      for (int a = 0; a < verticies.size(); a++){
        //add 1 to placeholder (a) because tgf format starts with 1, not 0
        result += a+1 + " " + verticies.get(a) + "\n";
      }
      result += "#\n";
      //handle each set of arcs in arcs
      for (int b = 0; b < arcs.numRows(); b++){ 
        for (int c = 0 ; c < arcs.numColumns(b) ; c++){ 
          //again add 1 to placeholders to account for tgf formatting
          if (!(arcs.get(b,c) == null)){
          result += (b+1) + " " + (arcs.get(b,c).getEndVertex()+1) + "\n";
          }
        }
      }
      System.out.println("Saving the graph into " + tgf_file_name + ".tgf");
      writer.println(result); 
      writer.close();
    }
    catch (IOException e){
      System.out.println(e);
    }
  }
  /* Additional Helper Function:
   * Get the index of a given vertex to use in arcs 
   * @ return index of given vertex
   */ 
  public int getIndex(Room vertex){
    return verticies.indexOf(vertex);
  }
  
  /* Format AdjListsGraph objects for printing
   * @ return nicely formatted String of AdjListsGraph object
   */ 
  public String toString(){
    String result = verticies.toString() + "\n" + arcs.toString();
    result += "\nSize: " + arcs.numRows();
    return result;
  }
  /* Access a particular vertex based on its index number
   * @ return that vertex
   */ 
  public Room getVertex(int index){
    return (Room) verticies.get(index);
  }
  
  /* Allows for changes to arc information - never used in run of the program, only in event of backend changes
   * @param new travel time and/or directions for Arc
   */ 
  public void changeArcInformation(Room from, Room to, String directions, int seconds){
    
    arcs.get(getIndex(from), getIndex(to)).setDirections(directions);
    arcs.get(getIndex(from), getIndex(to)).setSeconds(seconds);
    
  }
  
  /* Get arc between two rooms assuming it exsists
   * @ params rooms at either end of arc
   * @ return Arc between the two, null if it doesn't exist
   */ 
  public Arc getArc(Room from, Room to){
    return arcs.get(getIndex(from),getIndex(to));
  }
  
  /* Getter for ArcInformation object (arcs) - used only for testing purposes
   * @ return arcs 
   */ 
  public ArcInformation getArcs(){
    return arcs;
  }
  
  public static void main(String[] args){
    
    //AdjListsGraph rooms = new AdjListsGraph();
    
    //Room hello = new Room("160b");
    //Room there = new Room("170b");
    //rooms.addVertex(hello);
    //rooms.addVertex(there);
    
    //rooms.addArc(hello, there);
    
    //rooms.saveToTGF("testSaveToTGF");
    //System.out.println("Rooms.tgf was just saved to show initial state");
    
    //MapsBuilder roomx = new MapsBuilder();
    //AdjListsGraph roomxGraph = roomx.build("allrooms.tgf");
    //System.out.println("Building new AdjListsGraph object from .tgf file; result should match"
                         //+ " final state above");
    //System.out.println("arc: " + roomxGraph.getArc(roomxGraph.getRoom("S127"),roomxGraph.getRoom("E135")));
    //System.out.println("isArc: " + roomxGraph.isEdge(roomxGraph.getRoom("S127"),roomxGraph.getRoom("E135")));
    //System.out.println("Maps: " + roomxGraph);
    
    //roomxGraph.arrayToString();
    
    //System.out.println("isUndirected: " + roomxGraph.isUndirected());
    //System.out.println("num arcs: " + roomxGraph.getNumArcs());
    //System.out.println("isEdge: " + roomxGraph.isEdge(roomxGraph.getRoom("160A"), roomxGraph.getRoom("160B")));
    //roomxGraph.addArc(roomxGraph.getRoom("01"), roomxGraph.getRoom("210"), "leave elevator", 45);
    //System.out.println("isArc: " + roomxGraph.isArc(roomxGraph.getRoom("01"), roomxGraph.getRoom("210")));
    //System.out.println("getKids: " + roomxGraph.getChildRooms(roomxGraph.getRoom("170")));
    //System.out.println("getParents: " + roomxGraph.getParentRooms(roomxGraph.getRoom("170")));
    //System.out.println("getSuccessors: " + roomxGraph.getSuccessors(roomxGraph.getRoom("170")));
    //System.out.println("getPredecessors: " + roomxGraph.getPredecessors(roomxGraph.getRoom("170")));
    //roomxGraph.removeVertex(roomxGraph.getRoom("190"));
    //System.out.println("after removing 01: " + roomxGraph);


  }
}

