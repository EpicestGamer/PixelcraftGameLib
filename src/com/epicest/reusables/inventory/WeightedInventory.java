/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.inventory;

import com.epicest.reusables.inventory.Items;
import java.util.HashMap;

/**
 * Weighted Inventory System.
 *
 * @author EpicestGamer
 */
public class WeightedInventory {

    /**
     * Maximum holdable weight before overencumbrance.
     */
    protected int maxWeight = 360;
    /**
     * The set of items this inventory will use.
     */
    public Items itemSet;
    /**
     * the inventory itself.
     */
    private HashMap inventory = new HashMap();

    /**
     * Initialises inventory.
     *
     * @param is the item set to use.
     */
    public WeightedInventory(Items is) {
        itemSet = is;
    }

    /**
     * Adds an item to the inventory.
     */
    public void addItem(String code) {
        if (inventory.containsKey(code)) {
            int amount = (Integer) inventory.get(code);
            amount++;
            inventory.put(code, amount);
        } else {
            inventory.put(code, 1);

        }
    }

    /**
     * Removes an item from the inventory.
     */
    public void removeItem(String code) {
        if (inventory.containsKey(code)) {
            int amount = (Integer) inventory.get(code);
            if (amount > 1) {
                amount--;
                inventory.put(code, amount);
            } else {
                inventory.remove(code);
            }
        }
    }

    /**
     * Updates the status of overencumbrance, and then returns that status.
     *
     * @return the status of overencumbrance.
     */
    public boolean getOverEncumbered() {
        String[] itemCodes = convertToString(inventory.keySet().toArray());
        int weight = 0;
        for (int i = 0; i < itemCodes.length; i++) {
            int amount = (Integer) inventory.get(itemCodes[i]);
            int curItemWeight = itemSet.getItemWeight(itemCodes[i]);
            weight += (amount * curItemWeight);
        }
        if (weight > maxWeight) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the max weight a player can handle before getting overencumbered.
     */
    public int getMaxWeight() {
        return maxWeight;
    }

    /**
     * Sets a max wight
     *
     * @param weight the max weight a player can handle before getting
     * overencumbered.
     */
    public void setMaxWeight(int weight) {
        maxWeight = weight;
    }

    /**
     * @return current weight from inventory.
     */
    public int getWeight() {
        String[] itemCodes = convertToString(inventory.keySet().toArray());
        int weight = 0;
        for (int i = 0; i < itemCodes.length; i++) {
            int amount = (Integer) inventory.get(itemCodes[i]);
            int curItemWeight = itemSet.getItemWeight(itemCodes[i]);
            weight += (amount * curItemWeight);
        }
        return weight;
    }

    /**
     * Used in testing.
     *
     * @return the list of Items this inventory holds, with debug info
     */
    public String getItems() {
        String stuff = "\nSYNTAX: Code, Name, Quantity, Weight (per item)";
        String[] itemCodes = convertToString(inventory.keySet().toArray());
        for (int i = 0; i < itemCodes.length; i++) {
            int amount = (Integer) inventory.get(itemCodes[i]);
            int curItemWeight = itemSet.getItemWeight(itemCodes[i]);
            stuff += "\n" + itemCodes[i] + " " + itemSet.getItemName(itemCodes[i]) + " " + inventory.get(itemCodes[i]) + " " + itemSet.getItemWeight(itemCodes[i]);
        }
        return stuff;
    }

    /**
     * Used in other functions of this Object to convert an Object array to a
     * String array.
     *
     * @param objectArray
     * @return the string array
     */
    public static String[] convertToString(Object[] objectArray) {
        String[] strArray = new String[objectArray.length];
        for (int i = 0; i < objectArray.length; i++) {
            strArray[i] = (String) objectArray[i];
        }
        return strArray;
    }
}
