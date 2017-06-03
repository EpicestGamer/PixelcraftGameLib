/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.entity;

import com.epicest.reusables.inventory.*;
import com.jme3.bullet.control.*;
import com.jme3.input.controls.*;
import com.jme3.math.*;
import com.jme3.renderer.*;
import com.jme3.scene.*;
import com.jme3.scene.control.*;
import java.util.*;

/**
 * Entity control.
 *
 * @author Rickard
 * @author EpicestGamer
 */
public class GameCharacterControl extends BetterCharacterControl implements ActionListener, AnalogListener {

 /**
  * Movement variables
  */
 boolean forward = false, backward = false, leftRotate = false, rightRotate = false, leftStrafe = false, rightStrafe = false;
 protected Node head = new Node("Head");
 private float yaw = 0;
 /**
  * The speed of this control when sprinting
  */
 protected float sprintSpeed = 12;
 /**
  * The speed of the control when walking
  */
 protected float walkSpeed = 7;
 /**
  *
  */
 protected float moveSpeed = walkSpeed;
 protected float usedSpeed = moveSpeed;
 /**
  * Traits
  */
 HashMap<String, Float> traits = new HashMap();
 /**
  * Inventory
  */
 protected WeightedInventory inventory;
 /**
  * Items
  */
 protected Items itemSet;

 public GameCharacterControl(float radius, float height, float mass) {
  super(radius, height, mass);
  setPhysicsLocation(new Vector3f(0f, 256f, 0f));
  init(radius, height, mass, location);
 }

 public GameCharacterControl(float radius, float height, float mass, Vector3f location) {
  super(radius, height, mass);
  setPhysicsLocation(location);
  init(radius, height, mass, location);
 }

 protected void init(float radius, float height, float mass, Vector3f location) {
  head.setLocalTranslation(0, height * 0.85f, 0);
  this.setJumpForce(new Vector3f(0, 200, 0));
  this.setDuckedFactor(2f);
 }

 @Override
 public void update(float tpf) {
  super.update(tpf);
  Vector3f modelForwardDir = spatial.getWorldRotation().mult(Vector3f.UNIT_Z);
  Vector3f modelLeftDir = spatial.getWorldRotation().mult(Vector3f.UNIT_X);

  walkDirection.set(0, 0, 0);
  if (forward) {
   walkDirection.addLocal(modelForwardDir.mult(usedSpeed));
  }
  if (backward) {
   walkDirection.addLocal(modelForwardDir.negate().multLocal(usedSpeed));
  }
  if (leftStrafe) {
   walkDirection.addLocal(modelLeftDir.mult(usedSpeed));
  }
  if (rightStrafe) {
   walkDirection.addLocal(modelLeftDir.negate().multLocal(usedSpeed));
  }
  setWalkDirection(walkDirection);
 }

 @Override
 public void setSpatial(Spatial spatial) {
  super.setSpatial(spatial);
  if (spatial instanceof Node) {
   ((Node) spatial).attachChild(head);
  }
 }

 public void onAction(String binding, boolean value, float tpf) {
  if (binding.equals("StrafeLeft")) {
   leftStrafe = value;
  } else if (binding.equals("StrafeRight")) {
   rightStrafe = value;
  } else if (binding.equals("MoveForward")) {
   forward = value;
  } else if (binding.equals("MoveBackward")) {
   backward = value;
  } else if (binding.equals("Jump") && value) {
   jump();
  } else if (binding.equals("Sprint")) {
   if (value) {
    moveSpeed = sprintSpeed;
   } else {
    moveSpeed = walkSpeed;
   }
  } else if (binding.equals("Duck")) {
   setDucked(value);//Logger.getLogger("").log(Logger.Level.WARN,"DAUGH");
  } else if (binding.equals("RClick")) {
   //Aim, Block, Etc
  } else if (binding.equals("LClick")) {
   //Shoot, Hit, Etc
  }
 }

 public void onAnalog(String name, float value, float tpf) {
  if (name.equals("RotateLeft")) {
   rotate(tpf * value);
  } else if (name.equals("RotateRight")) {
   rotate(-tpf * value);
  } else if (name.equals("LookUp")) {
   lookUpDown(value * tpf);
  } else if (name.equals("LookDown")) {
   lookUpDown(-value * tpf);
  } /**
   *
   */
  else if (name.equals("MoveForward") || name.equals("MoveBackward") || name.equals("StrafeLeft") || name.equals("StrafeRight")) {
   usedSpeed = value * moveSpeed;
  }
  /**
   *
   */
 }

 public void setCamera(Camera cam) {
  CameraNode camNode = new CameraNode("CamNode", cam);
  camNode.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
  head.attachChild(camNode);
  /**
   * Uncomment for chasecam
   */
//        camNode.setLocalTranslation(new Vector3f(0, 5, -5));
//        camNode.lookAt(head.getLocalTranslation(), Vector3f.UNIT_Y);
 }

 protected void rotate(float value) {
  Quaternion rotate = new Quaternion().fromAngleAxis(FastMath.PI * value, Vector3f.UNIT_Y);
  rotate.multLocal(viewDirection);
  setViewDirection(viewDirection);
 }

 public void setTrait(float initValue, String key) {
  traits.put(key, initValue);
 }

 public float getTrait(String key) {
  return traits.get(key);
 }

 protected void lookUpDown(float value) {
  yaw += value;
  yaw = FastMath.clamp(yaw, -FastMath.HALF_PI, FastMath.HALF_PI);
  head.setLocalRotation(new Quaternion().fromAngles(yaw, 0, 0));
 }

 public Items getItemSet() {
  return itemSet;
 }

 public void setItemSet(Items itemSet) {
  // return itemSet;
  inventory = new WeightedInventory(itemSet);
  this.itemSet = itemSet;
 }

 public void addItem(String item) {
  inventory.addItem(item);
 }
}
