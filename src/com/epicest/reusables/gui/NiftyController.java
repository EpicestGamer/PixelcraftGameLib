/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.gui;

import com.epicest.reusables.*;
import de.lessvoid.nifty.*;
import de.lessvoid.nifty.screen.*;

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

 public String getVersion() {
  return app.getVersion();
 }
}
