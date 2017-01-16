/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.dialogue;

/**
 * A conversation structure
 *
 * @author mjspr
 */
public class DialogueTree {

    /**
     * Root of the conversation
     */
    protected DialogueNode rootNode;
    /**
     * Current conversation line
     */
    protected DialogueNode curNode;

    /**
     * @param root the root of the conversation
     */
    public DialogueTree(DialogueNode root) {
        rootNode = root;
        curNode = rootNode;
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
