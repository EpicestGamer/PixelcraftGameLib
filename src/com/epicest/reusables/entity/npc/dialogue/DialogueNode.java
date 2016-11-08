/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.entity.npc.dialogue;

import com.jme3.scene.Node;

/**
 *
 * @author EpicestGamer
 */
public class DialogueNode extends Node {

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
     * Initializes DialogueNode
     */
    public DialogueNode() {
        super();
    }
}
