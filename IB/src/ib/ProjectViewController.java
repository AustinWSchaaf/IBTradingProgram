/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ib;

import com.ib.client.Bar;
import com.ib.client.CommissionReport;
import java.awt.Color;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.ib.client.EClientSocket;
import com.ib.client.EReader;
import com.ib.client.EReaderSignal;
import com.ib.client.ExecutionFilter;
import com.ib.client.Order;
import com.ib.client.Types.FADataType;
import com.ib.client.Contract;
import com.ib.client.ContractDescription;
import com.ib.client.ContractDetails;
import com.ib.client.DeltaNeutralContract;
import com.ib.client.DepthMktDataDescription;
import com.ib.client.EJavaSignal;
import com.ib.client.EWrapper;
import com.ib.client.EWrapperMsgGenerator;
import com.ib.client.Execution;
import com.ib.client.FamilyCode;
import com.ib.client.HistogramEntry;
import com.ib.client.HistoricalTick;
import com.ib.client.HistoricalTickBidAsk;
import com.ib.client.HistoricalTickLast;
import com.ib.client.NewsProvider;
import com.ib.client.OrderState;
import com.ib.client.PriceIncrement;
import com.ib.client.SoftDollarTier;
import com.ib.client.TagValue;
import com.ib.client.TickAttr;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.text.DateFormatter;
/**
 *
 * @author
 * 
 * austinschaaf
 */

public class ProjectViewController extends javax.swing.JFrame implements EWrapperDelegate, Trade{

    private static EClientSocket client;
    private static int id = 0;
    
    private static EReaderSignal sig;
    protected int currentOrderId = -1;
    private static EWrapperImpl wrapper;
    
    private static Double bidPrice;
    private static Double askPrice;
    private static double lastBidPrice = 0.0;
    private static double lastAskPrice = 0.0;
    private static int bidSize = 0;
    private static int askSize = 0;
    private Double tradedPrice;
    private int tradedSize = 0;
    private static MarketMaking market;
    private static Strategy strat;
    
    private static Contract stock;
    private static Contract future;
    
    static boolean isInTrade = false;
    
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    /**
     * Creates new form ProjectViewController
     */
    public ProjectViewController() {
        initComponents();
        wrapper = new EWrapperImpl(this);
        market = new MarketMaking(this);
        strat = new Strategy(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ibLabel = new javax.swing.JLabel();
        connect = new javax.swing.JButton();
        connectLabel = new javax.swing.JLabel();
        pnlLabel = new javax.swing.JLabel();
        profitLossLabel = new javax.swing.JLabel();
        inputField = new javax.swing.JTextField();
        inputLabel = new javax.swing.JLabel();
        getTickerData = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        buyButton = new javax.swing.JButton();
        sellButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ibLabel.setForeground(new java.awt.Color(51, 51, 255));
        ibLabel.setText("Interactive Brokers");

        connect.setText("Connect");
        connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectActionPerformed(evt);
            }
        });

        pnlLabel.setText("PnL:");

        profitLossLabel.setText("0");

        inputLabel.setText("Input Ticker Symbol:");

        getTickerData.setText("Search");
        getTickerData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getTickerDataActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        buyButton.setBackground(new java.awt.Color(51, 255, 0));
        buyButton.setText("BUY");
        buyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyButtonActionPerformed(evt);
            }
        });

        sellButton.setBackground(new java.awt.Color(255, 51, 51));
        sellButton.setText("SELL");
        sellButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ibLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 441, Short.MAX_VALUE)
                        .addComponent(pnlLabel)
                        .addGap(18, 18, 18)
                        .addComponent(profitLossLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(inputField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(connect)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(connectLabel)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(getTickerData)))
                        .addGap(0, 532, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buyButton)
                    .addComponent(sellButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ibLabel)
                    .addComponent(pnlLabel)
                    .addComponent(profitLossLabel))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(connect)
                    .addComponent(connectLabel))
                .addGap(36, 36, 36)
                .addComponent(inputLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(getTickerData))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buyButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sellButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectActionPerformed
        // TODO add your handling code here:
        try{
            connect();
        }catch (Exception e){
            System.out.println("Error while trying to connect....");
        }
        
    }//GEN-LAST:event_connectActionPerformed

    private void getTickerDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getTickerDataActionPerformed
