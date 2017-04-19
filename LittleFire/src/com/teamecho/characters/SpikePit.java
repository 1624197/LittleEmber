/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamecho.characters;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * This class is a representation of a spike pit the player must avoid in the game
 * @author 1622542  Created by Jeff Grant 18/04/17
 */

public class SpikePit {
    
    private int x; // The trap x coordinate
    private int y; // The trap y coordinate
    private int spriteWidth; //This variable stores the width of the image
    private int spriteHeight; //This variable stores the height of the image
    private int damage; //This variable stores the damage done to the player if there is a collision
    private boolean isVisible;
    
    private BufferedImage sprite; //This image represents the spike pit
    
    public SpikePit()
    {
        //Starting X and Y coordinates
        x = 400;
        y = 480;

        damage = 5;
        isVisible = true;
   
        try {
            sprite = ImageIO.read(getClass().getResource("/Images/Enemy_Images/SpikePit.png"));
        } catch (Exception ex) {
            System.err.println("Error loading SpikePit image");
        }

        spriteWidth = sprite.getWidth();
        spriteHeight = sprite.getHeight();
    }
    
    public BufferedImage getSprite() 
    {
        return sprite;
    }
    
    public Rectangle getBounds()
    {
        Rectangle objectRect = new Rectangle(x, y, spriteWidth, spriteHeight);   
        return objectRect;
    }
    
    public void setX(int newX) 
    {
        x = newX;
    }

    public int getX() 
    {
        return x;
    }

    public void setY(int newY) 
    {
        y = newY;
    }

    public int getY() 
    {
        return y;
    }
    
    public void setDamage(int newDamage) 
    {
       damage = newDamage;
    }
    
    public int getDamage()
    {
        return damage;
    }
    
    public void setVisible(boolean visible)
    {
        isVisible = visible;  
    }
    
    public boolean getVisible()
    {
        return isVisible; 
    }
    
}

