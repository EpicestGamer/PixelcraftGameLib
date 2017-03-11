/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.scripting.preset;

import com.epicest.reusables.scripting.*;

/**
 * Used when a player interacts with an Item. Preset scripts are faster than
 * normal interpreted scripts
 *
 * @author EpicestGamer
 */
public class OnItem extends Script {

 /**
  * Does nothing in preset scripts.
  */
 @Override
 public void setScript(String path) {
  //do nothing
 }

 /**
  * Runs code for 1.Adding an item 2.Updating players Inventory 3.Deletes Item.
  */
 public String getString(Runner in) {
  return "Java.type('com.epicest.reusables.PixelApplication')"
          + ".currentApplication"
          + ".player"
          + ".addItem("
          + "'" + in.getObject().getUserData("itemID") + "');\n"
          + "Java.type('com.epicest.reusables.PixelApplication')"
          + ".currentApplication"
          + ".getRootNode()"
          + ".detachChildNamed('" + in.getObject().getName() + "');";
//additem~" + in.getObject().getUserData("itemID") + "dispose~" + in.getObject().getName();
 }
}
