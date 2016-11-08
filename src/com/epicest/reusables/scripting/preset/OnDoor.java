/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.scripting.preset;

import com.epicest.reusables.scripting.*;

/**
 * Used when a player interacts with an Door. Preset scripts are faster than
 * normal interpreted scripts.
 *
 * @author EpicestGamer
 */
public class OnDoor extends Script {

    /**
     * Does nothing in preset scripts.
     */
    @Override
    public void setScript(String path) {
        //do nothing
    }

    /**
     * Runs code for 1.loading a new scene.
     */
    @Override
    public void runTick(ScriptAppState scriptAS, float tpf, Runner in) {
        scriptAS.run("loadmap~" + in.getObject().getUserData("toPlace"), in);
    }
}
