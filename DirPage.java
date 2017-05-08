/* DirPage.java
 * Written by: Ana Fernandez
 * Part of SciMaps GUI
 */

import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class DirPage extends JPanel  { 
  private String inputStart, inputDestination;
  private JTextField text;
  private JLabel labelSouth;
  
  
  //----------------------------------------------------
  // CONSTRUCTOR
  //----------------------------------------------------
  
  public DirPage() {  
    
    setLayout (new BorderLayout());  
    setBackground (Color.white);   
    
    JPanel panelNorth = new DirPageNorthPanel();
    JPanel panelSouth = new DirPageSouthPanel();
    //JPanel panelSouth = new HomePageSouthPanel();
    
    panelNorth.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));
    //panelSouth.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));
    
    add(panelNorth, BorderLayout.NORTH);
    add(panelSouth, BorderLayout.SOUTH);
    //add(panelSouth, BorderLayout.SOUTH);
  } 
  
  //----------------------------------------------------
  // DIRPAGE PANELS (NORTH, CENTER, & SOUTH)
  //----------------------------------------------------
  
  public class DirPageSouthPanel extends JPanel {
    private JLabel label, startLabel, destinationLabel;
    public DirPageSouthPanel() {
      setBackground (Color.white); 
      /* 
       //1) Text Field for user to input their start location
       startLabel = new JLabel("Start: ");
       add(startLabel);
       text = new JTextField (5);        
       text.addActionListener (new TempListener());   
       add (text);    
       
       //2) Text Field for user to input their destination location
       destinationLabel = new JLabel("Destination: ");
       add(destinationLabel);
       text = new JTextField (5);        
       text.addActionListener (new TempListener());   
       add (text);  
       */
    }
  }
  
  public class DirPageNorthPanel extends JPanel {
    private JLabel labelNorth; 
    public DirPageNorthPanel () {
      labelNorth = new JLabel ("Here are your directions from "+ inputStart + " to " + inputDestination + ".");   
      add (labelNorth);    
      setBackground (Color.cyan); 
    }
  }
  /* 
   public class DirPageSouthPanel extends JPanel {
   private JButton addGetDirectionsButton;
   
   public DirPageSouthPanel () {
   setBackground (Color.white);  
   labelSouth = new JLabel ();      
   add (labelSouth);
   
   //3) Get Directions button that initiates navigation
   addGetDirectionsButton = new JButton ("Get Directions!");
   add(addGetDirectionsButton);
   
   addGetDirectionsButton.addActionListener(new ButtonListener());  
   }
   
   }
   */
  
  private class TempListener implements ActionListener {
    public void actionPerformed (ActionEvent event) {   
    }
  }
  
  private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
    }
  }
}