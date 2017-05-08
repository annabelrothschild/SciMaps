/* HomePage.java
 * Written by: Ana Fernandez
 * Part of SciMaps GUI
 */

import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.JPanel;
import java.util.List;

public class HomePage extends JPanel  { 
  private String inputStart, inputDestination;
  private JTextField text1, text2;
  private JLabel labelCenter, labelSouth, label;
  private JPanel panelNorth, panelCenter, panelSouth;
  private JButton addGetDirectionsButton;
  private static MapsBuilder graph;
  private static AdjListsGraph<Room> roomGraph;
  private Room startRoom;
  private Room endRoom;
  
  
  
  //----------------------------------------------------
  // CONSTRUCTOR
  //----------------------------------------------------
  
  public HomePage(AdjListsGraph<Room> rg) { 
    
    roomGraph = rg;

    setLayout (new BorderLayout());  
    setBackground (Color.white);   
    
    panelNorth = new HomePageNorthPanel();
    panelCenter = new HomePageCenterPanel();
    panelSouth = new HomePageSouthPanel();
    
    
    panelNorth.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));
    panelSouth.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));
    
    add(panelNorth, BorderLayout.NORTH);
    add(panelCenter, BorderLayout.CENTER);
    add(panelSouth, BorderLayout.SOUTH);
    
    /*
     //Panel that shows up to show directions
     JPanel DirPanel = new DirectionsPanel();
     
     panelNorth.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));
     panelSouth.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));
     */
  } 
  
  //----------------------------------------------------
  // HOMEPAGE PANELS (NORTH, CENTER, & SOUTH)
  //----------------------------------------------------
  
  public class HomePageCenterPanel extends JPanel {
    private JLabel startLabel, destinationLabel;
    private JComboBox startOptions, destinationOptions;
    
    public HomePageCenterPanel () {
      setBackground (Color.white); 
      
      //1) Text Field for user to input their start location
      startLabel = new JLabel("Start: ");
      add(startLabel);
      text1 = new JTextField (5);        
      text1.addActionListener (new TempListener());   
      add (text1); 
      
      
      //2) Text Field for user to input their destination location
      destinationLabel = new JLabel("Destination: ");
      add(destinationLabel);
      text2 = new JTextField (5);        
      text2.addActionListener (new TempListener());   
      add (text2); 
      
      label = new JLabel();
      add(label);
      
      
      labelCenter = new JLabel ();      
      add (labelCenter);
    }
    
  }
  //panel that greets users
  public class HomePageNorthPanel extends JPanel {
    private JLabel labelNorth; 
    public HomePageNorthPanel () {
      labelNorth = new JLabel ("Welcome to SciMaps! Please enter a starting point and destination to begin navigation!");   
      add (labelNorth);    
      setBackground (Color.green); 
    }
  }
  
  public class HomePageSouthPanel extends JPanel {
    
    public HomePageSouthPanel () {
      setBackground (Color.white);  
      labelSouth = new JLabel ();      
      add (labelSouth);
      
      //3) Get Directions button that initiates navigation
      addGetDirectionsButton = new JButton ("Get Directions!");
      add(addGetDirectionsButton);
      
      addGetDirectionsButton.addActionListener(new ButtonListener());  
    }
    
  }
  
  /*
   public class DirectionsPanel extends JPanel {
   private JLabel labelNorth; 
   public HomePageNorthPanel () {
   labelNorth = new JLabel ("Welcome to SciMaps! Please enter a starting point and destination to begin navigation!");   
   add (labelNorth);    
   setBackground (Color.cyan); 
   }
   } 
   */
  private class TempListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
    }
  }
  
  private class ButtonListener implements ActionListener {
    private Dijkstra dijkstra;
    private CalculateDirections calcDirs;
    public void actionPerformed(ActionEvent event) {
      if(event.getSource() == addGetDirectionsButton) {
        //Listener for start text field
        inputStart = text1.getText();
        
        if (roomGraph.getRoom(inputStart) == null){
          labelCenter.setText("Your start location is not in our system. Please try another room.");
        }
        
        //Listener for destination text field
        inputDestination = text2.getText();
        if (roomGraph.getRoom(inputDestination) == null){
          labelCenter.setText("Your destination is not in our system. Please try another room.");
        }
        
        
        //displays an error message if user's
        //start and destination locations are the same
        else if(inputStart.equals(inputDestination)){
          labelCenter.setText("Your start and destination are the same.");
        }
        else {
         startRoom = roomGraph.getRoom(inputStart);
         endRoom = roomGraph.getRoom(inputDestination);
         dijkstra = new Dijkstra();
         List<Room> path = dijkstra.computePaths(roomGraph, startRoom, endRoom);
         calcDirs = new CalculateDirections();
         
//         String star = "\n**********************************************************";
//         star = "<html>" + star.replaceAll("\n","<br>");
//         label.setText(star);
         
         String directions = calcDirs.getTextDirections(roomGraph, roomGraph.getArcs(), path);
         directions = "<html>" + directions.replaceAll("\n","<br>");
         labelCenter.setText(directions);
        }
      }
      
      
      
      
      
      
    }
  }
}