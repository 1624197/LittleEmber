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
 * This class is a static representation of an ember that the player can collect to 
 * increase score and charge up a powerful attack
 * 
 * Created by Jeff Grant 01/03/17
 * @author 1622542
 */
public class Ember 
{
    private int x;
    private int y;
    private int spriteWidth;
    private int spriteHeight;
    private boolean isVisible;
    
    private BufferedImage sprite;
    
    private int score;
    
    
    /**
     * The default constructor that initialises values to suitable defaults
     */
    
    public Ember(int X, int Y)
    {
        x = X;
        y = Y;
        score = 50;
        isVisible = true;
        
        try
        {
            sprite = ImageIO.read(getClass().getResourceAsStream("/Images/ember.png"));
        }catch(Exception ex)
        {
            System.err.println("Error loading ember sprite");
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
    
    public void setScore(int newScore)
    {
        score = newScore;
    }
    
    public int getScore()
    {
        return score;
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
