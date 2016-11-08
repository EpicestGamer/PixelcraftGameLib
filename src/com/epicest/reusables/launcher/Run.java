/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.launcher;

import com.epicest.reusables.PixelApplication;
import com.epicest.reusables.settings.GamersAppSettings;
import com.jme3.system.AppSettings;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * skeleton
 *
 * @author mjspr
 */
public abstract class Run {

    public static void main(String[] args) {
        Logger.getLogger("").setLevel(Level.ALL);
        PixelApplication app = getApp();
        AppSettings settings = GamersAppSettings.appSettings();
        settings.setTitle(PixelApplication.class.getSimpleName());
        app.setSettings(settings);
        app.setShowSettings(false);
        app.start();
    }

    public static PixelApplication getApp() {
        return null;
    }
}
