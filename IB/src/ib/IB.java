/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ib;

import com.ib.client.Contract;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author austinschaaf
 */
public class IB {

//    /**
//     * @param args the command line arguments
//     */
//    private static String[] symbols;
//    
//    public static void main(String[] args) {
//        JFrame f = new JFrame("Interactive Brokers API");
//        JButton b = new JButton("Enter");
//        JTextField inputField = new JTextField("");
//        JLabel inputLabel = new JLabel("Enter Symbol(s)");
//        inputLabel.setBounds(12, 25, 200, 30);
//        b.setBounds(225, 50, 95, 30);
//        b.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                parseSymbols(inputField.getText());
//            }
//        });
//        inputField.setBounds(10, 50, 200, 30);
//        f.add(inputField);
//        f.add(b);
//        f.add(inputLabel);
//        f.setSize(500, 1000);
//        f.setLayout(null);
//        f.setVisible(true);
//
////        try{
////            Connection connection = new Connection();
////            Contract contract = connection.createStockContract("AAPL");
////            connection.getDataForContract(contract);
////        }catch (Exception e){
////            System.out.println("Error while trying to connect....");
////        }
//    }
//    
//    private static void setTickerSymbol(){
//        for (int i = 0; i < symbols.length; i++){
//            System.out.println("Entered Ticker Symbol " + symbols[i] + ".");
//        }
//        
//    }
//    
//    private static void parseSymbols(String input){
//        String[] list = input.split(" ");
//        symbols = list;
//    }
}
