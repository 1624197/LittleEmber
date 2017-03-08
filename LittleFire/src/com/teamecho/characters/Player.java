/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamecho.characters;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Rectangle;

/**
 * Created by Jeff Grant 28/02/17 edited further by Calum Blair
 *
 * @author 1622542
 */
public class Player {

    private int x; // The character's y coordinate
    private int y; // The character's x coordinate

    // displacement from current x coordinate (i.e. how far the x coord has changed 
    // by user interaction
    private int dX;
    // displacement from the current y coordinate (i.e. how far the y coord has changed
    // by user interaction
    private int dY = 0;

    //This image represents the character
    private BufferedImage sprite;

    //This variable stores the width of the image
    private int spriteWidth;
    //This variable stores the height of the image
    private int spriteHeight;

    private int MaxFallSpeed = 5;
    private int JumpHeight = -10;
    private boolean Jumping = false;
    private int Gravity = 1;
    // Test 123

    /**
     * Default constructor that sets X and Y coordinates to 10
     */
    public Player() {
        //Starting X and Y coordinates
        x = 10;
        y = 10;
        initCharacter();
    }

    /**
     * This method is called to initialise the player
     */
    public void initCharacter() {
        try {
            sprite = ImageIO.read(getClass().getResource("/Images/character.png"));
        } catch (Exception ex) {
            System.err.println("Error loading player sprite");
        }

        spriteWidth = sprite.getWidth();
        spriteHeight = sprite.getHeight();
    }

    public void setX(int newX) {
        x = newX;
    }

    public int getX() {
        return x;
    }

    public void setY(int newY) {
        y = newY;
    }

    public int getY() {
        return y;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    /**
     * This method returns the set sprite so that it can be drawn into the game.
     *
     * @return
     */
    public BufferedImage getSprite() {
        return sprite;
    }

    /**
     * This method is called to move the position of the player
     */
    public void updateMove() {
        x += dX;
        y += dY;

        fall();

    }

    /**
     * This method updates the displacement of the character based on the users
     * key press
     *
     * @param direction
     */
    public void move(int direction) {
        switch (direction) {
            case 1:
                dX = -1;
                break;

            case 2:
                dX = 1;
                break;
            case 3:
                Jump();
                break;
            default:
                break;
        }
    }

    /**
     * When the user releases the key, reset the move displacement to 0
     */
    public void stop() {
        dX = 0;
        
    }

    public void Jump() {
        if (Jumping == false) {
            Jumping = true;
            dY = JumpHeight;
        }
    }

    public void fall() {
        
        if (Jumping == true) {
            {
                
                    dY += Gravity;
                    temp = 0;
                    System.out.println("I am falling");
                    if (dY > MaxFallSpeed) {
                        this.dY = MaxFallSpeed;
                    }
                
                
            }
            System.out.println(getY());

        }
    }

    public Rectangle getBounds() {
        Rectangle characterRect = new Rectangle(x, y, spriteWidth, spriteHeight);
        return characterRect;
    }

    public void Land() {
        Jumping = false;
        dY = 0;

    }
}
