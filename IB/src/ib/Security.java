/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ib;

/**
 *
 * @author austinschaaf
 */
public class Security {
    
    private static int orderID = 0;
    private static int stopLossID = 0;
    private static int targetID = 0;
    
    private static int ID = 0;
    
    public Security (int ID){
        this.ID = ID;
    }
    
    public int getID(){
        return ID;
    }
    
}
