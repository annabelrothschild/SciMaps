/* AdjListsGraph.java    
 * Written By: Annabel Rothschild
 * Modified By:
 * Part of SciMaps
 * Written Originally for: Pset07 task1 - Graph with Adjacency List Implementation
 */

import java.util.*;
import java.io.*;

/* TOP OF FILE NOTES:
 * - NO LONGER IMPLEMENTS GRAPH.JAVA BECAUSE WE HAVE SO MANY SPECIFICS
 * - NO ADDEDGE: NEED TO ADD SINGULAR ARCS BECAUSE EACH ARC HAS UNIQUE INFORMATION - DIRECTIONS
 * - 2D ARRAY SIZE IS HARDCODED BECAUSE WE LOAD ALL THE EDGES OURSELVES - WILL KNOW THE SIZE OF THE GRAPH WE ARE ADDING
 */ 

//need to make all instance variables private after testing

public class AdjListsGraph<T>{
  
  private Vector<T> verticies;
  /* Added by Annabel 04/26 to account for arc directions array
   */ 
  private ArcInformation arcs;
  /*Added by Annabel 04/27 - account for weights
   */
  
  public AdjListsGraph(){
    
    verticies = new Vector<T>();
    //default size - can be updated
    //arcs = new ArcInformation(5, 5);
    
  }
  
  /* Initialize arcs to hold space for every possible arc
   */
  public void initializeArcs(){
    
    arcs = new ArcInformation(verticies.size(), verticies.size());
    
  }
  
  /* Check if graph is undirected (if for every arc there is one going between the same 
   * verticies in the opposite direction)
   * @return whether or not graph is undirected
   */ 
  public boolean isUndirected(){
    //check each arc in the collection by going through each Arc in array
    for (int t = 0; t<arcs.numRows(); t++){
      for (int u = 0; u<arcs.numColumns(t); u++){ 
        //if one arc does not have a reciprocal arc, the entire graph is undirected
        //handle possibility that array does not have as many columns as it does rows
        try{ 
          if (!(arcs.get(t,u).getStartVertex() == arcs.get(u,t).getStartVertex())
                && (arcs.get(t,u).getEndVertex() == arcs.get(u,t).getEndVertex())){
            return false;
        }
        } catch (ArrayIndexOutOfBoundsException e){
        }
      }
    }
    return true;
  }
  
  /* Check if graph is empty
   * @return whether or not graph is empty
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
   * only go in one direction
   * @return whether or not an arc exists between two given verticies
   */ 
  
  //UPDATED 05/06 - our graph only has arcs so don't need to check if goes the other way
  public boolean isArc (T vertex1, T vertex2){
    return (!(arcs.get(getIndex(vertex1),getIndex(vertex2)) == null));
  }
  
  /* Add arc to graph assuming that both verticies are in the graph and that arc-to-be-added doesn't
   * already exist in the graph. Overrides a pre-existing arc in event of collision.
   * @return void
   */ 
  public void addArc (T vertex1, T vertex2, String direction, int distance){
    //check that both vertices exist and that arc is null
    if (verticies.contains(vertex1) && verticies.contains(vertex2)){
      arcs.addInformation(getIndex(vertex1),getIndex(vertex2),direction,distance);
    }
  }
  
  /* Check if an edge exists between two verticies - meaning there are two reciprocal arcs
   * @return whether or not check exists
   */ 
//condensed version because all edges are arcs in our graph
  public boolean isEdge (T vertex1, T vertex2){
    return isArc(vertex1, vertex2);
//    return (!(arcs.get(getIndex(vertex1), getIndex(vertex2) == null)) || 
//            (!(arcs.get(getIndex(vertex2), getIndex(vertex1)) == null)));
  } 
  
  /* Remove vertex from verticies along with removing all edges that point from, or too, that vertex
   * @return void
   */ 
  public void removeVertex (T vertex){
    System.out.println("Method is unimplemented");
//    arcs.(getIndex(vertex));
//    verticies.remove(vertex);
//    for (int i = 0; i<arcs.size(); i++){
//      removeArc(verticies.get(i), vertex);
//    }
  }
  
