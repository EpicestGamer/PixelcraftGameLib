/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.launcher.action;

/**
 *
 * @author mjspr
 */
public class QuitAction implements ButtonAction {
    
    @Override
    public void doAction() {
        System.exit(1);
    }
}
