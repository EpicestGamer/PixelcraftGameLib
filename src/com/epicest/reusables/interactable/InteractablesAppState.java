/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.interactable;

import com.epicest.reusables.*;
import com.epicest.reusables.scripting.*;
import com.epicest.reusables.scripting.preset.*;
import com.jme3.app.*;
import com.jme3.app.state.*;
import com.jme3.collision.*;
import com.jme3.math.*;
import com.jme3.scene.*;
import java.util.logging.*;

/**
 * Application state to service interaction between the player and in-game
 * objects.
 *
 * @author EpicestGamer
 */
public class InteractablesAppState extends AbstractAppState {

    /**
     * Application this appstate services.
     */
    private PixelApplication app;

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
     * Interacts with something.
     *
     * @param name Button name, should always be equal to "Interact".
     * @param isPressed Whether or not the button is pressed.
     */
    public void onAction(String name, boolean isPressed) {
        try {
            if (name.equals("Interact") && isPressed) {
                CollisionResults results = new CollisionResults();
                Ray ray = new Ray(app.getCamera().getLocation(), app.getCamera().getDirection());
                app.getRootNode().getChild("Interactables").collideWith(ray, results);
                if (results.size() >= 1) {
                    float dist = results.getCollision(0).getDistance();
                    Geometry geom = results.getCollision(0).getGeometry();
                    if (dist <= 20) {
                        Runner runner = new Runner();
                        runner.setObject(geom);
                        if (((String) geom.getUserData("onInteract")).equals("onDoor")) {
                            runner.setScript(new OnDoor());
                        } else if (((String) geom.getUserData("onInteract")).equals("onItem")) {
                            runner.setScript(new OnItem());
                        } else {
                            runner.setScript(((String) geom.getUserData("onInteract")));
                        }
                        app.getStateManager().getState(ScriptAppState.class).addTempRunner(runner);
                    }
                }
            }

        } catch (Exception e) {
            Logger.getLogger("").log(Level.WARNING, "An Exception Occured While Interacting, It was probably a NPE\n"
                    + "Debugging Info:\n"
                    + "StackTrace: " + e.getStackTrace());
        }


    }
}
