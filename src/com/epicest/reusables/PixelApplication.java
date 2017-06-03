/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables;

import com.epicest.reusables.entity.*;
import com.epicest.reusables.entity.player.*;
import com.epicest.reusables.interactable.*;
import com.epicest.reusables.loading.*;
import com.epicest.reusables.scripting.*;
import com.jme3.app.*;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.*;
import com.jme3.bullet.control.*;
import com.jme3.input.*;
import com.jme3.math.*;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.*;
import java.util.concurrent.*;

/**
 *
 * @author EpicestGamer
 */
public abstract class PixelApplication extends SimpleApplication {

 /**
  * Graphics.
  */
 protected Spatial curScene;
 /**
  * Physics.
  */
 protected BulletAppState bulletAS;
 /**
  * Worldspace physics object.
  */
 protected RigidBodyControl landscape;
 /**
  * Gravity.
  */
 protected Vector3f normalGravity = new Vector3f(0, -70f, 0);
 /**
  * Player Input.
  */
 protected CharacterInputAppState charInAS;
 /**
  * Player Interaction.
  */
 protected InteractablesAppState interactAS;
 /**
  * Player Entity.
  */
 public GameCharacterControl player;
 /**
  * Scripter.
  */
 protected ScriptAppState scriptAS;
 /**
  * Game isnt loading anything.
  */
 public static final byte LOAD_NONE = 0;
 /**
  * Game has something queued for a load.
  */
 public static final byte LOAD_QUEUED = 1;
 /**
  * Game has started loading something.
  */
 public static final byte LOAD_STARTED = 2;
 /**
  * Game has finished loading something.
  */
 public static final byte LOAD_FINISHED = 4;
 /**
  * Loading AppState.
  */
 protected AdvancedLoadingAppState loaderAppState;
 /**
  * Loading State.
  */
 protected byte loadState = LOAD_NONE;
 /**
  * Loading Queue.
  */
 protected String load = "";
 /**
  * Loading Queue Spawnpoint.
  */
 protected String spawn = "Spawn";
 /**
  * states whether or not the game is in a world.
  */
 protected boolean isInWorld = false;
 /**
  * Boolean to tell wether or not the scene has initialized before
  */
 protected boolean sceneInitedOnce = false;
 /**
  * Describes whether or not this application is marked for a dialogue GUI
  */
 protected boolean markedForDialogue = false;
 /**
  * Current Application
  */
 public static PixelApplication currentApplication;

 public PixelApplication() {
  loaderAppState = new AdvancedLoadingAppState(this);

  scriptAS = new ScriptAppState();
  stateManager.attach(scriptAS);

  currentApplication = this;
 }

 /**
  * Is used to queue a scene to be loaded in the next update.
  *
  * @param scene argument to later be used in sceneInit().
  */
 public void loadScene(String scene, String spawnpoint) {
  load = scene;
  spawn = spawnpoint;
  loadState = LOAD_QUEUED;
 }

 public void openLoad() {
  try {

   final Spatial scene = assetManager.loadModel(load);
   this.enqueue(new Callable<Boolean>() {
    @Override
    public Boolean call() {
     preInitScene();
     initScene(scene);
     return true;
    }
   });
  } catch (Exception e) {
   e.printStackTrace();
   //loaderAppState.markForLoad();
  }
 }

 /**
  * Runs directly before loading a scene
  */
 public void preInitScene() {
  //reset scene
  rootNode.detachAllChildren();
  //viewPort.clearProcessors();
 }

 /**
  * Loads a scene.
  */
 public void initScene(Spatial newScene) {
  curScene = newScene;
  //init scene
  displayInit();
  physicsInit();
  otherInit();
  //finish
  //niftyState.gotoScreen("hud");
  loadState = LOAD_FINISHED;
  sceneInitedOnce = true;
  isInWorld = true;
 }

 /**
  * Initializes any graphical needs Such as putting the scene you are loading on
  * the screen, and any filters.
  */
 protected void displayInit() {
  //Display
  rootNode.attachChild(curScene);

  /*//Post Processor Filters
  if (!sceneInitedOnce) {
   viewPort.addProcessor(assetManager.loadFilter("Shaders/Default.j3f"));
  } */
 }

