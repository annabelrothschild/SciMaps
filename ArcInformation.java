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
   * @ params index of beginning arc, index of end arc, String directions, double for distance in minutes
   */ 
  //required Arc indicies - use getIndex on Arcs before trying to use resetDirections
  public void addInformation(int from, int to, String d, double m){
    
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
  /* Reset distance (minutes) for a given arc
   * @ params index of beginning arc, index of end arc, double minutes
   */ 
  //required Arc indicies - use getIndex on Arcs before trying to use resetMinutes
  public void resetMinutes(int from, int to, double m){
    
    if ((rows < from) || (columns < to)){
      System.out.println("This index does not exist in the array. Please double check your indicies.");
    }
    else{
      arcInfo[from][to].setMinutes(m);
    }
  }
  
  /* @ return number of rows
   */ 
  public int numRows(){
    
    return this.rows;
    
  }
  
  /* @ return number of columns at given index
   */ 
  public int numColumns(int index){
    
    if (columns < index){
      System.out.println("You are trying to access a column that doesn't exist yet. Please double check index.");
      return -1;
    }
    
    return arcInfo.length;
  }
  
  /* Create String representation of array
   * @ return String representation of array
   */ 
  public String toString(){
    //assumes weights and arcsResults will always have same size and will both be null in same places
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
  
  /* @ return Arc for reference
   */ 
  public Arc get(int start, int end){
    
    if ((rows < start) || (columns < end)){
      System.out.println("You just tried to get an arc that doesn't exist.");
      return null;
    }
    
    return arcInfo[start][end];
    
  }
  
  /* Re-assign an arc to null
   * @ param arc-to-be-removed's indicies
   */
  public void removeArc(int start, int end){
    
    if ((rows < start) || (columns < end)){
      System.out.println("You just tried to remove an arc that doesn't exist.");
      return;
    }
    
    arcInfo[start][end] = null;
    
  }
  
  
  public static void main(String[] args){
    
//    ArcInformation myInfo = new ArcInformation(5,5);
//    myInfo.addInformation(1,6,"right",.4);
//    System.out.println(myInfo);
//    
  }
}