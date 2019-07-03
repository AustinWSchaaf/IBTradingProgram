/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ib;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author austinschaaf
 */
public class InPlayStocks {
    private Map<String, Integer> stocks;
    
    public InPlayStocks(String[] s){
        stocks = new HashMap<String, Integer>();
        for (int i = 0; i < s.length; i++){
            stocks.put(s[i], i + 1);
        }
    }
}
