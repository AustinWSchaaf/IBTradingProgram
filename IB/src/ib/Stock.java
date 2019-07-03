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
public class Stock{
    
    private static String ticker;
    private static int id;
    private static int BENCHMARK = 100;
    
    
    public Stock(String ticker, int id){
        this.ticker = ticker;
        this.id = id;
    }
        
    public int getId(){
        return id;
    }
    
    public String getTicker(){
        return ticker;
    }
    
    public void size(int quantity){
        if (quantity > BENCHMARK){
            
        }
    }
    
}
