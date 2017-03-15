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
    private int dX = 0;
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
    private int Movespeed = 30;
    private int GravityDelay = 0;
    private boolean MovingRight = false;
    private boolean MovingLeft = false;

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

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
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
                if (MovingLeft == false) {
                    dX -= 1;
                    MovingLeft = true;
                }
                break;
            case 2:
                if (MovingRight == false) {
                    dX += 1;
                    MovingRight = true;
                }
                break;
            case 3:
                Jump();
                break;
            default:
                break;
        }
    }

    public void Jump() {
        if (Jumping == false) {
            Jumping = true;
            dY = JumpHeight;
        }
    }

    /**
     * When the user releases the key, reset the move displacement to 0 this is
     * to be changed
     *
     * @param stop
     */
    public void stop(int stop) {
        switch (stop) {
            case 1:
                if (MovingLeft == true) {
                    dX += 1;
                    MovingLeft = false;
                }
                break;

            case 2:
                if (MovingRight == true) {
                    dX -= 1;
                    MovingRight = false;
                }
                break;

            default:
                break;

        }
    }

    public void fall() {
        //this checks to see if the player is in the air
        if (Jumping == true) {
            {
                // this is used to delay the change in gravity for a more gradual change
                if (GravityDelay == 3) {
                    //this modifies the displacement for the y to gradually shift it downwards
                    dY += Gravity;
                    //this resets the delay
                    GravityDelay = 0;
                    //this checks to see if the player is falling too fast
                    if (dY > MaxFallSpeed) {
                        //this changes the displacement to cap the max fall speed
                        dY = MaxFallSpeed;
                    }
                }
                System.out.println(dX);
                GravityDelay += 1;
            }

        }
    }
//This gets the area of the player for use in collision checks

    public Rectangle getBounds() {
        Rectangle characterRect = new Rectangle(x, y, spriteWidth, spriteHeight);
        return characterRect;
    }

    //this is called when the player has landed on top of any object to stop the downward momentum
    //jumping is set to false to allow the player to jump again
    public void Land() {
        Jumping = false;
        dY = 0;
    }
}
