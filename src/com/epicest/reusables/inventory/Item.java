package com.epicest.reusables.inventory;

/**
 * A Interface for a general Item
 *
 * @author EpicestGamer
 */
public interface Item {

 /**
  * @return the item code
  */
 public String getCode();

 /**
  * @return the type/catagory of the item
  */
 public String getType();

 /**
  * @return the item name
  */
 public String getName();

 /**
  * @return the item weight
  */
 public double getWeight();

 /**
  * @return the Items model
  */
 public String getModelPath();

 /**
  * @return the script for use
  */
 public String getUseScript();

}
