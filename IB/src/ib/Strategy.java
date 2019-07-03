/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ib;

import java.util.Date;

/**
 *
 * @author austinschaaf
 */
public class Strategy {

    private static double high = 0.0;
    private static double low = 0.0;
    private static double filledPrice = 0.0;

    private static double bidPrice = 0.0;
    private static double askPrice = 0.0;
    private static int bidSize = 0;
    private static int askSize = 0;

    private static int maxSize = 0;
    private boolean size = false;

    private static boolean isInTrade = false;
    private static boolean isLong = false;
    private static boolean visited = false;

    private static int quantity = 39;
    private static double margin = 3.0;
    
    private static int count = 0;
    private static boolean shouldTrade = false;
    private static int threshold = 29;
    private static String firstSent = "";
    private static String secondSent = "";
    
    private static Trade trade;

    public Strategy(Trade trade) {
        this.trade = trade;
    }

    public void fiveSecData(double high, double low, Date date) {
        if (size) {
            this.high = high;
            this.low = low;
            this.size = false;
//            System.out.println("DATE: " + date + " HIGH: " + high + " LOW: " + low);
        }

    }
    
    private void clearSignal(){
        high = 0.0;
        low = 0.0;
    }

    public void addPrice(double price) {
//        System.out.println("CLOSE: " + price + " HIGH: " + high + " LOW: " + low);
        if (high != 0.0) {
//            check(price);
            if (price > high) {
                if (isInTrade) {
                    if (!isLong) {
                        //Enter LONG
                        trade.sentiment("BUY", 6);
                        System.out.println("BUY: " + price);
                        isLong = true;
//                        clearSignal();
                    }
                } else {
                    //Enter LONG
                    trade.sentiment("BUY", 3);
                    System.out.println("BUY: " + price);
                    isInTrade = true;
                    isLong = true;
//                    clearSignal();
                }
            } else if (price < low) {
                if (isInTrade) {
                    if (isLong) {
                        //Enter SHORT
                        trade.sentiment("SELL", 6);
                        System.out.println("SELL: " + price);
                        isLong = false;
//                        clearSignal();
                    }
                } else {
                    //Enter SHORT
                    trade.sentiment("SELL", 3);
                    System.out.println("SELL: " + price);
                    isInTrade = true;
                    isLong = false;
//                    clearSignal();
                }
            }
        }
    }
    
    public static void check(double price){
//        if (isInTrade){
//            if (isLong){
//                if (price - filledPrice >= margin){
//                    isInTrade = false;
//                    trade.sentiment("SELL", 3);
//                    System.out.println("SELL: " + price);
//                }
//            }else{
//                if (filledPrice - price >= margin){
//                    isInTrade = false;
//                    trade.sentiment("BUY", 3);
//                    System.out.println("BUY: " + price);
//                }
//            }
//        }
    }

    public void addSize(int size) {
        if (size > quantity) {
            this.size = true;
//            System.out.println("SIZE: " + size);
        }
//		System.out.println("SIZE: " + size);
    }

    public void setFilledPrice(double price) {
        filledPrice = price;
    }
    
    public void addTrade(int size){
        count += size;
        
        if (count > threshold){
            shouldTrade = true;
        }
    }

}