  /* Remove an arc by going through each arc in LinkedList corresponding to first vertex
   * and checking if it goes to the second vertex and if so remove that arc
   * @return void
   */ 
  public void removeArc (T vertex1, T vertex2){
    arcs.removeArc(getIndex(vertex1),getIndex(vertex2));
//    for (int c = 0; c<arcs.get(getIndex(vertex1)).size(); c++){
//      if (arcs.get(getIndex(vertex1)).get(c) == vertex2){
//        arcs.get(getIndex(vertex1)).remove(c);
//      }
//    }
  }
  
  /* Add a vertex, if not already in verticies, by adding it to verticies vector and creating
   * new LinkedList element in arcs
   * @return void
   */ 
  public void addVertex(T vertex){
    if (!verticies.contains(vertex)){
      verticies.add(vertex);
      //arcs.add(new LinkedList<T>());
    }
  }
  
//  /* Add edge to graph if both verticies exist by adding reciprocal arcs as long as those arcs
//   * don't already exist - check individually to add one arc if other already exists
//   * @return void
//   */ 
//  public void addEdge (T vertex1, T vertex2, String direction, ){
//    addArc(vertex1, vertex2, distance, );
//    addArc(vertex2, vertex1);
//  }
  
  /* Remove edge by removing both of the reciprocal arcs assuming and edge exists between the two verticies
   * @return void
   */ 
  public void removeEdge (T vertex1, T vertex2){
    arcs.removeArc(getIndex(vertex1), getIndex(vertex2));
//    if (isEdge(vertex1, vertex2)){
//      arcs.get(getIndex(vertex1)).remove(vertex2);
//      arcs.get(getIndex(vertex2)).remove(vertex1);
//    }
  }
  
  /* Check for successors to given vertex by returning corresponding LinkedList of arcs starting at 
   * given vertex. Assumes a vertex can be its own successor because of slings.
   * @return LinkedList<T> of sucessors
   */ 
  public LinkedList<Arc> getSuccessors(T vertex){
    LinkedList<Arc> kids = new LinkedList<Arc>();
    for (int k = 0; k < arcs.numColumns(getIndex(vertex)); k++){
      if (!(arcs.get(getIndex(vertex),k) == null)){
        kids.add(arcs.get(getIndex(vertex),k));
      }
    }
    return kids;
  }
  
  /* Check for predecessors to given vertex by checking all LinkedLists in arcs to add any arc that
   * points to given vertex to new LinkedList of predecessors.
   * @return LinkedList<T> of predecessors
   */ 
  public LinkedList<Arc> getPredecessors(T vertex){ //can do w/o nested for loop?
    LinkedList<Arc> preds = new LinkedList<Arc>();
    for (int k = 0; k < arcs.numRows(); k++){
      for (int j = 0; j < arcs.numColumns(getIndex(vertex)); j++){
        if (!(arcs.get(k,j).getEndVertex() == getIndex(vertex))){
          preds.add(arcs.get(k,j));
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
   * @return index of given vertex
   */ 
  public int getIndex(T vertex){
    return verticies.indexOf(vertex);
  }
  
  /* Format AdjListsGraph objects for printing
   * @return nicely formatted String of AdjListsGraph object
   */ 
  public String toString(){
    String result = verticies.toString() + "\n" + arcs.toString();
    return result;
  }
  /* Access a particular vertex based on its index number
   * @return that vertex
   */ 
  public T getVertex(int index){
    return verticies.get(index-1);
  }
  
  /* Added by Annabel 04/26 - account for need to change direction or seconds in arcInformation
   */ 
  public void changeArcInformation(T from, T to, String directions, int seconds){
    
    arcs.get(getIndex(from), getIndex(to)).setDirections(directions);
    arcs.get(getIndex(from), getIndex(to)).setSeconds(seconds);
    
  }
  
  public static void main(String[] args){
    
    AdjListsGraph rooms = new AdjListsGraph();
    
    Room hello = new Room("160b");
    Room there = new Room("170b");
    rooms.addVertex(hello);
    rooms.addVertex(there);
    
    //rooms.addArc(hello, there);
    
    //rooms.saveToTGF("Rooms");
    System.out.println("Rooms.tgf was just saved to show initial state");
    
    MapsBuilder roomx = new MapsBuilder();
    AdjListsGraph roomxGraph = roomx.build("Room.tgf");
    System.out.println("Building new AdjListsGraph object from .tgf file; result should match"
                         + " final state above");
    System.out.println("Maps: " + roomxGraph);
    
    //roomxGraph.arrayToString();


  }
}

