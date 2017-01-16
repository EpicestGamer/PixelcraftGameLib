/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.dialogue.gui;

import com.epicest.reusables.dialogue.DialogueNode;
import com.epicest.reusables.dialogue.DialogueTree;
import com.epicest.reusables.gui.NiftyController;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;

/**
 * Used for conversations in NiftyGUI
 *
 * @author mjspr
 */
public class DialogueController extends NiftyController {

    /**
     * Conversation structure
     */
    protected static DialogueTree currentDialogue = null;

    @Override
    public void bind(Nifty nifty, Screen screen) {
        super.bind(nifty, screen);
        if (currentDialogue != null) {
            refreshButtons();
        }
    }

    /**
     * Set's the conversation structure
     */
    public static void setDialogueTree(DialogueTree dialogue) {
        currentDialogue = dialogue;
    }

    protected void refreshButtons() {
        Element element = screen.findElementByName("choices");
        for (Element e : element.getElements()) {
            e.hide();
        }
        for (int i = 0; i < currentDialogue.getCurrent().getChildren().toArray().length; i++) {
            final int index = i;
            new ButtonBuilder("dialogueChoice" + i, currentDialogue.getCurrent().getChildren().get(i).getIn()) {
                {
                    alignCenter();
                    height("20px");
                    width("96%");
                    visibleToMouse(true);
                    interactOnClick("runButton(" + index + ")");
                }
            }.build(nifty, screen, element);
        }
    }

    public void runButton(String index) {
        int indexChoice = Integer.parseInt(index);
        DialogueNode choice = currentDialogue.getCurrent().getChildren().get(indexChoice);
        choice.playAudio(app.getAssetManager(), app.getRootNode());
        currentDialogue.setCurrent(choice);
        refreshButtons();
    }
}
