
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.launcher;

import com.epicest.reusables.launcher.action.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.*;
import javax.swing.*;

/**
 * A gui Launcher, designed to be like Bethesda's game launcher
 *
 * @author EpicestGamer
 */
public class Launcher extends JFrame {
    
    LauncherPanel panel;
    
    public Launcher() {
        super("Pixelcraft Game Launcher");
        setUndecorated(true);
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        panel = new LauncherPanel();
        panel.imageFile = "Textures/2D/Interface/Launcher/Background.png";//D:\Documents\JMEP\FalloutJayar\assets\Textures\2D\Interface\Launcher\Background.png
        add(panel);
        setVisible(true);
    }
    
    public void setImage(String image) {
    }
    
    public void addButton(String image, String highlight, ButtonAction action) {
        panel.addButton(image, highlight, action);
    }
    
    public void setButtonDimensions(int x, int y) {
    }
    
    protected void drawButtons() {
    }
}

class LauncherPanel extends JPanel implements Runnable, MouseListener {
    
    public String imageFile;
    public ArrayList<String> buttons = new ArrayList();
    public ArrayList<String> highlightButtons = new ArrayList();
    public ArrayList<ButtonAction> buttonActions = new ArrayList();
    public int buttonX = 600;
    public boolean running = true;
    
    public LauncherPanel() {
    }
    
    public void addButton(String image, String highlight, ButtonAction action) {
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("assets/" + imageFile));
            g.drawImage(img, 0, 0, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void run() {
        while (running) {
            repaint();
            try {
                Thread.sleep(20);
            } catch (Exception e) {
            }
        }
    }
}
