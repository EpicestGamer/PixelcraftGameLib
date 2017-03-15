/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.dialogue;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioData;
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
  * Initializes an empty DialogueNode
  */
 public DialogueNode() {
 }

 /**
  * Initialized DialogueNode
  *
  * @param in the player input for this DialogueNode
  * @param out the subtitle of the audio for this DialogueNode
  * @param audio the path of the audio for this DialogueNode
  */
 public DialogueNode(String in, String out, String audio) {
  characterIn = in;
  characterOut = out;
  this.audio = audio;
 }

 /**
  * @return any children of this DialogueNode
  */
 public ArrayList<DialogueNode> getChildren() {
  return children;
 }

 /**
  * @return any parents of this DialogueNode
  */
 public ArrayList<DialogueNode> getParents() {
  return parents;
 }

 /**
  * Adds a child to this DialogueNode
  *
  * @param child the child to add
  */
 public void addChild(DialogueNode child) {
  children.add(child);
  child.addParent(this);
 }

 /**
  * Adds a parent to this DialogueNode
  *
  * @param parent the parent to add
  */
 void addParent(DialogueNode parent) {
  parents.add(parent);
 }

 /**
  * Sets the DialogueSoundPlayEventListener
  *
  * @param dspel the DialogueSoundPlayEventListener to use
  */
 public void setDialogueSoundPlayEventListener(DialogueSoundPlayEventListener dspel) {
  del = dspel;
 }

 /**
  * @param subtitle the player input for this DialogueNode
  */
 public void setIn(String subtitle) {
  characterIn = subtitle;
 }

 /**
  * @return the player input for this DialogueNode
  */
 public String getIn() {
  return characterIn;
 }

 /**
  * Sets this DialogueNode's output
  *
  * @param subtitle the subtitle of the audio for this DialogueNode
  * @param audio the path of the audio for this DialogueNode
  */
 public void setOut(String subtitle, String audio) {
  characterOut = subtitle;
  this.audio = audio;
 }

 /**
  * @return the subtitle of this DialogueNode
  */
 public String getOut() {
  return characterOut;
 }

 /**
  * Plays the audio, and runs the DialogueSoundPlayEventListener, if any
  */
 public AudioNode playAudio(AssetManager assets, Node rootNode) {
  AudioNode audio = new AudioNode(assets, this.audio, AudioData.DataType.Buffer);
  audio.setLooping(false);
  audio.setPositional(false);
  rootNode.attachChild(audio);
  audio.play();
  if (del != null) {
   del.onSoundPlay(new DialogueSoundPlayEvent(this));
  }
  return audio;
 }
}
