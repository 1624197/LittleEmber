/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamecho.game;

import com.teamecho.game.screens.StartGamePanel;
import com.teamecho.levels.Level1;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.CardLayout;

/**
 * Created by Jeff Grant 21/02/17
 * @author 1622542
 */
public class Game 
{
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    
    private final String Title = "Little Fire";
    
    private int lastGameScore = 0;
    
    JFrame gameWindow; // Main Game Window - We add game components here
    StartGamePanel startScreen; // the starting screen displayed before the game is played
    Level1 lvl1; // this is level 1
    
    public Game()
    {
        initWindow(); // Call the initWindow method to initialise window properties
        initScreens(); // Call the initStartScreen to set up the start screen
    }
    
    private void initWindow()
    {
        gameWindow = new JFrame();
        gameWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setLocationRelativeTo(null); // Centers game on screen
        gameWindow.setTitle(Title);
    }

    private void initScreens()
    {   
        gameWindow.setLayout(new CardLayout());
        startScreen = new StartGamePanel(this);
        startScreen.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        lvl1 = new Level1(this);
        lvl1.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        
        // This will add a start Screen to the Main Window
        
        gameWindow.getContentPane().add(startScreen, "INTRO");
        gameWindow.getContentPane().add(lvl1, "LVL1");
        
    }
    
    public void showStartScreen()
    {
        gameWindow.setVisible(true);
        startScreen.requestFocus();
    }
    
    /**
     * display level1 panel
     */
    
    public void playGame()
    {
        //this methood will start the main game
        CardLayout cl = (CardLayout)gameWindow.getContentPane().getLayout();
        cl.next(gameWindow.getContentPane());
        lvl1.requestFocus();

    }
    
    public static void main(String[] args)
    {
        Game window = new Game();
        window.showStartScreen();
    }


}