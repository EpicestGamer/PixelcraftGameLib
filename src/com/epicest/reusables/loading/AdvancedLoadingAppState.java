/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.loading;

import com.epicest.reusables.PixelApplication;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;

/**
 *
 * @author mjspr
 */
public class AdvancedLoadingAppState extends AbstractAppState implements Runnable {

 /**
  * State Manager for the loading appstate
  */
 private AppStateManager asm;
 private Thread thisThread;
 protected static final float tpf = 0.5f;
 protected boolean running = true;
 protected boolean markedForLoad = false;
 protected PixelApplication app;

 public AdvancedLoadingAppState(PixelApplication app) {
  super();
  this.app = app;
  asm = new AppStateManager((Application) app);

  asm.attach(this);

  thisThread = new Thread(this, "AdvancedLoadingAppState");
  thisThread.start();
 }

 public void markForLoad() {
  markedForLoad = true;
 }

 @Override
 public void initialize(AppStateManager stateManager, Application app) {
  super.initialize(stateManager, app);
 }

 @Override
 public void update(float tpf) {
  if (markedForLoad) {
   markedForLoad = false;
   app.openLoad();
  }
 }

 @Override
 public void run() {
  while (running) {
   asm.update(tpf);
   try {
    thisThread.sleep(20);
   } catch (Exception e) {
   }
  }
 }

 public void stop() {
  running = false;
 }

}
