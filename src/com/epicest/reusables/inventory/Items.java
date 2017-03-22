/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.inventory;

/**
 * An set of Item objects
 *
 * @author EpicestGamer
 */
public interface Items {

 /**
  * @param code the identification of an Item.
  */
 public abstract Item getItem(String code);
}
