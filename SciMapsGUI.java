/* SciMapsGUI.java
 * Written by: Ana Fernandez
 * Part of SciMaps GUI
 */

import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class SciMapsGUI extends JPanel  { 
  private String inputStart, inputDestination;
  private JTextField text;
  private JLabel labelSouth;
  
  
  //----------------------------------------------------
  // CONSTRUCTOR
  //----------------------------------------------------
  
  public SciMapsGUI() {  

    setLayout (new BorderLayout());  
    setBackground (Color.white);   
    
    JPanel panelNorth = new SciMapsGUINorthPanel();
    JPanel panelCenter = new SciMapsGUICenterPanel();
    JPanel panelSouth = new SciMapsGUISouthPanel();
    
    panelNorth.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));
    panelSouth.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));
    
    add(panelNorth, BorderLayout.NORTH);
    add(panelCenter, BorderLayout.CENTER);
    add(panelSouth, BorderLayout.SOUTH);
  } 
  
  //----------------------------------------------------
  // SCIMAPS PANELS (NORTH, CENTER, & SOUTH)
  //----------------------------------------------------
  
  public class SciMapsGUICenterPanel extends JPanel {
    private JLabel label, startLabel, destinationLabel;
    public SciMapsGUICenterPanel () {
      setBackground (Color.white); 
      
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
    }
  }
  
  public class SciMapsGUINorthPanel extends JPanel {
    private JLabel labelNorth; 
    public SciMapsGUINorthPanel () {
      labelNorth = new JLabel ("Welcome to SciMaps! Please enter a starting point and destination to begin navigation!");   
      add (labelNorth);    
      setBackground (Color.cyan); 
    }
  }
  
  public class SciMapsGUISouthPanel extends JPanel {
    private JButton addGetDirectionsButton;
    
    public SciMapsGUISouthPanel () {
      setBackground (Color.white);  
      labelSouth = new JLabel ();      
      add (labelSouth);
    
    //3) Get Directions button that initiates navigation
      addGetDirectionsButton = new JButton ("Get Directions!");
      add(addGetDirectionsButton);
      
      addGetDirectionsButton.addActionListener(new ButtonListener());  
    }
    
  }
  
  private class TempListener implements ActionListener {
    public void actionPerformed (ActionEvent event) {   
    }
  }
  
  private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      //Listener for start text field
      inputStart = text.getText();
      
      if (inputStart.length() == 0) {
        labelSouth.setText("You need to enter a starting location!");
      }
      
      //Listener for destination text field
      inputDestination = text.getText();
      
      if (inputDestination.length() == 0) {
        labelSouth.setText("You need to enter a destination!");
      }
      }
    }
  }