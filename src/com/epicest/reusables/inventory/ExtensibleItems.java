package com.epicest.reusables.inventory;

import java.util.ArrayList;

/**
 * Lets you use multiple Items classes at the same time
 *
 * @author EpicestGamer
 */
public class ExtensibleItems implements Items {

 protected ArrayList<Items> itemsList = new ArrayList<Items>();

 public void addItems(Items items) {
  itemsList.add(items);
 }

 public void removeItems(Items items) {
  itemsList.remove(this);
 }

 @Override
 public Item getItem(String code) {
  for (Items items : itemsList) {
   Item item = items.getItem(code);
   if (item != null) {
    return item;
   }
  }
  return null;
 }

}
