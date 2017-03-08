/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamecho.game;

import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Created by Jeff Grant 08/03/17
 *
 * @author 1622542
 */

// Created a placeholder for background music. This can be swapped at a later stage for another track
// No other sounds added at this time

public class Sound {
        
    public static synchronized void play(InputStream soundResource)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream ais = AudioSystem.getAudioInputStream(soundResource);
                    clip.open(ais);
                    clip.start();
                }catch(Exception ex)
                {
                    System.out.println("Error playing sound " + ex.getMessage());
                }
            }
        }).start();
    }
    
        public static synchronized void play(InputStream soundResource, boolean isMusic)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                 Clip clip = AudioSystem.getClip();
                 AudioInputStream ais = AudioSystem.getAudioInputStream(soundResource);
                 clip.open(ais);
                 clip.start();
                 
                 if(isMusic == true)
                     clip.loop(Clip.LOOP_CONTINUOUSLY);
                 
                }catch(Exception ex)
                {
                    System.out.println("Error playing background sound" + ex.getMessage());
                }
            }
        }).start();
    }
   
        
        
}