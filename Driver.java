/* Driver.java
 * Written by: Ana Fernandez
 * Part of SciMaps GUI
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Driver extends JPanel {
  public static void main (String[] args) {   
    JFrame frame = new JFrame ("Layout Manager");  
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);  
    
    //Add in the start of the program
    JTabbedPane tp = new JTabbedPane();        
    tp.addTab ("SciMaps", new SciMapsGUI());
      
    frame.getContentPane().add(tp);     
    frame.pack();         
    frame.setVisible(true); 
  } 
}