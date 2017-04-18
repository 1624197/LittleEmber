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

    //this caps the fall speed of the player
    private int MaxFallSpeed = 5;
    //this is the initial jump speed of the player
    private int JumpHeight = -10;
    // this holds wheather the player is in the air or not
    private boolean Jumping = false;
    //this holds
    private int Gravity = 1;
    //this holds the movement speed modifier for the player
    private int Movespeed = 3;
    //this variable is used to delay the chage of downward momentum
    private int GravityDelay = 0;
    //these are used to hold if the player is trying to move left and or right
    private boolean MovingRight = false;
    private boolean MovingLeft = false;
    
    private int SpriteChangeDelay = 0;
    private int ImagePosition=1;
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
            sprite = ImageIO.read(getClass().getResource("/Images/Player_Images/PlayerRunning1.png"));
        } catch (Exception ex) {
            System.err.println("Error loading player sprite");
        }

        spriteWidth = sprite.getWidth();
        spriteHeight = sprite.getHeight();
    }
    //getSpriteWidth and getSpriteHeight are used to let other classes access the 
    //width and height of the character

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }
    //this is used to forcefully set the x value  from other classes
    public void setX(int newX) {
        x = newX;
    }
    //this is used to get the X value of the player character from other clasess
    public int getX() {
        return x;
    }
    
    //this is used to forcefully set the y value  from other classes
    public void setY(int newY) {
        y = newY;
    }
    //this is used to get the Y value of the player character from other clasess
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
     * This method is called to move the position of the player based on the
     * displacement values
     */
    public void updateMove() {
        x += dX * Movespeed;
        //this modifies the y value of the player
        y += dY;
        //this is called to check if the player is in the air and to manipulate dY
        fall();
    }

    /**
     * This method modifies the displacement of the x value depending on weather
     * the player is trying to move left or right the displacement is modified
     * instead of being set because this stops the stop and go movement of
     * setting it
     *
     * @param direction
     */
    public void move(int direction) {
        //the direction the player moves is sent from the key adaptor in the levels
        switch (direction) {
            case 1:
                //this checks if the player is not already moving left
                if (MovingLeft == false) {
                    //this modifies the displacement to be more towards the left
                    dX -= 1;
                    /**
                     * this then sets moving left to true to prevent the
                     * displacement from being modified without the player
                     * stopping first
                     */
                    MovingLeft = true;
                }
                break;
            case 2:
                //this checks if the player is not already moving right
                if (MovingRight == false) {
                    //this modifies the displacement to be more towards the right
                    dX += 1;
                    /**
                     * this then sets moving right to true to prevent the
                     * displacement from being modified without the player
                     * stopping first
                     */
                    MovingRight = true;
                }
                break;
            case 3:
                //this calls the jump function
                Jump();
                break;
            default:
                break;
        }
    }
// this is called when the player initially jumps

    public void Jump() {
        //this checks to see if the player is already in the air
        if (Jumping == false) {
            //this sets the player character to be in the air
            Jumping = true;
            //this sets the initial upwards momentum for when the player jumps
            dY = JumpHeight;
        }
    }

    /**
     * When the user releases a key the displacement from pressing that key is
     * negated. this is done instead of setting it because the displacement is
     * shared between going left and going right so setting it would disrupt the
     * other movement
     *
     * @param stop
     */
    public void stop(int stop) {
        switch (stop) {
            case 1:
                //this checks to see if the player is already
                //moving left before it tries to stop them
                if (MovingLeft == true) {
                    //this neutralises the displacement change from the movement
                    dX += 1;
                    //this then sets movingLeft to false to allow the player to
                    //moveleft again
                    MovingLeft = false;
                }
                break;

            case 2:
                //this checks to see if the player is already
                //moving left before it tries to stop them
                if (MovingRight == true) {
                    //this neutralises the displacement change from the movement
                    dX -= 1;
                    //this then sets movingLeft to false to allow the player to
                    //moveleft again
                    MovingRight = false;
                }
                break;
            //nothing to do with jumping is needed as other functions handle it
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
                //this increments the delay value
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
        //this forcefully sets the displacement to 0 so that this function can 
        // be used for more than 
        dY = 0;
    }
    
    
    public void SpriteChangeDelay(){
        SpriteChangeDelay++;
        if(SpriteChangeDelay==30){
            changeSprite();
        }
    }
    public void changeSprite(){
        switch (ImagePosition) {
            case 1:
                try {
                    sprite = ImageIO.read(getClass().getResource("/Images/Player_Images/PlayerRunning1.png"));
                } catch (Exception ex) {
                    System.err.println("Error loading player sprite");
                }
                ImagePosition=2;
                break;
            case 2:
                try {
                    sprite = ImageIO.read(getClass().getResource("/Images/Player_Images/PlayerRunning2.png"));
                } catch (Exception ex) {
                    System.err.println("Error loading player sprite");
                }
                ImagePosition=1;
                break;
            default:
                
        }
    }
}
