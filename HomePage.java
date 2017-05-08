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
  //instance vars 
  private String inputStart, inputDestination;
  private JTextField text1, text2;
  private JLabel labelCenter, labelSouth, label;
  private JPanel panelNorth, panelCenter, panelSouth;
  private JButton addGetDirectionsButton;
  
  //instance variables as well, GUI makes use of:
  //MapsBuilder to creates graph from tgf, graph will represent our first floor plan
  //AdjListsGraph creates a graph of rooms with adjacency lists to store the arcs
  //Room represents each vertex in our graph
  private static MapsBuilder graph;
  private static AdjListsGraph<Room> roomGraph;
  private Room startRoom;
  private Room endRoom;
  
  
  
  //----------------------------------------------------
  // CONSTRUCTOR
  //----------------------------------------------------
  
  /*
   * @param takes in AdjListsGraph<Room>
  */
  public HomePage(AdjListsGraph<Room> rg) { 
    //instantiates instance
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
  } 
  
  //----------------------------------------------------
  // HOMEPAGE PANELS (NORTH, CENTER, & SOUTH)
  //----------------------------------------------------
  
  //panel that has textfields that prompts users for start/destination
  public class HomePageCenterPanel extends JPanel {
    //instance vars
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
    //instance vars
    private JLabel labelNorth; 
    public HomePageNorthPanel () {
      //welcome message
      labelNorth = new JLabel ("Welcome to SciMaps! Please enter a " + 
                               "starting point and destination to begin navigation!");   
      add (labelNorth);    
      setBackground (Color.green); 
    }
  }
  
  public class HomePageSouthPanel extends JPanel {
    
    public HomePageSouthPanel () {
      setBackground (Color.white);  
      labelSouth = new JLabel ();   //adds a blank label   
      add (labelSouth);
      
      //3) Get Directions button that initiates navigation
      addGetDirectionsButton = new JButton ("Get Directions!");
      add(addGetDirectionsButton);
      
      addGetDirectionsButton.addActionListener(new ButtonListener());  
    }
    
  }
  
  private class TempListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      //listens to textfields
    }
  }
  
  private class ButtonListener implements ActionListener {
    //instance vars
    private Dijkstra dijkstra;
    private CalculateDirections calcDirs;
    
    public void actionPerformed(ActionEvent event) {
      //when 'Get Directions' button is clicked
      if(event.getSource() == addGetDirectionsButton) {
        
        //gets text from start textfield that will be used for calcDirs
        inputStart = text1.getText();
        //gets text from desination textfield that will be used for calcDirs
        inputDestination = text2.getText();
        
        //if user enters a room that does not exist
        if (roomGraph.getRoom(inputStart) == null){
          labelCenter.setText("Your start location is not in our system. Please try another room.");
        }
        
        
        //if user enters a room that does not exist
        if (roomGraph.getRoom(inputDestination) == null){
          labelCenter.setText("Your destination is not in our system. Please try another room.");
        }
        
        
        //displays a message if user's
        //start and destination locations are the same
        else if(inputStart.equals(inputDestination)){
          labelCenter.setText("Your start and destination are the same.");
        }
        
        //if user does everything correctly, navigation will begin
        else {
         startRoom = roomGraph.getRoom(inputStart); //startRoom is set as the user's start input
         endRoom = roomGraph.getRoom(inputDestination); //endRoom is set as user's desination input
          
         dijkstra = new Dijkstra(); //creates a new instance of Dijkstra
         List<Room> path = dijkstra.computePaths(roomGraph, startRoom, endRoom); //computes fastest path to desination
          
         calcDirs = new CalculateDirections(); //creates new instance of CalculateDirections
          
         ///gets the text directions that will displayed in center panel
         String directions = calcDirs.getTextDirections(roomGraph, roomGraph.getArcs(), path); 
         directions = "<html>" + directions.replaceAll("\n","<br>"); //fixes formatting
         labelCenter.setText(directions); //displays directions in center panel
        }
      } 
    }
  }
}
