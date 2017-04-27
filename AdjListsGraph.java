/* AdjListsGraph.java    
 * Written By: Annabel Rothschild
 * Modified By:
 * Part of SciMaps
 * Written Originally for: Pset07 task1 - Graph with Adjacency List Implementation
 */

import java.util.*;
import java.io.*;

public class AdjListsGraph<T> implements Graph<T>{
  
  private Vector<T> verticies;
  private Vector<LinkedList<T>> arcs;
  /* Added by Annabel 04/26 to account for arc directions array
   */ 
  private String[][] arcDirections;
  private int arcArrayCount;
  
  public AdjListsGraph(){
    
    verticies = new Vector<T>();
    arcs = new Vector<LinkedList<T>>();
    arcDirections = new String[1][3];
    arcArrayCount ++;
    
  }
  /* Check if graph is undirected (if for every arc there is one going between the same 
   * verticies in the opposite direction)
   * @return whether or not graph is undirected
   */ 
  public boolean isUndirected(){
    //check each arc in the collection by going through each LinkedList in the Vector
    for (int t = 0; t<arcs.size(); t++){
      for (T arc : arcs.get(t)){ 
        //if one arc does not have a reciprocal arc, the entire graph is undirected
        if (!(arcs.get(getIndex(arc)).contains(verticies.elementAt(t)))){
          return false;
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
    for (LinkedList<T> oneSet: arcs){
      totalArcs+= oneSet.size();
    }
    return totalArcs;
  }
  
  /* Check if an arc exists between two verticies - assumes that edge must be directed so can 
   * only go in one direction
   * @return whether or not an arc exists between two given verticies
   */ 
  public boolean isArc (T vertex1, T vertex2){
    return (arcs.get(getIndex(vertex1)).contains(vertex2) && !arcs.get(getIndex(vertex2)).contains(vertex1));
  }
  
  /* Add arc to graph assuming that both verticies are in the graph and that arc-to-be-added doesn't
   * already exist in the graph. 
   * @return void
   */ 
  public void addArc (T vertex1, T vertex2){
    if (verticies.contains(vertex1) && verticies.contains(vertex2) && !arcs.get(getIndex(vertex1)).contains(vertex2)){
      arcs.get(getIndex(vertex1)).add(vertex2);
    }
  }
  
  /* Check if an edge exists between two verticies - meaning there are two reciprocal arcs
   * @return whether or not check exists
   */ 
  public boolean isEdge (T vertex1, T vertex2){
    return (arcs.get(getIndex(vertex1)).contains(vertex2) && arcs.get(getIndex(vertex2)).contains(vertex1));
  } 
  
  /* Remove vertex from verticies along with removing all edges that point from, or too, that vertex
   * @return void
   */ 
  public void removeVertex (T vertex){
    arcs.remove(getIndex(vertex));
    verticies.remove(vertex);
    for (int i = 0; i<arcs.size(); i++){
      removeArc(verticies.get(i), vertex);
    }
  }
  
  /* Remove an arc by going through each arc in LinkedList corresponding to first vertex
   * and checking if it goes to the second vertex and if so remove that arc
   * @return void
   */ 
  public void removeArc (T vertex1, T vertex2){
    for (int c = 0; c<arcs.get(getIndex(vertex1)).size(); c++){
      if (arcs.get(getIndex(vertex1)).get(c) == vertex2){
        arcs.get(getIndex(vertex1)).remove(c);
      }
    }
  }
  
  /* Add a vertex, if not already in verticies, by adding it to verticies vector and creating
   * new LinkedList element in arcs
   * @return void
   */ 
  public void addVertex(T vertex){
    if (!verticies.contains(vertex)){
      verticies.add(vertex);
      arcs.add(new LinkedList<T>());
    }
  }
  
  /* Add edge to graph if both verticies exist by adding reciprocal arcs as long as those arcs
   * don't already exist - check individually to add one arc if other already exists
   * @return void
   */ 
  public void addEdge (T vertex1, T vertex2){
    addArc(vertex1, vertex2);
    addArc(vertex2, vertex1);
  }
  
  /* Remove edge by removing both of the reciprocal arcs assuming and edge exists between the two verticies
   * @return void
   */ 
  public void removeEdge (T vertex1, T vertex2){
    if (isEdge(vertex1, vertex2)){
      arcs.get(getIndex(vertex1)).remove(vertex2);
      arcs.get(getIndex(vertex2)).remove(vertex1);
    }
  }
  
  /* Check for successors to given vertex by returning corresponding LinkedList of arcs starting at 
   * given vertex. Assumes a vertex can be its own successor because of slings.
   * @return LinkedList<T> of sucessors
   */ 
  public LinkedList<T> getSuccessors(T vertex){
    return arcs.get(getIndex(vertex));
  }
  
  /* Check for predecessors to given vertex by checking all LinkedLists in arcs to add any arc that
   * points to given vertex to new LinkedList of predecessors.
   * @return LinkedList<T> of predecessors
   */ 
  public LinkedList<T> getPredecessors(T vertex){ //can do w/o nested for loop?
    LinkedList<T> preds = new LinkedList<T>();
    for (int a = 0; a < arcs.size() ; a++){
      for (int k = 0; k < arcs.get(a).size(); k++){
        if (arcs.get(a).get(k) == vertex){
          preds.add(verticies.get(a));
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
      for (int b = 0; b < arcs.size(); b++){ 
        for (int c = 0 ; c < arcs.get(b).size() ; c++){ 
          //again add 1 to placeholders to account for tgf formatting
          result += (b+1) + " " + (getIndex(arcs.get(b).get(c))+1) + "\n";
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
  
  /* Additional Helper Function:
   * Print all edges nicely 
   * @return nicely formatted String of all edges
   */ 
  public String getEdges(){
    String result = "";
    for (int e = 0; e < verticies.size(); e++){
      result += "From " + verticies.get(e) + arcs.get(e) + "\n";
    }
    return result;
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
  
  /* Added by Annabel 04/26 - account for need to create a 2-d array to hold arc directions
   */ 
  public void addArcDirection(String direction, int x, int y){
    
    if (arcArrayCount < (x*y)){
      doubleArray();
    }
    
    arcDirections[x][y] = direction;
    
  }
  
  /* Added by Annabel 04/26 - account for need to resize arcDirections array
   */ 
  private void doubleArray(){
    //makes each column far larger than it needs to be - is there a better solution to this?
    String[][] biggerDirections = new String[arcDirections.length*2][3 + arcDirections.length*2];
    for (int i=0; i<arcDirections.length; i++) {
      //System.out.println("i value: " + i);
      for (int j=0; j<arcDirections[i].length; j++){
        //System.out.println("j value: " + j);
        //System.out.println(" (i,j) --> " + i + ", " + j);
        //System.out.println("arcDirections @ value pair: " + arcDirections[i][j]); 
        //System.out.println("biggerDirections @ value pair: " + biggerDirections[i][j]);
        biggerDirections[i][j] = arcDirections[i][j];
        //System.out.println("biggerDirections: " + biggerDirections[i][j] + "  arcDirections: " + arcDirections[i][j]);
      }
    }
    arcDirections = biggerDirections;
  }
  
  /* Added by Annabel 04/26 - toString for arcDirections matrix
   */
  public String arcDirectionsToString(){
    String results = "";
    for (int i=0; i<arcDirections.length; i++){
      if (!(arcDirections[i]==null)){
        for (int j=0; j<arcDirections[i].length; j++){
          if (!(arcDirections[i][j] == null)){
            results += arcDirections[i][j] + "\n";
          }
        }
      }
    }
    return results;
  }
  
  public static void main(String[] args){
    
    AdjListsGraph rooms = new AdjListsGraph();
    
    Room hello = new Room("160b");
    Room there = new Room("170b");
    rooms.addVertex(hello);
    rooms.addVertex(there);
    
    rooms.addArc(hello, there);
    
    rooms.saveToTGF("Rooms");
    System.out.println("Rooms.tgf was just saved to show initial state");
    
    MapsBuilder roomx = new MapsBuilder();
    AdjListsGraph roomxGraph = roomx.build("Room.tgf");
    System.out.println("Building new AdjListsGraph object from .tgf file; result should match"
                         + " final state above");
    System.out.println("Maps: " + roomxGraph);
    
    System.out.println(roomxGraph.arcDirectionsToString());


  }
}