 /**
  * Initializes physics.
  */
 protected void physicsInit() {
  //Initiallize AppState
  stateManager.detach(stateManager.getState(BulletAppState.class));
  bulletAS = new BulletAppState();
  stateManager.attach(bulletAS);
  bulletAS.getPhysicsSpace().setGravity(normalGravity);
  inputManager.setCursorVisible(false);
  //SceneCollisions
  bulletAS.getPhysicsSpace().addAll(curScene);
  //Player
  playerInit(spawn);
  //TODO: Invisible Walls
 }

 /**
  * Initializes the player physics object at a spawnpoint.
  *
  * @param spawnNode the name of a Node for the player to spawn at.
  */
 protected void playerInit(String spawnNode) {
  stateManager.detach(stateManager.getState(FlyCamAppState.class));
  stateManager.detach(stateManager.getState(CharacterInputAppState.class));
  stateManager.detach(stateManager.getState(InteractablesAppState.class));
  //Player
  Node playerNode = new Node("Player");
  playerNode.setLocalTranslation(rootNode.getChild(spawnNode).getWorldTranslation());
  if (player == null) {
   player = new GameCharacterControl(1.1f, 5f, 7f, playerNode.getWorldTranslation());
   player.setGravity(normalGravity);
   player.setCamera(cam);
  }
  player.setSpatial(playerNode);
  playerNode.addControl(player);
  bulletAS.getPhysicsSpace().add(player);

  charInAS = new CharacterInputAppState();
  charInAS.setCharacter(player);
  stateManager.attach(charInAS);
  interactAS = new InteractablesAppState();
  stateManager.attach(interactAS);
  rootNode.attachChild(playerNode);
 }

 /**
  * Initializes anything else needed at the end of loading
  */
 protected abstract void otherInit();

 /**
  * Simple Update Loop.
  *
  * @param tpf time since last update.
  */
 @Override
 public void simpleUpdate(float tpf) {
  //TODO: add update code
  if (loadState != LOAD_NONE) {
   if (loadState == LOAD_QUEUED) {
    onLoadStart();
    loaderAppState.markForLoad();
    loadState = LOAD_STARTED;
   } else if (loadState == LOAD_STARTED) {
    //Do nothing
   } else if (loadState == LOAD_FINISHED) {
    onLoadFinish();
    Node entranceNode = (Node) rootNode.getChild("EnterNode");
    if (entranceNode != null) {
     Runner runner = new Runner();
     runner.setScript((String) entranceNode.getUserData("script"));
     scriptAS.addTempRunner(runner);
    }
    loadState = LOAD_NONE;
   } else if (loadState < LOAD_FINISHED) {
    loadState++;
   }
  }
 }

 /**
  * Is run when loading starts.
  */
 protected abstract void onLoadStart();

 /**
  * Is run when loading ends.
  */
 protected abstract void onLoadFinish();

 /**
  * @return the main asset manager for the application
  */
 public AssetManager getAssetManager() {
  return assetManager;
 }

 /**
  * @return the main input manager for the application
  */
 public InputManager getInputManager() {
  return inputManager;
 }

 /**
  * @return the root node of the application
  */
 public Node getRootNode() {
  return rootNode;
 }

 /**
  * @return the main view port for the application
  */
 public ViewPort getViewPort() {
  return viewPort;
 }

 /**
  * @param mark determines whether or not the application is marked for a
  * dialogue sequence
  */
 public void markForDialogue(boolean mark) {
  markedForDialogue = mark;
 }

 /**
  * @return whether or not the application is marked for a dialogue sequence
  */
 public boolean getMarkedForDialogue() {
  return markedForDialogue;
 }

 /**
  * @return the current library version
  */
 public static String getVersion() {
  return "Pixelcraft Game Library\n"
          + " Alpha 0.1.2";
 }

 /**
  * @return a value based on whether a scene is loaded
  */
 public boolean getIsInWorld() {
  return isInWorld;
 }

 /**
  * @inheritDoc
  */
 @Override
 public void destroy() {
  super.destroy();
  loaderAppState.stop();
 }
}
