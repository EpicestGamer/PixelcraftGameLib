/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.scripting.preset;

import com.epicest.reusables.scripting.Runner;
import com.epicest.reusables.scripting.preset.*;
import com.epicest.reusables.scripting.Script;
import com.epicest.reusables.scripting.Script;
import com.epicest.reusables.scripting.ScriptAppState;

/**
 * Used when a player interacts with an Item. Preset scripts are faster than
 * normal interpreted scripts
 *
 * @author EpicestGamer
 */
public class OnItem extends Script {

    /**
     * Does nothing in preset scripts.
     */
    @Override
    public void setScript(String path) {
        //do nothing
    }

    /**
     * Runs code for 1.Adding an item 2.Updating players Inventory 3.Deletes
     * Item.
     */
    public void runTick(ScriptAppState scriptAS, float tpf, Runner in) {
        scriptAS.run("additem~" + in.getObject().getUserData(""), in);
        scriptAS.run("dispose~" + in.getObject().getName(), in);
    }
}
