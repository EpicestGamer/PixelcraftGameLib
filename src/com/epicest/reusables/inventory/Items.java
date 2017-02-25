/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.inventory;

/**
 * An Item Skeleton
 *
 * @author EpicestGamer
 */
public interface Items {

 /**
  * @param code the identification of an Item.
  */
 public abstract String getItemName(String code);

 /**
  * @param code the identification of an Item.
  */
 public abstract int getItemWeight(String code);
}
