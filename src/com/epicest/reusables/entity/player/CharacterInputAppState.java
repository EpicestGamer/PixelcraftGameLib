/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.entity.player;

import com.epicest.reusables.PixelApplication;
import com.epicest.reusables.interactable.InteractablesAppState;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.InputManager;
import com.jme3.input.Joystick;
import com.jme3.input.JoystickAxis;
import com.jme3.input.JoystickButton;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.JoyAxisTrigger;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.epicest.reusables.entity.GameCharacterControl;
import com.jme3.input.JoyInput;
import com.jme3.input.controls.JoyButtonTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import java.util.ArrayList;
import java.util.List;

/**
 * Application state to player input.
 *
 * @author Rickard
 * @author EpicestGamer
 */
public class CharacterInputAppState extends AbstractAppState implements AnalogListener, ActionListener {

    /**
     * Application this appstate services.
     */
    private PixelApplication app;
    /**
     * Inputmanager we essentially get input from.
     */
    private InputManager inputManager;
    /**
     * Entity this appsate services.
     */
    private GameCharacterControl character;
    /**
     * Camera sensitivity.
     */
    private float sensitivity = 80;

    /**
     * Input mappings.
     */
    public enum InputMapping {

        Jump,
        Interact,
        LClick,
        RClick,
        Duck,
        Sprint,
        RotateLeft,
        ControllerRL,
        RotateRight,
        ControllerRR,
        LookUp,
        ControllerLU,
        LookDown,
        ControllerLD,
        StrafeLeft,
        StrafeRight,
        MoveForward,
        MoveBackward,
        Pause,
        Inventory;
    }
    /**
     * Input mapping names.
     */
    private String[] mappingNames = new String[]{InputMapping.Jump.name(), InputMapping.Interact.name(), InputMapping.RClick.name(), InputMapping.LClick.name(), InputMapping.Duck.name(), InputMapping.Sprint.name(), InputMapping.RotateLeft.name(), InputMapping.RotateRight.name(), InputMapping.LookUp.name(), InputMapping.LookDown.name(), InputMapping.StrafeLeft.name(), InputMapping.StrafeRight.name(), InputMapping.MoveForward.name(), InputMapping.MoveBackward.name()};

    /**
     * Initialises the application state.
     *
     * @param app Application this appstate services.
     */
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (PixelApplication) app;
        this.inputManager = app.getInputManager();
        addInputMappings();
        assignJoysticks();
    }

    /**
     * Assigns standard (keyboard and mouse) mappings.
     */
    private void addInputMappings() {
        inputManager.addMapping(InputMapping.Jump.name(), new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addMapping(InputMapping.Interact.name(), new KeyTrigger(KeyInput.KEY_E));
        inputManager.addMapping(InputMapping.RClick.name(), new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
        inputManager.addMapping(InputMapping.LClick.name(), new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addMapping(InputMapping.Duck.name(), new KeyTrigger(KeyInput.KEY_LCONTROL));
        inputManager.addMapping(InputMapping.Sprint.name(), new KeyTrigger(KeyInput.KEY_LSHIFT));
        inputManager.addMapping(InputMapping.RotateLeft.name(), new MouseAxisTrigger(MouseInput.AXIS_X, true));
        inputManager.addMapping(InputMapping.RotateRight.name(), new MouseAxisTrigger(MouseInput.AXIS_X, false));
        inputManager.addMapping(InputMapping.LookUp.name(), new MouseAxisTrigger(MouseInput.AXIS_Y, true));
        inputManager.addMapping(InputMapping.LookDown.name(), new MouseAxisTrigger(MouseInput.AXIS_Y, false));
        inputManager.addMapping(InputMapping.StrafeLeft.name(), new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping(InputMapping.StrafeRight.name(), new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping(InputMapping.MoveForward.name(), new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping(InputMapping.MoveBackward.name(), new KeyTrigger(KeyInput.KEY_S));
        inputManager.addListener(this, mappingNames);
    }

    /**
     * Assigns Joystick (Gamepad) mappings.
     */
    private void assignJoysticks() {
        Joystick[] joysticks = inputManager.getJoysticks();
        if (joysticks != null) {
            for (Joystick j : joysticks) {
                for (JoystickAxis axis : j.getAxes()) {
                    if (axis == j.getXAxis()) {
                        axis.assignAxis(InputMapping.StrafeRight.name(), InputMapping.StrafeLeft.name());
                    } else if (axis == j.getYAxis()) {
                        axis.assignAxis(InputMapping.MoveBackward.name(), InputMapping.MoveForward.name());
                    } else if (axis.getLogicalId().equals("ry")) {
                        axis.assignAxis(InputMapping.ControllerLU.name(), InputMapping.ControllerLD.name());
                    } else if (axis.getLogicalId().equals("rx")) {
                        axis.assignAxis(InputMapping.ControllerRR.name(), InputMapping.ControllerRL.name());
                    }
                }

                for (JoystickButton button : j.getButtons()) {
                    switch (button.getButtonId()) {
                        case 0:
                            button.assignButton(InputMapping.Jump.name());
                            break;
                        case 1:
                            button.assignButton(InputMapping.Duck.name());
                            break;
                        case 2:
                            button.assignButton(InputMapping.Interact.name());
                            break;
                        case 8:
                            button.assignButton(InputMapping.Sprint.name());
                            break;
                    }
                }
            }


        }
    }

    /**
     * Cleans up any variables.
     */
    @Override
    public void cleanup() {
        super.cleanup();
        for (InputMapping i : InputMapping.values()) {
            if (inputManager.hasMapping(i.name())) {
                inputManager.deleteMapping(i.name());
            }
        }
        inputManager.removeListener(this);
    }

    /**
     * Called on input from an analog device (such as mouse movement or
     * individual joysticks).
     *
     * @param action the mapping the input is set to.
     * @param value the amount of movement.
     */
    @Override
    public void onAnalog(String action, float value, float tpf) {
        if (character != null) {
            if ((action.equals(InputMapping.ControllerLD)) || (action.equals(InputMapping.ControllerLU)) || (action.equals(InputMapping.ControllerRR)) || (action.equals(InputMapping.ControllerRL))) {
                if (value >= 0.01) {
                    character.onAnalog(action, value * sensitivity, tpf);
                }
            } else {
                character.onAnalog(action, value * sensitivity, tpf);
            }
        }
    }

    /**
     * Called on input from a binary device (buttons).
     *
     * @param action the mapping the input is set to.
     * @param isPressed whether or not the button is pressed.
     */
    @Override
    public void onAction(String action, boolean isPressed, float tpf) {
        if ((character != null) && (!action.equals("Interact"))) {
            character.onAction(action, isPressed, tpf);
        } else if (action.equals("Interact")) {
            ((InteractablesAppState) app.getStateManager().getState(InteractablesAppState.class)).onAction(action, isPressed);
        }
    }

    /**
     * Sets a entity to enact controls upon
     */
    public void setCharacter(GameCharacterControl character) {
        this.character = character;
    }
}
