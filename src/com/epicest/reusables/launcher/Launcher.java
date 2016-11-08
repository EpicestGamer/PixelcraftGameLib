
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.launcher;

import javax.swing.JFrame;

/**
 * A gui Launcher, designed to be like Bethesda's game launcher
 *
 * @author EpicestGamer
 */
public class Launcher extends JFrame {

    public Launcher() {
        super("Pixelcraft Game Launcher");
        setUndecorated(true);
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    protected void drawButtons() {
    }
}