//        String input = inputField.getText();
//        String[] tokens = input.split(" ");
//        DefaultListModel model = new DefaultListModel();
//        for (int i = 0; i < tokens.length; i++){
////            symbolList.add(tokens[i].toUpperCase(), i);
//            model.addElement(tokens[i].toUpperCase());
//        }
//        symbolList.setModel(model);
//        inputField.setText("");
//        symbolList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);        
////        symbolList.addListSelectionListener(valueChanged);
//        Stock s = new Stock("AAPL", 1, this);
        
    }//GEN-LAST:event_getTickerDataActionPerformed

    private void buyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyButtonActionPerformed
        // TODO add your handling code here:
        System.out.println("BUY");
    }//GEN-LAST:event_buyButtonActionPerformed

    private void sellButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellButtonActionPerformed
        // TODO add your handling code here:
        System.out.println("SELL");
    }//GEN-LAST:event_sellButtonActionPerformed
     
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProjectViewController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProjectViewController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProjectViewController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProjectViewController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProjectViewController().setVisible(true);
            }
        });
        
        
    }

    private static void connect() throws InterruptedException{
//        EWrapperImpl wrapper = new EWrapperImpl();
        writeToFile("newfile.txt");
        final EClientSocket m_client = wrapper.getClient();
        client = m_client;
        final EReaderSignal m_signal = wrapper.getSignal();
        //! [connect]
        m_client.eConnect("127.0.0.1", 7496, 0);
        
		//! [connect]
        //! [ereader]
        final EReader reader = new EReader(m_client, m_signal);

        reader.start();
        //An additional thread is created in this program design to empty the messaging queue
        new Thread(() -> {
            while (m_client.isConnected()) {
                m_signal.waitForSignal();
                try {
                    reader.processMsgs();
                } catch (Exception e) {
//                    System.out.println("Exception: " + "Can not Connect");
                }
            }
        }).start();
	
        Thread.sleep(1000);
        
        client.reqIds(-1);
//        client.reqCurrentTime();
        stock = createStockContract("NVDA");
        
//        stock = c;
//        future = createFutureContract("GC");
        future = Future.getNQ();
////        getDataForContract(c);
        getRealtimeData(stock);
        id = wrapper.getCurrentOrderId();
        

        Vector<TagValue> realTimeBarsOptions = new Vector<TagValue>();
        // Make a call to start off data retrieval
        client.reqRealTimeBars(0, stock,
                5, // Bar Size 5 seconds
                "TRADES", // whatToShow
                false, // useRTH
                realTimeBarsOptions);
        String date = "20180609 10:00:00";
        
//        client.reqHistoricalData(4002, future, date, "10 D", "1 min", "TRADES", 1, 1, false, null);
//       getData(future);
    }
    
   static String[] april = {"02","03","04","05","06","09","10","11","12","13","16","17","18","19","20","23","24","25","26","27","30"};
   static String[] may = {"01","02","03","04","07","08","09","10","11","14","15","16","17","18","21","22","23","24","25","29","30","31"};
   static String[] june = {"04","05","06", "07"};
   static String[] days = {"08", "11", "12"};
   public static void getData(Contract con) throws InterruptedException{
        int n = 0;
        String yearMonth = "201806";
        int day = 21;
        String time = "10:00:00";
        int hour = 10;
        int min = 0;
        
        for (int i = 0; i < days.length; i++){
            String newDate = yearMonth + days[i] + " " + time;
            client.reqHistoricalData(i + 1, con, newDate, "5400 S", "5 secs", "TRADES", 1, 1, false, null);
            TimeUnit.SECONDS.sleep(5);
        }
           
    }
    
   
    public static String getDate(){
        Date date = new Date();
        return date.toString();
    }
    
    private static void updateId(){
        id += 1;
    }
    
    public static Contract createStockContract(String symbol){
        Contract contract = new Contract();
        contract.symbol(symbol);
        contract.secType("STK");
        contract.currency("USD");
        contract.exchange("SMART");
        return contract;
    }
    
    public static Contract createFutureContract(String symbol){
        Contract contract = new Contract();
        contract.symbol(symbol.toUpperCase());
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("NYMEX");
        contract.lastTradeDateOrContractMonth("20171227");
        return contract;
    }
  
    static void getDataForContract(Contract contract){
        client.reqHistoricalTicks(id, contract, "20171017 10:30:00", null, 10, "BID_ASK", 1, true, null);
        updateId();
    }
    
    static void getRealtimeData(Contract contract){
        client.reqMktData(1002, contract, "", false, false, null);
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buyButton;
    private javax.swing.JButton connect;
    private javax.swing.JLabel connectLabel;
    private javax.swing.JButton getTickerData;
    private javax.swing.JLabel ibLabel;
    private javax.swing.JTextField inputField;
    private javax.swing.JLabel inputLabel;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel pnlLabel;
    private javax.swing.JLabel profitLossLabel;
    private javax.swing.JButton sellButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void priceChange(int tickerId, int field, double price) {
        //To change body of generated methods, choose Tools | Templates.
        if (field == 1){
            bidPrice = price;
            strat.check(bidPrice);
        }else if (field == 2){
            askPrice = price;
            strat.check(askPrice);
        }else if (field == 4){
            tradedPrice = price;
        }
    }

    @Override
    public void sizeChange(int tickerId, int field, int size, Date date) {
//        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("mm");
//        String d = f.format(date);
        int minute = Integer.parseInt(f.format(date));
        
        if (field == 0 || field == 3){
            strat.addSize(size);
        }
        client.reqCurrentTime();
        if (field == 0){
            market.add(bidPrice, size, "BID", date);
            bidSize = size;
        }else if (field == 3){
            market.add(askPrice, size, "ASK", date);
            askSize = size;
        }else if (field == 5){
            tradedSize = size;
            market.addSale(tradedPrice, tradedSize);
            
        }
    }

    @Override
    public void sentiment(String outlook, int quantity) {
        if (outlook.equals("BUY")){
            Order order = createMarketOrder(future, "BUY", quantity);
            placeOrder(order);
            System.out.println(ANSI_GREEN + "BUY" + ANSI_RESET);
        }else{
            Order order = createMarketOrder(future, "SELL", quantity);
            placeOrder(order);
            System.out.println(ANSI_RED + "SELL" + ANSI_RESET);
        }
            
    }
    
    private static Order createMarketOrder(Contract c, String action, int quantity){
        client.reqIds(-1);
        
        Order order = new Order();
        order.action(action);
        order.orderType("MKT");
        order.totalQuantity(quantity);
        return order;
    }
    
    private static void placeOrder(Order order){
//        client.placeOrder(id, future, order);
//        isInTrade = true;
//        updateId();
    }
    
    private static void addText(Double p){
        Color color;
        if (p == bidPrice){
            color = Color.GREEN;
        }else if (p == askPrice){
            color = Color.RED;
        }else{
            color = Color.WHITE;
        }
        
    }
    
    public static void writeToFile(String name){
        Charset utf8 = StandardCharsets.UTF_8;
        List<String> lines = Arrays.asList("1st line", "2nd line");
        String t = "Some Text";
        byte[] data = {1, 2, 3, 4, 5};

        try {
            Files.write(Paths.get(name), t.getBytes());
//            Files.write(Paths.get("file2.bin"), data,
//                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
//            Files.write(Paths.get("file3.txt"), "content".getBytes());
//            Files.write(Paths.get("file4.txt"), "content".getBytes(utf8));
//            Files.write(Paths.get("file5.txt"), lines, utf8);
//            Files.write(Paths.get("file6.txt"), lines, utf8,
//                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void filledPrice(double price) {
//        market.setFillPrice(price);
        strat.setFilledPrice(price);
    }

    @Override
    public void fiveSecBar(double high, double low, double close, Date date) {
        strat.fiveSecData(high, low, date);
        strat.addPrice(close);
//        System.out.println(market.shouldTrade);
//        if (market.shouldTrade){
//            strat.fiveSecData(high, low, date);
//            strat.addPrice(100);
//            market.shouldTrade = false;
//        }
        
    }
    @Override
    public void tradePrice(Double price, int size){
//        market.addTrade(size);
    }
}