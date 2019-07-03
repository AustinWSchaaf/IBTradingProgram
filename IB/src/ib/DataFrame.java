/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author austinschaaf
 */
public class DataFrame {
    
    private static double price;
    private static int n;
    private static double mean;
    private static int max;
    private static int min;
    private static int total;
    private static int volume;
    private static int firstPoint;
    private static double slope;
    private static Map<Integer, Integer> xMax;
    private static Map<Integer, Integer> xMin;
    
    public DataFrame(double price, int size){
        this.price = price;
        this.n = 1;
//        this.sizes.add(size);
        this.mean = size;
        this.max = size;
        this.min = size;
        this.total = size;
        this.volume = 0;
        this.firstPoint = size;
        this.slope = 0.0;
        xMax = new HashMap<Integer, Integer>();
        xMin = new HashMap<Integer, Integer>();
        xMax.put(max, n);
        xMin.put(min, n);
        
    }
    
    public void addData(int size){
        n += 1;
        total += size;
        
        if (size >= max){
            max = size;
            xMax.put(max, n);
        }else if (size <= min){
            min = size;
            xMin.put(min, n);
        }
    
        mean = total / n;        
    }
    
    public void addTrade(double price, int size){
        volume += size;
    }
    
    public int getMax(){
        return max;
    }
    
    public int getMin(){
        return min;
    }
    
    public double getMean(){
        return mean;
    }
    
    public int getVolume(){
        return volume;
    }
    
    public double getPrice(){
        return price;
    }
   
    public void print(){
        String t = "Max: " + max + " Min: " + min + " Mean: " + mean + " Volume: " + volume;
        System.out.println(t);
    }
    
    public int maxLocation(){
        return xMax.get(max);
    }
    
    public int minLocation(){
        return xMin.get(min);
    }
}
