/* Arc.java
 * Written By: Annabel Rothschild
 * Modified By:
 * Defines one Arc object (w/distance between two vertices and direction as instance variables, along w/start 
 * & end points)
 */ 

public class Arc{
  
  private String directions;
  private int seconds;
  private int startVertex;
  private int endVertex;
  
  //assign instance variables
  public Arc(int f, int t, String d, int s){
    
    directions = d;
    seconds = s;
    startVertex = f;
    endVertex = t;
    
  }
  
  /* @ return minutes between endpoints
   */ 
  public int getSeconds(){
    
    return seconds;
    
  }
  
  /* @ return direction between endpoints
   */ 
  public String getDirections(){
    
    return directions;
    
  }
  
  /* Reset the minutes required in travel time
   */ 
  public void setSeconds(int freshSeconds){
    
    seconds = freshSeconds;
    
  }
  
  /* Reset the directions of an arc
   */ 
  public void setDirections(String freshDirections){
    
    directions = freshDirections;
    
  }
  
  /* @ return String representation of arc
   */ 
  public String toString(){
    
    String result = "Distance in seconds: " + seconds + " with directions: " + directions;
    return result;
    
  }
  
  /* @ return starting point of arc
   */ 
  public int getStartVertex(){
    
    return startVertex;
    
  }
  
  /* @ return endpoint of arc
   */ 
  public int getEndVertex(){
    
    return endVertex;
    
  }
  
  
  public static void main(String[] args){
    
    //Arc myArc = new Arc("eek", 2.0);
    //System.out.println(myArc);
    
  }
  
}