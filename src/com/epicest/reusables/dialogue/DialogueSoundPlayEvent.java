/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.dialogue;

import com.jme3.scene.Node;

/**
 * An Event, for use with DialogueSoundPlayEventListener
 *
 * @author EpicestGamer
 */
public class DialogueSoundPlayEvent {

 /**
  * The DialogueNode associated with the event
  */
 DialogueNode dialogueNode = null;

 /**
  * Creates a DialogueSoundPlayEvent
  */
 public DialogueSoundPlayEvent(DialogueNode nodeInhibitor) {
  dialogueNode = nodeInhibitor;
 }

 /**
  * @return the DialogueNode that this event is associated with
  */
 public DialogueNode getDialogueNode() {
  return dialogueNode;
 }
}
