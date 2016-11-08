/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.scripting;

import com.epicest.reusables.*;
import com.jme3.app.*;
import com.jme3.app.state.*;
import java.util.*;
import org.bushe.swing.event.*;

/**
 * Application state to service scripts (.esf epicest script file).
 *
 * @author EpicestGamer
 */
public class ScriptAppState extends AbstractAppState {

    /**
     * Application this appstate services.
     */
    public PixelApplication app;

    /*public enum ScriptCommands {

     Let,
     If,
     Print, //for debug
     AddItem,
     LoadMap,
     PlayerGoTo,
     Dispose
     };*/
    /**
     * Runners.
     */
    protected ArrayList<Runner> runners = new ArrayList();
    protected ArrayList<Runner> tempRunners = new ArrayList();
    /**
     * Script variables.
     */
    protected boolean if_ = false;
    protected HashMap<String, String> variables = new HashMap();

    /**
     * Initialises the application state.
     *
     * @param app Application this appstate services.
     */
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (PixelApplication) app;
    }

    /**
     * Adds a Runner to run continuously.
     *
     * @param runner Runner to add.
     */
    public void addRunner(Runner runner) {
        runners.add(runner);
    }

    /**
     * Adds a Runner to run once..
     *
     * @param runner Runner to add.
     */
    public void addTempRunner(Runner runner) {
        tempRunners.add(runner);
    }

    /**
     * Clears Runners.
     */
    public void clearAllRunners() {
        runners = new ArrayList();
        tempRunners = new ArrayList();
    }

    /**
     * Runs any Runners applicable and clears temporary Runners.
     */
    @Override
    public void update(float tpf) {
        for (Runner runner : runners) {
            runner.runScriptTick(this, tpf);
        }
        for (Runner runner : tempRunners) {
            runner.runScriptTick(this, tpf);
        }
        tempRunners = new ArrayList();
    }

    /**
     * Runs a single command.
     */
    public void run(String command, Runner runner) {
        int i = 0;
        int commandMark = -1;
        HashMap<String, String> commandData = new HashMap();
        if (command.toLowerCase().equals("endif")) {
            if_ = false;
        } else if (!if_) {
            for (String subCommand : command.split("~")) {
                if (i == 0) {
                    switch (subCommand.toLowerCase()) {
                        case "let":
                            commandMark = 0;
                            break;
                        case "if":
                            commandMark = 1;
                            break;
                        case "print":
                            commandMark = 2;
                            break;
                        case "additem":
                            commandMark = 3;
                            break;
                        case "loadmap":
                            commandMark = 4;
                            break;
                        case "playergoto":
                            commandMark = 5;
                            break;
                        case "dispose":
                            commandMark = 6;
                            break;
                    }
                } else {
                    if (subCommand.startsWith("variable-")) {
                        String[] variable = subCommand.split("-");
                        subCommand = variables.get(variable[1]);
                    }
                    switch (commandMark) {
                        case 0:
                            if (i == 1) {
                                commandData.put("variable-name", subCommand);
                            } else {
                                variables.put(commandData.get("variable-name"), subCommand);
                            }
                            break;
                        case 1:
                            if (i == 1) {
                                commandData.put("if-1", subCommand);
                            } else if (i == 2) {
                                commandData.put("if-Operator", subCommand);
                            } else {
                                commandData.put("if-2", subCommand);
                                String if1 = commandData.get("if-1");
                                String if2 = commandData.get("if-2");
                                String operator = commandData.get("if-Operator");
                            }
                            //commandMark == ScriptCommands.If.ordinal();
                            break;
                        case 2:
                            Logger.getLogger("").log(Logger.Level.WARN, subCommand);
                            break;
                        case 3:
                            app.player.addItem(subCommand);
                            break;
                        case 4:
                            app.loadScene(subCommand);
                            break;
                        case 5:
                            app.getRootNode().getChild(subCommand);
                            break;
                        case 6:
                            app.getRootNode().detachChild(app.getRootNode().getChild(subCommand));
                            break;
                    }
                }
                i++;
            }
        }
    }
}
