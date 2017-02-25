/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.dialogue;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.scene.Node;
import java.util.*;

/**
 * A dialogue line in a conversation, having children for player choices
 *
 * @author EpicestGamer
 */
public class DialogueNode {

 /**
  * The input from the player
  */
 protected String characterIn = "THIS DIALOGUE SHOULD NOT BE SEEN";
 /**
  * The output the npc says
  */
 protected String characterOut = "THIS DIALOGUE SHOULD NOT BE SEEN";
 /**
  * The audio file played
  */
 protected String audio = null;
 /**
  * Parents of this node
  */
 protected ArrayList<DialogueNode> parents = new ArrayList();
 /**
  * Children of this node
  */
 protected ArrayList<DialogueNode> children = new ArrayList();
 /**
  * The Event Listener
  */
 protected DialogueSoundPlayEventListener del = null;

 /**
  * Initializes DialogueNode
  */
 public DialogueNode() {
 }

 public DialogueNode(String in, String out, String audio) {
  characterIn = in;
  characterOut = out;
  this.audio = audio;
 }

 public ArrayList<DialogueNode> getChildren() {
  return children;
 }

 public ArrayList<DialogueNode> getParents() {
  return parents;
 }

 public void addChild(DialogueNode child) {
  children.add(child);
  child.addParent(this);
 }

 void addParent(DialogueNode parent) {
  parents.add(parent);
 }

 public void setDialogueSoundPlayEventListener(DialogueSoundPlayEventListener dspel) {
  del = dspel;
 }

 public void setIn(String subtitle) {
  characterIn = subtitle;
 }

 public String getIn() {
  return characterIn;
 }

 public void setOut(String subtitle, String audio) {
  characterOut = subtitle;
  this.audio = audio;
 }

 public String getOut() {
  return characterOut;
 }

 public AudioNode playAudio(AssetManager assets, Node rootNode) {
  AudioNode audio = new AudioNode(assets, this.audio);
  audio.setLooping(false);
  audio.setPositional(false);
  rootNode.attachChild(audio);
  audio.play();
  if (del != null) {
   del.onSoundPlay(new DialogueSoundPlayEvent(rootNode, this));
  }
  return audio;
 }
}
