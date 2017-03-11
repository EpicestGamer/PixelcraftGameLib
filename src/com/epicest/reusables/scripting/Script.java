/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.scripting;

import java.io.*;
import java.util.logging.*;

/**
 * Scripts to use in Runner.
 *
 * @author EpicestGamer
 */
public class Script {

 File script;
 BufferedInputStream bin;

 /**
  * Sets a script to run.
  */
 public void setAssetScript(String path) {
  setScript("assets/" + path);
 }

 /**
  * Sets a script using a absolute path to run.
  */
 public void setScript(String path) {
  try {
   script = new File(path);
   FileInputStream in = new FileInputStream(script);
   bin = new BufferedInputStream(in);
  } catch (Exception e) {
   Logger.getLogger("").log(Level.WARNING, "Umm" + e.toString());
   script = null;
   bin = null;
  }
 }

 /**
  * Runs this script once.
  */
 public String getString(Runner in) {
  StringBuffer commandBuffer = new StringBuffer();
  String commands = null;
  try {
   bin.mark(2048);
   int nin = 0;
   char inChar;
   do {
    nin = bin.read();
    inChar = (char) nin;
    if (nin != -1) {
     commandBuffer.append(inChar);
    }
   } while (nin != -1);
   commands = commandBuffer.toString();
   bin.reset();
  } catch (Exception e) {
   e.printStackTrace();
  }
  return commands;
 }
}
