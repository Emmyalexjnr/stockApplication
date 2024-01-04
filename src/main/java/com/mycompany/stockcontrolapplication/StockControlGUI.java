/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.stockcontrolapplication;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author chukwuemeka
 */
public class StockControlGUI extends JFrame {

    private List<ASCStockItem> stocks;
//    private List
    private List<Transaction> transactions;
    /**
     * Creates new form StockControlGUI
     */
    public StockControlGUI() {

        initComponents();
        stocks = ASCStockItem.loadStockCSV();
        stocks.addAll(MSMStockToStockAdaptor.loadMSMStockCSV());
        transactions = new ArrayList<Transaction>();
        StockTable.setModel(new ASCStockModel(stocks));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        StockTable = new javax.swing.JTable();
        BuyButton = new javax.swing.JButton();
        SellButton = new javax.swing.JButton();
        QuitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Stock Control with low stock reporting");

        StockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(StockTable);

        BuyButton.setText("Buy");
        BuyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuyButtonActionPerformed(evt);
            }
        });

        SellButton.setText("Sell");
        SellButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SellButtonActionPerformed(evt);
            }
        });

        QuitButton.setBackground(new java.awt.Color(255, 102, 102));
        QuitButton.setForeground(new java.awt.Color(255, 255, 255));
        QuitButton.setText("Quit");
        QuitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BuyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SellButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(QuitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BuyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SellButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QuitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BuyButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_BuyButtonActionPerformed
        ArrayList<String> titles = getProductTitles();
        Object selectedProduct = JOptionPane.showInputDialog(
                null,
                "Please select the product you want to buy",
                "Buy product",
                JOptionPane.QUESTION_MESSAGE,
                null, titles.toArray(), titles.get(0)
        );
        ASCStockItem selectedStock = getSelectedItem(selectedProduct);
        assert selectedStock != null;
        String quantity = (String) JOptionPane.showInputDialog(
                null,
                "Please enter the quantity you are buying",
                "Buy quantity",
                JOptionPane.QUESTION_MESSAGE
        );
        selectedStock.setQuanity(Integer.parseInt(quantity) + selectedStock.getQuantityInStock());
        StockTable.setModel(new ASCStockModel(stocks));
        String message = "You bought " + quantity +  " of " +  selectedStock.getProductTitle();
        JOptionPane.showMessageDialog(null, message,
                "Bought " + selectedStock.getProductTitle(), JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_BuyButtonActionPerformed

    private void QuitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitButtonActionPerformed
        // TODO add your handling code here:
        JButton btn = (JButton) evt.getSource();
        quit();
        btn.setEnabled(false);
    }//GEN-LAST:event_QuitButtonActionPerformed

    private void SellButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SellButtonActionPerformed

        ArrayList<String> titles = getProductTitles();

        //Use options array to get input via pop-up dialog
        Object selectedProduct = JOptionPane.showInputDialog(
            null, 
            "Please select the product you want to sell",
            "Sell product", 
            JOptionPane.QUESTION_MESSAGE, 
            null, titles.toArray(), titles.get(0)
        );
        ASCStockItem selectedStock = getSelectedItem(selectedProduct);
        assert selectedStock != null;
        int quantity = returnQuantity(selectedStock);

        int newQuantity = selectedStock.getQuantityInStock() - quantity;
        selectedStock.setQuanity(newQuantity);
        String amountSold = selectedStock.calculatePrice(newQuantity);

        StockTable.setModel(new ASCStockModel(stocks));
        String message = "You sold " + newQuantity +  " " +  selectedStock.getProductTitle() + " for £" + amountSold;
        String code = selectedStock.getProductCode();
        LocalDateTime now = LocalDateTime.now();

        Transaction transaction = new Transaction(code, quantity, amountSold, now);
        transaction.addObservers(selectedStock);
        System.out.println(transaction.toString());
        transactions.add(transaction);
        JOptionPane.showMessageDialog(null, message,
                "Sold " + selectedStock.getProductTitle(), JOptionPane.INFORMATION_MESSAGE);
        transaction.notifyObservers();
//        transaction.removeObservers(selectedStock);


    }//GEN-LAST:event_SellButtonActionPerformed

    private int returnQuantity(ASCStockItem selectedStock) {
        String quantity;
        quantity = (String) JOptionPane.showInputDialog(
                null,
                "Please enter the quantity [1 to " + selectedStock.getQuantityInStock() + "]",
                "Sell quantity",
                JOptionPane.QUESTION_MESSAGE
        );
        boolean bol = true;
        while (bol) {
            if((Integer.parseInt(quantity) > selectedStock.getQuantityInStock()) || Integer.parseInt(quantity) < 1 ) {
                quantity = (String) JOptionPane.showInputDialog(
                        null,
                        "Please enter a valid quantity [1 to " + selectedStock.getQuantityInStock() + "]",
                        "Sell quantity",
                        JOptionPane.QUESTION_MESSAGE
                );
            } else {
                bol = false;
                return Integer.parseInt(quantity);
            }
        }
        return 0;
    }

    private ASCStockItem getSelectedItem(Object selectedProduct) {
        ASCStockItem selectedStock = null;
        for (ASCStockItem stock : stocks) {
            if(stock.getProductTitle().equals(selectedProduct)) {
                selectedStock = stock;
            }
        }
        return selectedStock;
    }

    /**
     * filter out the product titles from the stocks
     * @return
     */
    private ArrayList<String> getProductTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (ASCStockItem stock : stocks) {
            titles.add(stock.getProductTitle());
        }
        return titles;
    }


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
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockControlGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(StockControlGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(StockControlGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(StockControlGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StockControlGUI().setVisible(true);
            }
        });
    }
    
    public void quit() {

        try {
            ASCStockItem.saveASCContentToFile(stocks);
        } catch (FileNotFoundException e) {
            //warn user
            System.out.println("\n\n!!!!! Unable to open output file !!!!!\n" + e.getMessage() + "\n");
        } catch (IOException e) {
            //warn user
            System.out.print("\n\n!!!!! File write error !!!!!\n" + e.getMessage() + "\n");
        }
        //end message
        System.out.println("\n\n***** Thank You for using this application *****\n");

        System.exit(0);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BuyButton;
    private javax.swing.JButton QuitButton;
    private javax.swing.JButton SellButton;
    private javax.swing.JTable StockTable;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
