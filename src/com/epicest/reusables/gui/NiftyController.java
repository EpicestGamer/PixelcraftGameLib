/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.gui;

import com.epicest.reusables.PixelApplication;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.Console;
import de.lessvoid.nifty.controls.ConsoleCommands;
import de.lessvoid.nifty.controls.ConsoleExecuteCommandEvent;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.tools.Color;

/**
 * Simple nifty ScreenController class.
 *
 * @author reden
 * @author EpicestGamer
 */
public abstract class NiftyController implements ScreenController {

    /**
     * The nifty this services.
     */
    protected Nifty nifty;
    /**
     * The screen this services.
     */
    protected Screen screen;
    /**
     * The app this services.
     */
    protected static PixelApplication app;

    /**
     * Initializes the controller (I think).
     */
    @Override
    public void bind(Nifty nifty, Screen screen) {
        this.nifty = nifty;
        this.screen = screen;
    }

    /**
     * Does nothing.
     */
    @Override
    public void onStartScreen() {
    }

    /**
     * Does nothing.
     */
    @Override
    public void onEndScreen() {
    }

    /**
     * Sets an app.
     */
    public static void setApplication(PixelApplication app) {
        NiftyController.app = app;
    }

    /**
     * Quits the application.
     */
    public void quit() {
        app.stop();
    }
}
