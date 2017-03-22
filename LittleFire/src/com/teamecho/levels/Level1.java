/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamecho.levels;

/**
 * Created by Jeff Grant 22/02/17
 *
 * @author 1622542
 */
import com.teamecho.game.Game;
import com.teamecho.characters.Player;
import com.teamecho.characters.Ember;
import com.teamecho.game.Sound;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;

/**
 * This panel represents the game world It contains all of the objects that take
 * part in the game It uses a timer to update every 10ms It uses a keyAdapter to
 * listen for user key presses and update the player's position
 */
public class Level1 extends JPanel implements ActionListener {

    private int score = 0;
    private Timer timer;
    BufferedImage background;
    private Game game;
    private Player thePlayer;
    private Ember theEmber;
    private Ember theEmber2;
    private Ember theEmber3;
    private Ember theEmber4;
    private Ember theEmber5;
    //JG 22/03/17 USE AN ARRAY WHERE POSSIBLE FOR COLLECTABLES BUT FOR PURPOSE OF TESTING HAVE SET UP AS SEPERATE 
    private final int GroundLevel = 520;

    public Level1(Game theGame) {

        game = theGame;
        thePlayer = new Player();
        thePlayer.setY(GroundLevel);
        theEmber = new Ember(100,400); 
        theEmber2 = new Ember(200,350);
        theEmber3 = new Ember(300,400); 
        theEmber4 = new Ember(400,350);  
        theEmber5 = new Ember(500,400);  
        //JG 22/03/17 USE AN ARRAY WHERE POSSIBLE FOR COLLECTABLES BUT FOR PURPOSE OF TESTING HAVE SET UP AS SEPERATE 
        init();
    }

//This is the private init method that we use to set the defaults for the 3. * level.
//We can call this method to reset the level (if required) - we can't do that
//with the constructor method - that can only be called once.
    private void init() {
        score = 0;
        addKeyListener(new TAdapter());
        setFocusable(true);
        setDoubleBuffered(true);

        try {
            background = ImageIO.read(getClass().getResource("/Images/level1_background.png"));
        } catch (Exception ex) {
            System.err.println("Error loading Level 1 background image");
        }

        timer = new Timer(10, this);
        timer.start();

        //Starts the background music
        Sound.play(getClass().getResourceAsStream("/Sounds/music.wav"), true);
    }

    /**
     * This method initiates the in game drawing. The graphics parameter allows
     * drawing operations to be carried out on the component. We use this method
     * to draw all of the game components - layering them from front to back
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Draw Background
        g.drawImage(background, 0, 0, null);
        //Draw Obsticles

        //Draw the player Character
        g.drawImage(thePlayer.getSprite(), thePlayer.getX(), thePlayer.getY(), null);

        //Draw the ember if it has not been picked up
        if (theEmber.getVisible() == true) {
            g.drawImage(theEmber.getSprite(), theEmber.getX(), theEmber.getY(), null);
        }
        
        if (theEmber2.getVisible() == true) {
            g.drawImage(theEmber2.getSprite(), theEmber2.getX(), theEmber2.getY(), null);
        }
                
        if (theEmber3.getVisible() == true) {
            g.drawImage(theEmber3.getSprite(), theEmber3.getX(), theEmber3.getY(), null);
        }
                        
        if (theEmber4.getVisible() == true) {
            g.drawImage(theEmber4.getSprite(), theEmber4.getX(), theEmber4.getY(), null);
        }

        if (theEmber5.getVisible() == true) {
            g.drawImage(theEmber5.getSprite(), theEmber5.getX(), theEmber5.getY(), null);
        }  

        //Code to draw the score on screen
        Font uiFont = new Font("Arial", Font.PLAIN, 14);
        g.setColor(Color.orange);
        g.setFont(uiFont);
        g.drawString("Score: " + score, 10, 15);

        g.dispose();
    }

    /**
     * This method will be called to check for collisions
     */
    public void checkCollisions() {
        Rectangle playerBounds = thePlayer.getBounds();
        Rectangle treasureBounds = theEmber.getBounds();
        if (thePlayer.getY() > GroundLevel - 3) {
            thePlayer.Land();
            thePlayer.setY(GroundLevel - 3);
        }

        // Check to see if the player boundary (rectangle) intersects
        // with the ember boundary (i.e. there is a collision)
        if (theEmber.getVisible() == true) {
            if (playerBounds.intersects(treasureBounds)) {
                score += 10;
                theEmber.setVisible(false);
            }
        }
    }

    /**
     * This method calls the movement methods on characters and NPCs
     */
    public void doMovement() {
        thePlayer.updateMove();
    }

    /**
     * This method is called in response to the timer firing Every 10ms, this
     * method will update the state of the game in response to changes such as
     * key presses and to generate computer movement
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        //The repaint method starts the process of updating the screen - calling
        //our version of the paintComponent method, which has the code for drawing
        //our characters and objects
        
        doMovement();
        checkCollisions();
        repaint();
    }

    /**
     * This is a private KeyAdapter Class that we use to process keypresses
     */
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int move = 0;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    move = 1;
                    break;
                case KeyEvent.VK_RIGHT:
                    move = 2;
                    break;
                case KeyEvent.VK_UP:
                    move = 3;
                    break;

                default:
                    break;
            }
            thePlayer.move(move);
        }

        @Override
        public void keyReleased(KeyEvent e) {

            int stop = 0;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    stop = 1;
                    break;
                case KeyEvent.VK_RIGHT:
                    stop = 2;
                    break;

                default:
                    break;
            }
            thePlayer.stop(stop);
        }
    }
}
