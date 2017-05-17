package gui;

import java.awt.Dimension;

import javax.swing.*;

import game.GameManager;

public class GameScreen {
	
	
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("MineSweeperSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        // There is no need to start a new game because by creating the game manager a new game started as well
        GameManager gameManager = new GameManager(20, 20, 40);
        
        frame.getContentPane().add(gameManager.getGameBoard());
 
        //Display the window.
        frame.pack();
        frame.setMinimumSize(new Dimension(1000,1000));
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
}
