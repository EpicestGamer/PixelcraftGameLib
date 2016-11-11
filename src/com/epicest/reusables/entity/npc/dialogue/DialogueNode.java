/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.entity.npc.dialogue;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.scene.Node;
import java.util.*;

/**
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
     *
     */
    protected ArrayList<DialogueNode> parents = new ArrayList();
    /**
     * Children of this node
     */
    protected ArrayList<DialogueNode> children = new ArrayList();

    /**
     * Initializes DialogueNode
     */
    public DialogueNode() {
        super();
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

    public void setIn(String subtitle) {
        characterIn = subtitle;
    }

    public void setOut(String subtitle, String audio) {
        characterOut = subtitle;
        this.audio = audio;
    }

    public AudioNode playAudio(AssetManager assets, Node rootNode) {
        AudioNode audio = new AudioNode(assets, this.audio);
        audio.setLooping(false);
        audio.setPositional(false);
        rootNode.attachChild(audio);
        audio.play();
        return audio;
    }
}
