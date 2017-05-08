/* Driver.java
 * Written by: Ana Fernandez
 * Part of SciMaps GUI
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Driver extends JPanel {
  
  //instance variables
  private static MapsBuilder graph;
  private static AdjListsGraph<Room> roomGraph;
  
  public static void main (String[] args) {   
    //creates new instances
    graph = new MapsBuilder();
    roomGraph = graph.build("allrooms.tgf");
    
    //creates jframe that our panes will be added
    JFrame frame = new JFrame ("Layout Manager");  
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);  
    
    //adds tabs to the pane
    //first tab is the about/intro page
    //second tab is where the magic happens
    JTabbedPane tp = new JTabbedPane();        
    tp.addTab ("SciMaps", new About());
    tp.addTab ("Get Directions", new HomePage(roomGraph));
    
      
    frame.getContentPane().add(tp);     
    frame.pack();         
    frame.setVisible(true); 
  } 
}
