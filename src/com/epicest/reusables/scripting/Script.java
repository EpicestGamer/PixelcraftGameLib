/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.scripting;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Scripts to use in Runner.
 *
 * @author EpicestGamer
 */
public class Script {

    File script;
    BufferedInputStream bin;

    /**
     * Sets a script to run.
     */
    public void setScript(String path) {
        try {
            script = new File(path);
            FileInputStream in = new FileInputStream("assets/" + script);
            bin = new BufferedInputStream(in);
        } catch (Exception e) {
            Logger.getLogger("").log(Level.WARNING, "Umm" + e.toString());
            script = null;
            bin = null;
        }
    }

    /**
     * Runs this script once.
     */
    public void runTick(ScriptAppState scriptAS, float tpf, Runner in) {
        StringBuffer commandBuffer = new StringBuffer();
        String commands = null;
        try {
            bin.mark(2048);
            int nin = 0;
            char inChar;
            do {
                nin = bin.read();
                inChar = (char) nin;
                if (nin != -1) {
                    commandBuffer.append(inChar);
                }
            } while (nin != -1);
            commands = commandBuffer.toString();
            for (String command : commands.split("\n")) {
                if (command.equals("") || command.startsWith("#")) {
                } else {
                    scriptAS.run(command, in);
                }
            }
            bin.reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
