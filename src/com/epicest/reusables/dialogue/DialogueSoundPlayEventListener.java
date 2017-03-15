/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.dialogue;

/**
 * A EventListener for when a dialogue sound plays.
 *
 * @author EpicestGamer
 */
public interface DialogueSoundPlayEventListener {

 /**
  * Runs when the DialogueNode associated plays a sound
  */
 public void onSoundPlay(DialogueSoundPlayEvent e);
}
