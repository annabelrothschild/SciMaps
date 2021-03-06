/* About.java
 * Part of SciMaps GUI
 * Written by: Ana Fernandez
 */


import java.awt.*; 
import javax.swing.*; 

//introduction panel
public class About extends JPanel  {   
  private ImageIcon memetastic;
  public About() { 
    setBackground (Color.cyan);  
    setLayout(new FlowLayout());
    
    //displays intro message
    JLabel l1 = new JLabel ("<html> Got lost in the maze that is the Science Center? Worry no longer!" +  
                              "<br><br>SciMaps, designed by Ana Fernandez, Annabel Rothschild, and Meha Ahluwalia will get you                                to your destination." + 
                              "<br>" + 
                              "<br>Select the 'Get Directions' tab to start your navigation!</html>");    
    add (l1);
    
    //displays a picture of a woman looking confused surrounded by math equations
    //this picture depicts how one feels trying to get around the science center
    JLabel l2 = new JLabel();
    memetastic = createImageIcon("mathlady.png", "the science center tho??");
    l2.setIcon(memetastic);
    add (l2);
    
  } 
    //helper method to display picture
    private static ImageIcon createImageIcon(String path, String description) {
    java.net.URL imgURL = AdjListsGraph.class.getResource(path);
    if (imgURL != null) {
      return new ImageIcon(imgURL, description);
    } else {
      System.err.println("Couldn't find file: " + path);
      return null;
    }
  }
} 
