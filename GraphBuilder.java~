/* GraphBuilder.java
 * Written by: CS230 staff
 * Modified by: Annabel Rothschild
 * Part of SciMaps
 */ 

import java.io.*;
import java.util.*;

/* When we want to create a graph of some specific object type,
 * we need to extend this abstarct class. The child class will have to 
 * provide implementation for the "createOneThing()" method.
 * */
abstract class GraphBuilder<T> {
  
  /*
   * To be overriden in the extension of this class 
   * (like the PersonGraphBuilder).
   * It will create and return an object of the specific 
   * type the graph will contain, from the input string s.
   * */
  abstract T createOneThing(String s);
  
  /*
   * Read from the input .tgf file, line by line.
   * Create the vertex objects, and add them to the graph.
   * Then, add the connections between the vertices.
   * 
   * PRECONDITION: the input file is in .tgf format
   * */
  public AdjListsGraph<T> build (String fileName) {
    AdjListsGraph<T> graph = new AdjListsGraph<T>();
 
    try{
      Scanner scan = new Scanner(new File(fileName));
      //for each of the verticies (know you've gone through all verticies when you hit #)
      while (!scan.next().equals("#")){
        String line = scan.nextLine().trim(); //take out extra whitespace or it tries to create another object
        System.out.println("LINE" + line);
        T something = createOneThing(line); //create one vertex out of that object and add it to graph
        graph.addVertex(something);
    }
      while (scan.hasNext()){
        //first index is origin, second is destination
        Integer from = scan.nextInt();
        Integer to = scan.nextInt();
        //add an arc between the actual vertex objects that those indexes
        graph.addArc(graph.getVertex(from), graph.getVertex(to));
      }
      scan.close();
    } catch (IOException ex){
      System.out.println("There was an error opening the file: " + ex);
    }
    return graph;
  }
  
}