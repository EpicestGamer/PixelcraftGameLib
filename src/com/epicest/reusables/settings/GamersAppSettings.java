/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.settings;

import com.jme3.system.AppSettings;

/**
 * Default settings for a PixelApplication.
 *
 * @author EpicestGamer
 */
public class GamersAppSettings {

    /**
     * Default settings for a PixelApplication.
     */
    public static AppSettings appSettings() {
        AppSettings as = new AppSettings(true);
        //Video
        as.setResolution(720, 480);
        //as.setSamples(4);
        as.setFrameRate(60);
        //Input
        as.setUseJoysticks(true);
        //Branding
        as.setTitle("A Game");
        return as;
    }
}
