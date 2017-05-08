/* ArcInformation.java
 * Written By: Annabel Rothschild
 * Modified By:
 * Creates ArcInformation object - 2d array to store directions and weight of all arcs
 */
public class ArcInformation{
  
  private Arc[][] arcInfo;
  private int rows;
  private int columns;
  
  //create array with given dimentions
  public ArcInformation(int xDirection, int yDirection){
    
    arcInfo = new Arc[xDirection][yDirection];
    rows = xDirection;
    columns = yDirection;
    
  }
  
  /* Add a new arc
   * @ params index of beginning arc, index of end arc, String directions, integer for distance in seconds
   */ 
  //required Arc indicies - use getIndex on Arcs before trying to use resetDirections
  public void addInformation(int from, int to, String d, int m){
    
    if ((rows < from) || (columns < to)){
      System.out.println("This index does not exist in the array. Please double check your indicies.");
    }
    
    else{
      arcInfo[from][to] = new Arc(from, to, d, m);
    }
    
  }
  /* Reset directions for a given arc
   * @ params index of beginning arc, index of end arc, String directions
   */ 
  //required Arc indicies - use getIndex on Arcs before trying to use resetDirections
  public void resetDirection(int from, int to, String d){
    
    if ((rows < from) || (columns < to)){
      System.out.println("This index does not exist in the array. Please double check your indicies.");
    }
    else{
      arcInfo[from][to].setDirections(d);
    }
  }
  /* Reset distance (seconds) for a given arc
   * @ params index of beginning arc, index of end arc, integer seconds
   */ 
  //required Arc indicies - use getIndex on Arcs before trying to use resetMinutes
  public void resetSeconds(int from, int to, int s){
    
    if ((rows < from) || (columns < to)){
      System.out.println("This index does not exist in the array. Please double check your indicies.");
    }
    else{
      arcInfo[from][to].setSeconds(s);
    }
  }
  
  /* Geter for number of rows in ArcInformation object
   * @ return number of rows
   */ 
  public int numRows(){
    
    return this.rows;
    
  }
  
  /* Getter for number of columns at a given index
   * @ return number of columns at given index
   */ 
  public int numColumns(int index){
    
    if (columns < index){
      System.out.println("You are trying to access a column that doesn't exist yet. Please double check index.");
      return -1;
    }
    
    return arcInfo.length;
  }
  
  /* Create String representation of array
   * Utilize nested for loop to traverse all arcs
   * @ return String representation of array
   */ 
  public String toString(){
    String arcResults = "";
    for (int i=0; i<arcInfo.length; i++){
      if (!(arcInfo[i]==null)){
        for (int j=0; j<arcInfo[i].length; j++){
          if (!(arcInfo[i][j] == null)){
            arcResults += "From vertex " + arcInfo[i][j].getStartVertex() + " to vertex " 
              + arcInfo[i][j].getEndVertex() + " with " + arcInfo[i][j] + "\n";
          }
        }
      }
    }
    return arcResults;
  }
  
  /* Getter for a particular art given starting and ending indicies
   * @ param starting and ending vertices
   * @ return Arc for reference
   */ 
  public Arc get(int start, int end){
    
    //System.out.println(start + ", " + end);
    
    if ((rows < start) || (columns < end)){
      System.out.println("You just tried to get an arc that doesn't exist.");
      return null;
    }
    
    return arcInfo[start][end];
    
  }
  
  /* Remove an arc by re-assigning arc's space in array to null 
   * @ param arc-to-be-removed's indicies
   */
  public void removeArc(int start, int end){
    
    if ((rows < start) || (columns < end)){
      System.out.println("You just tried to remove an arc that doesn't exist.");
      return;
    }
    
    arcInfo[start][end] = null;
    
  }
  
  
  /* Set all arcs originating at a certain vertex to null
   * @ param vertex w/arcs to be removed
   */ 
  public void removeFromVertex(int vertexId){
    
    for (int o = 0; o < arcInfo[vertexId].length ; o++){
      arcInfo[vertexId][o] = null;
    }
    
  }
  
  /* Double array's size - never used in implementation of program, only for testing
   */ 
  private void doubleArray(){
    Arc[][] biggerInfo = new Arc[rows*2][3 + columns*2];
    rows = rows * 2;
    columns = columns * 2;
    for (int i=0; i<arcInfo.length; i++) {
      for (int j=0; j<arcInfo[i].length; j++){
        biggerInfo[i][j] = arcInfo[i][j];
      }
    }
    arcInfo = biggerInfo;
  }
  
  public static void main(String[] args){
    
//    Room room1 = new Room("1");
//    Room room2 = new Room("2");
//    Room room3 = new Room("3");
//    
//    ArcInformation myArcs = new ArcInformation(20,20);
    //myArcs.addInformation(room
    
//    ArcInformation myInfo = new ArcInformation(5,5);
//    myInfo.addInformation(1,6,"right",.4);
//    System.out.println(myInfo);
//    
  }
}
