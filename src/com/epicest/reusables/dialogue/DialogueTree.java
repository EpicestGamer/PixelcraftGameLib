/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.dialogue;

import com.jme3.scene.Node;

/**
 * A conversation structure
 *
 * @author EpicestGamer
 */
public class DialogueTree {

 /**
  * Creates a DialogueTree using existing values in a Node Tree
  *
  * @deprecated not finished, and I have no idea how to finish it.
  * @param parsedNode th Node to be parsed
  * @return a DialogueTree parsed in nodes
  */
 @Deprecated
 public static DialogueTree parseWorldNodes(Node parsedNode) {
  DialogueNode rootNode = new DialogueNode((String) parsedNode.getUserData("in"), (String) parsedNode.getUserData("out"), (String) parsedNode.getUserData("audio"));
  Node currentNode = (Node) parsedNode.getChildren().get(0);
  int lastCurrentNodeIndex;
  boolean finished = false;
  while (!finished) {
   for (int i = 0; i < currentNode.getQuantity(); i++) {
    Node nextCurrentNode = (Node) currentNode.getChild(i);
   }
  }

  DialogueTree parsedTree = new DialogueTree(rootNode);
  return parsedTree;
 }

 /**
  * Root of the conversation
  */
 protected DialogueNode rootNode;
 /**
  * Current conversation line
  */
 protected DialogueNode curNode;

 /**
  * Creates a DialogueTree with a rootNode
  *
  * @param root the root of the conversation
  */
 public DialogueTree(DialogueNode root) {
  rootNode = root;
  curNode = rootNode;
 }

 /**
  * Creates an empty DialogueTree
  */
 public DialogueTree() {
 }

 /**
  * @return the root of the conversation
  */
 public DialogueNode getRoot() {
  return rootNode;
 }

 /**
  * @return the current conversation line
  */
 public DialogueNode getCurrent() {
  return curNode;
 }

 /**
  * @param current the new current conversation line
  */
 public void setCurrent(DialogueNode current) {
  curNode = current;
 }
}
