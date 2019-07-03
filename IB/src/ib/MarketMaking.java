/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ib;
import java.util.Date;
import java.util.LinkedList;
import java.util.ArrayList;
/**
 *
 * @author austinschaaf
 */
interface Trade{
    void sentiment(String outlook, int quantity);
}

//public enum Market{
//    BID, ASK
//

public class MarketMaking{
    
    enum Sentiment{
        DROPPED_BID,
        LIFTED_OFFER,
        HELD_BID,
        HELD_OFFER,
        BULLISH,
        BEARISH,
        VOID
    }
    
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m"; 
    public static final String BLUE_BACKGROUND = "\033[44m";
        
    private static double bidPrice = 0.0;
    private static double askPrice = 0.0;
    private static int bidSize = 0;
    private static int askSize = 0;
    private static final int largeSize = 4;//NQ largest size = 14
    
    private static int bidVolume = 0;
    private static int askVolume = 0;
    
    private static double bidHeld = 0.0;
    private static double bidDropped = 0.0;
    private static double askHeld = 0.0;
    private static double askLifted = 0.0;
    
    private static boolean isInTrade = false;
    private static boolean isLong = false;
    private static int quantity = 3;
    
    private static String sentiment = "";
    private static double value = 0.0;
    
    private static double filledPrice = 0.0;
    private static double tradeSize = 3;
    
    private static int count = 0;
    private static boolean shouldTrade = false;
    private static int threshold = 19;
    
//    private static LinkedList<String> sentiments = new LinkedList<String>();
    private static LinkedList<Sentiment> sentiments = new LinkedList<Sentiment>();
    private static Trade trade;
    private static ArrayList<Integer> list = new ArrayList();
    
    private static String lastSent = "";
        
    public MarketMaking(Trade trade){
        this.trade = trade;
    }
    
    public void add(double price, int size, String side, Date date){
        if (largeSize == 0){
            int s = 0; 
        }
        checkOrder(price);
        if (side.equals("BID")){
            if (price == bidPrice){
                bidSize = Math.max(bidSize, size);
            }else{                
                sentiment(side, price, date);
                bidPrice = price;
                bidSize = size;
                bidVolume = 0;
            }
        }else if (side.equals("ASK")){
            if (price == askPrice){
                askSize = Math.max(askSize, size);
            }else if (price != askPrice){
                sentiment(side, price, date);
                askPrice = price;
                askSize = size;
                askVolume = 0;
            }
        }
    }
    
    private static void sentiment(String side, double price, Date date){

//        Date date = new Date();
        String s = "NOTHING";
        String trade = "";
        if (side.equals("BID")){
            if (price > bidPrice && bidSize > largeSize){
                if (bidSize > askSize){
                    sentiments.addFirst(Sentiment.HELD_BID);
                    s = ANSI_GREEN + "HELD BID " + ANSI_RESET + price + " SIZE: " + bidSize;
                    trade = "BUY";
                    bidHeld = bidPrice;
                    lastSent = "HELD BID";
                    System.out.println(s + " " + date);
                    addToList(1);
                    count = 0;
                }
                
            }else if (price < bidPrice && bidSize > largeSize){
                if (bidSize > askSize){
                    sentiments.addFirst(Sentiment.DROPPED_BID);
                    s = ANSI_RED + "DROPPED BID " + ANSI_RESET + price + " SIZE: " + bidSize;
                    trade = "SELL";
                    sentiment = "D";
                    value = bidPrice;
                    bidDropped = bidPrice;
//                    trade(price, "SELL");
                    lastSent = "DROPPED BID";
                    System.out.println(s + " " + date);
                    addToList(0);
                    count = 0;
                }
            }
        } else{
            if (price > askPrice && askSize > largeSize){
                if (askSize > bidSize){
                    sentiments.addFirst(Sentiment.LIFTED_OFFER);
                    s = ANSI_GREEN + "LIFTED OFFER " + ANSI_RESET + price + " SIZE: " + askSize;
                    trade = "BUY";
                    sentiment = "L";
                    value = askPrice;
                    askLifted = askPrice;
//                    trade(price, "BUY");
                    lastSent = "LIFTED OFFER";
                    System.out.println(s + " " + date);
                    addToList(1);
                    count = 0;
                }
                
            }else if (price < askPrice && askSize > largeSize){
                if (askSize > bidSize){
                    sentiments.addFirst(Sentiment.HELD_BID);
                    s = ANSI_RED + "HELD OFFER " + ANSI_RESET + price + " SIZE: " + askSize;
                    trade = "SELL";  
                    askHeld = askPrice;
                    lastSent = "HELD OFFER";
                    System.out.println(s + " " + date);
                    addToList(0);
                    count = 0;
                }
            }
        }
        
//        if (!s.equals("NOTHING")){
//            System.out.println(s + " " + date.toString());
//        }
            
        
//        trade(price, trade);
//        trade(price);
    }
    
 
    public String getLastSent(){
        return lastSent;
    }
    public static void trade(double price, String type){
        
        
    }
    
    public void addTrade(int size){
        count += size;
        
        if (count > threshold){
            shouldTrade = true;
        }
    }
    
    private static void addToList(int sent){
        if (shouldTrade){
            int size = list.size();
            if (size < 2){
                list.add(sent);
                if (list.size() == 2){
                    if (list.get(0) == 0 && list.get(1) == 0){
                        //SELL
                        if (isInTrade){
                            if (isLong){
                                trade.sentiment("SELL", quantity * 2);
                                isLong = false;
                            }
                        }else{
                            trade.sentiment("SELL", quantity);
                            isInTrade = true;
                            isLong = false;
                        }
                    }else if (list.get(0) == 1 && list.get(1) == 1){
                        //BUY
                        if (isInTrade){
                            if (!isLong){
                                trade.sentiment("BUY", quantity * 2);
                                isLong = true;
                            }
                        }else{
                            trade.sentiment("BUY", quantity);
                            isInTrade = true;
                            isLong = true;
                        }
                    }
                    list.clear();
                    shouldTrade = false;
                }
            }
        }
    }
    
    public static void addSale(double price, int size){
    }
    
    public void setFillPrice(double price){
        filledPrice = price;
    }
    
    private void checkOrder( double currentPrice){
        
    }
}
