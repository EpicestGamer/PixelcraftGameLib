/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.scripting;

import com.jme3.scene.*;

/**
 * Runner to run a Script
 *
 * @author EpicestGamer
 */
public class Runner {

 /**
  * Initializes a Runner.
  */
 public Runner() {
  setScript(new Script());
 }
 /**
  * The script the Runner runs.
  */
 Script script = new Script();
 /**
  * The object, ussually utilised witht the interactbles.
  */
 Spatial object = null;

 /**
  * Usually used in interactability.
  */
 public void setObject(Spatial object) {
  this.object = object;
 }

 /**
  * Usually used in interactability.
  */
 public Spatial getObject() {
  return object;
 }

 /**
  * Used to set a script.
  */
 public void setScript(Script script) {
  this.script = script;
 }

 /**
  * Used to set a script.
  */
 public void setScript(String scriptPath) {
  script.setScript(scriptPath);
 }

 /**
  * Used to get a script.
  */
 public Script getScript() {
  return script;
 }
}
