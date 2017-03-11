/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.dialogue;

import com.jme3.scene.Node;

/**
 *
 * @author mjspr
 */
public class DialogueSoundPlayEvent {

 Node applicationRootNode = null;
 DialogueNode dialogueNode = null;

 /**
  * Initializes Contructor
  */
 public DialogueSoundPlayEvent(Node rootNode, DialogueNode nodeInhibitor) {
  applicationRootNode = rootNode;
  dialogueNode = nodeInhibitor;
 }

 public Node getAppRootNode() {
  return applicationRootNode;
 }

 public DialogueNode getDialogueNodeInhibitor() {
  return dialogueNode;
 }
}
