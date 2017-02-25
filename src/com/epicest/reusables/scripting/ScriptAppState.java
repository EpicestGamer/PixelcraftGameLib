/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.scripting;

import com.epicest.reusables.*;
import com.jme3.app.*;
import com.jme3.app.state.*;
import de.lessvoid.nifty.Nifty;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Application state to service scripts (.js JavaScript file).
 *
 * @author EpicestGamer
 */
public class ScriptAppState extends AbstractAppState {

 /**
  * Application this appstate services.
  */
 protected PixelApplication app;
 /**
  * Runners.
  */
 protected ArrayList<Runner> runners = new ArrayList();
 /**
  * Temporary Runners.
  */
 protected ArrayList<Runner> tempRunners = new ArrayList();
 /**
  * ScriptManager
  */
 protected ScriptEngineManager factory = new ScriptEngineManager();
 /**
  * ScriptEngine
  */
 protected ScriptEngine scripter;
 /**
  * Nifty
  */
 protected Nifty nifty;

 /**
  * Initialises the application state.
  *
  * @param app Application this appstate services.
  */
 @Override
 public void initialize(AppStateManager stateManager, Application app) {
  super.initialize(stateManager, app);
  this.app = (PixelApplication) app;
  scripter = factory.getEngineByName("Nashorn");
 }

 /**
  * Adds a Runner to run continuously.
  *
  * @param runner Runner to add.
  */
 public void addRunner(Runner runner) {
  runners.add(runner);
 }

 /**
  * Adds a Runner to run once..
  *
  * @param runner Runner to add.
  */
 public void addTempRunner(Runner runner) {
  tempRunners.add(runner);
 }

 /**
  * Clears Runners.
  */
 public void clearAllRunners() {
  runners = new ArrayList();
  tempRunners = new ArrayList();
 }

 /**
  *
  */
 public void setNifty(Nifty nifty) {
  this.nifty = nifty;
 }

 /**
  * Runs any Runners applicable and clears temporary Runners.
  */
 @Override
 public void update(float tpf) {
  for (Runner runner : tempRunners) {
   try {
    run(runner);
   } catch (ScriptException ex) {
    Logger.getLogger(ScriptAppState.class.getName()).log(Level.SEVERE, null, ex);
   }
  }
  for (Runner runner : runners) {
   try {
    run(runner);
   } catch (ScriptException ex) {
    Logger.getLogger(ScriptAppState.class.getName()).log(Level.SEVERE, null, ex);
   }
  }
  tempRunners = new ArrayList();
 }

 /**
  * Runs a single command.
  */
 public void run(Runner runner) throws ScriptException {
  scripter.eval(runner.script.getString(runner));
 }
}
