/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamecho.characters;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author 1622542 Jeff Grant Created 18/4/17
 */
public class Enemy {

    private int x; // The enemy x coordinate
    private int y; // The enemy y coordinate

    // displacement from current x coordinate (i.e. how far the x coord has changed 
    // by user interaction
    //This image represents the enemy
    private BufferedImage sprite;

    //This variable stores the width of the image
    private int spriteWidth;
    //This variable stores the height of the image
    private int spriteHeight;

    private int speed;
    private int damage;
    private int score;
    private int direction;
    private boolean isVisible;

    /**
     * Default constructor that sets X and Y coordinates to 10
     */
    public Enemy(int newX, int newY) {
        //Starting X and Y coordinates
        x = newX;
        y = newY;

        damage = 5;
        score = 10;
        direction = 0;
        speed = 3;
        isVisible = true;
        initEnemy();
    }

    /**
     * This method is called to initialise the enemy
     */
    public void initEnemy() {
        try {
            sprite = ImageIO.read(getClass().getResource("/Images/Enemy_Images/EnemyImage1.png"));
        } catch (Exception ex) {
            System.err.println("Error loading enemy sprite");
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

    public void move(int levelWidth, int levelHeight) {
        Random rand = new Random();
        int randomDirection;
        int tempX = x; //Use a temporary value for X to start - only update X pos if everything is fine
        int tempY = y; //Use a temporary value for Y to start - only update X pos if everything is fine

        randomDirection = rand.nextInt(4) + 1;

        switch (randomDirection) {
            case 1:
                tempY -= speed;
                break;
            case 2:
                tempY += speed;
                break;
            case 3:
                tempX -= speed;
                break;
            case 4:
                tempX += speed;
                break;
        }

        // Adding in some boundary checks      
        // X coordinate first
        // First check that the new X coordinate won't go off the left hand
        // side of the window
        // Then check that the X coordinate won't go off the right hand side
        if (tempX < (spriteWidth / 2)) {
            tempX += spriteWidth + speed; // Bump the X coordinate by width + speed to move away from the edge
        } else if ((tempX + spriteWidth) > levelWidth - (spriteWidth / 2)) {
            tempX -= spriteWidth + speed; // Bump the X coordinate along by width+ speed
        }

        // Now check the Y coordinate - first the top of the screen, then the bottom of the screen  
        if (tempY < (spriteHeight / 2)) {
            tempY += spriteHeight + speed;
        } else if ((tempY + spriteHeight) > levelHeight - (spriteHeight / 2)) {
            tempY -= spriteHeight + speed;
        }

        x = tempX;
        y = tempY;

    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean getVisible() {
        return isVisible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, spriteWidth, spriteHeight);
    }

}
