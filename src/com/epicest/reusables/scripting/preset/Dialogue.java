/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.scripting.preset;

import com.epicest.reusables.scripting.Runner;
import com.epicest.reusables.scripting.Script;

/**
 *
 * @author mjspr
 */
public class Dialogue extends Script {

 /**
  * Does nothing in preset scripts.
  */
 @Override
 public void setScript(String path) {
  //do nothing
 }

 /**
  * Runs code for 1.opening dialogue
  */
 @Override
 public String getString(Runner in) {
  return "var Dialogue = Java.type("
          + "'" + in.getObject().getUserData("dialoguePointer") + "'));\n"
          + "Java.type('com.epicest.reusables.dialogue.gui.DialogueController')"
          + ".setDialogueTree("
          + "new Dialogue());\n"
          + "Java.type('com.epicest.reusables.PixelApplication')"
          + ".currentApplication"
          + ".markForDialogue();\n"
          + "Java.type('com.epicest.reusables.PixelApplication')"
          + ".currentApplication"
          + ".getInputManager()"
          + ".setCursorVisible(true);";//dialogue~" + in.getObject().getName();
 }

}
