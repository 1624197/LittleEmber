/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamecho.game.screens;

/**
 * Created by Jeff Grant 22/02/17
 *
 * @author 1622542
 */
import com.teamecho.game.Game;
import com.teamecho.game.objects.StartGameButton;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartGamePanel extends JPanel implements MouseListener {

    private Game game; // this is a link back to the game controller class
    private BufferedImage backgroundImage = null;
    private final String SCREEN_TITLE = "Main Menu"; // sets screen title
    private StartGameButton thebutton;

    public StartGamePanel(Game theGame) {
        game = theGame;
        thebutton = new StartGameButton();
        initPanel();
    }

    private void initPanel() {

        //Load the background image
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/Images/MainMenuBackground.png"));
        } catch (Exception ex) {
            System.err.println("Error Loading Start Game Image");
        }

        //Make sure the panle has GUI focus
        //So keypresses are registered to this panel
        setFocusable(true);
        addMouseListener(this);

    }

    @Override
    protected void paintComponent(Graphics g) {
        //call the paintComponent method on the superclass to initialise drawing
        super.paintComponent(g);
        Font titleFont = new Font("Arial", Font.PLAIN, 22);

        //Start drawing -- the background goes first
        g.setFont(titleFont);
        g.drawString(SCREEN_TITLE, 20, 20);
        g.drawImage(backgroundImage, 0, 0, null);
        g.drawImage(thebutton.getSprite(), thebutton.getX(), thebutton.getY(), null);
        Toolkit.getDefaultToolkit().sync(); // Ensure that the objects visual state is up to date
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            game.playGame();
        }

    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {

    }
}
