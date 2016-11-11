/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.settings;

import com.jme3.system.*;
import java.util.prefs.BackingStoreException;

/**
 * Default settings for a PixelApplication.
 *
 * @author EpicestGamer
 */
public class GamersAppSettings {

    protected static AppSettings as;

    static {
        as = new AppSettings(true);
        //Video
        as.setResolution(720, 480);
        //as.setSamples(4);
        as.setFrameRate(60);
        //Input
        as.setUseJoysticks(true);
        //Branding
        as.setTitle("A Game");
    }

    /**
     * Default settings for a PixelApplication.
     */
    public static AppSettings appSettings() {
        return as;
    }

    public static void load(String app) throws BackingStoreException {
        as.load(app);
    }

    public static void save(String app) throws BackingStoreException {
        as.save(app);
    }
}
