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
public class Strategy2 {
    
    private static boolean isLong = false;
    private static boolean isInTrade = false;
    private static int threshold = 19;
    private static boolean thresholdMet = false;
    private static int tradeQuantity = 3;
    private static Double high = 0.0;
    private static Double low = 0.0;
    private static Trade trade;
    private static Double filledPrice;
    private static int tradeCount = 0;
    private static int count = 0;

    public Strategy2(Trade trade) {
        this.trade = trade;
    }
    
    public void fiveSecData(Double high, Double low, Date date){
//        if (thresholdMet){
//            this.high = high;
//            this.low = low;
//            thresholdMet = false;
//        }
    }
    
    public void addPrice(Double price){
//        if (high != 0.0){
//            if (isInTrade){
//                if (!isLong){
//                    if (price > high){
//                        trade.sentiment("BUY", tradeQuantity * 2);
//                        isLong = true;
//                        System.out.println("BUY " + price);
//                    }
//                }else{
//                    if (price < low){
//                        trade.sentiment("SELL", tradeQuantity * 2);
//                        isLong = false;
//                        System.out.println("SELL " + price);
//                       
//                    }
//                }
//            }else{
//                if (price > high){
//                    trade.sentiment("BUY", tradeQuantity);
//                    isInTrade = true;
//                    isLong = true;
//                    System.out.println("BUY " + price);
//                    
//                }else if (price < low){
//                    trade.sentiment("SELL", tradeQuantity);
//                    isInTrade = true;
//                    isLong = false;
//                    System.out.println("SELL " + price);
//                  
//                }
//            }
//        }
    }
    
    public void addSize(int size){
        if (size > threshold){
//            thresholdMet = true;
            if (tradeCount < threshold){
                
            }
        }

    }
    
    public void addTrade(int size){
        tradeCount += size;
    }
    
    public void setFilledPrice(double price) {
        filledPrice = price;
    }
    
}
