/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables;

import com.epicest.reusables.interactable.InteractablesAppState;
import com.epicest.reusables.entity.player.CharacterInputAppState;
import com.epicest.reusables.entity.GameCharacterControl;
import com.epicest.reusables.scripting.ScriptAppState;
import com.jme3.app.FlyCamAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author EpicestGamer
 */
public abstract class PixelApplication extends SimpleApplication {

    /**
     * Graphics
     */
    protected Spatial curScene;
    /**
     * Physics
     */
    protected BulletAppState bulletAS;
    protected RigidBodyControl landscape;
    protected Vector3f normalGravity = new Vector3f(0, -70f, 0);
    /**
     * Player
     */
    protected CharacterInputAppState charInAS;
    protected InteractablesAppState interactAS;
    public GameCharacterControl player;
    /**
     * Scripter
     */
    protected ScriptAppState scriptAS;
    /**
     * Loading Types
     */
    public static final byte LOAD_NONE = 0;
    public static final byte LOAD_QUEUED = 1;
    public static final byte LOAD_STARTED = 2;
    public static final byte LOAD_FINISHED = 4;
    /**
     * Loading State
     */
    protected byte loadState = LOAD_NONE;
    /**
     * Loading Queue
     */
    protected String load = "";
    /**
     * states whether or not the game is in a world
     */
    protected boolean isInWorld = false;

    /**
     * Loads a scene
     */
    public void initScene(Spatial newScene) {
        //reset scene
        curScene = newScene;
        rootNode.detachAllChildren();
        viewPort.clearProcessors();
        //init other stuff
        assetsInit();
        displayInit();
        physicsInit();
        scriptingInit();
        otherInit();
        //finish
        //niftyState.gotoScreen("hud");
        loadState = LOAD_FINISHED;
        isInWorld = true;
    }

    /**
     * Initializes assets Honestly I'm not even sure I need this
     */
    protected abstract void assetsInit();

    /**
     * Initializes any graphical needs Such as putting the scene you are loading
     * on the screen, and any filters
     */
    protected void displayInit() {
        //Display
        rootNode.attachChild(curScene);
        /* Drop shadows 
         DirectionalLight sun = new DirectionalLight();
         sun.setDirection(new Vector3f(-0.57735026f, -0.57735026f, -0.57735026f));
         //[1.0, 0.9, 0.7, 1.0]
         final int SHADOWMAP_SIZE=1024;
         DirectionalLightShadowRenderer dlsr = new DirectionalLightShadowRenderer(assetManager, SHADOWMAP_SIZE, 3);
         dlsr.setLight(sun); //<==assuming a directional light
         viewPort.addProcessor(dlsr);
         //Post Processor Filters */
        viewPort.addProcessor(assetManager.loadFilter("Shaders/Default.j3f"));
    }

    /**
     * Initializes physics
     */
    protected void physicsInit() {
        //Initiallize AppState
        stateManager.detach(stateManager.getState(BulletAppState.class));
        bulletAS = new BulletAppState();
        stateManager.attach(bulletAS);
        bulletAS.getPhysicsSpace().setGravity(normalGravity);
        inputManager.setCursorVisible(false);
        //bulletAS.getPhysicsSpace().enableDebug(assetManager);
        //SceneCollisions
        CollisionShape sceneShape = CollisionShapeFactory.createMeshShape(curScene);
        landscape = new RigidBodyControl(sceneShape, 0);
        curScene.addControl(landscape);
        bulletAS.getPhysicsSpace().add(landscape);
        //Player
        playerInit("Spawn");
        //TODO: Invisible Walls
    }

    /**
     * Initializes the player physics object at a spawnpoint
     *
     * @param spawnNode the name of a Node for the player to spawn at
     */
    protected void playerInit(String spawnNode) {
        stateManager.detach(stateManager.getState(FlyCamAppState.class));
        stateManager.detach(stateManager.getState(CharacterInputAppState.class));
        stateManager.detach(stateManager.getState(InteractablesAppState.class));
        //Player
        Node playerNode = new Node("Player");
        playerNode.setLocalTranslation(rootNode.getChild(spawnNode).getWorldTranslation());
        //FOJItems itemset = new FOJItems();
        if (player == null) {
            player = new GameCharacterControl(1.1f, 5f, 7f, playerNode.getWorldTranslation());
            player.setGravity(normalGravity);
            player.setCamera(cam);
            player.setTrait(113, "a");
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
     * Initialises ScriptAppState
     */
    private void scriptingInit() {
        scriptAS = new ScriptAppState();
        stateManager.attach(scriptAS);
        interactAS.setScriptAS(scriptAS);
        //Initialize any scripts applicable in the scene
    }

    /**
     * Anything else needed to initialize during scene loading
     */
    protected abstract void otherInit();

    /**
     * Is used to queue a scene to be loaded in the next update
     *
     * @param scene argument to later be used in sceneInit()
     */
    public void loadScene(String scene) {
        onLoadStart();
        load = scene;
        loadState = LOAD_QUEUED;
    }

    /**
     * Simple Update Loop
     *
     * @param tpf time since last update
     */
    @Override
    public void simpleUpdate(float tpf) {
        //System.out.println(tpf);
        //TODO: add update code
        //player.update(tpf);
        if (loadState != LOAD_NONE) {
            if (loadState == LOAD_QUEUED) {
                onLoadStart();
                loadState = LOAD_STARTED;
            } else if (loadState == LOAD_STARTED) {
                initScene(assetManager.loadModel(load));
                //loadState = LOAD_NONE;
            } else if (loadState == LOAD_FINISHED) {
                onLoadFinish();
                loadState = LOAD_NONE;
            } else if (loadState < LOAD_FINISHED) {
                loadState++;
            }
        }
    }

    protected abstract void onLoadStart();

    protected abstract void onLoadFinish();

    /**
     * @return rootNode
     */
    public Node getRootNode() {
        return rootNode;
    }

    /**
     * @return isInWorld
     */
    public boolean getIsInWorld() {
        return isInWorld;
    }
}
