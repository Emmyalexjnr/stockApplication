/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stockcontrolapplication;

import com.mycompany.stockcontrolapplication.interfaces.ObserverInterface;

import javax.swing.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

/**
 *
 * @author chukwuemeka
 */
public class ASCStockItem extends Stock implements ObserverInterface {

    private static final String DELIMITER = ",";
    private static final int csvColumnLength = 6;

    /**
     * constructor for ASCStock class
     * @param productCode
     * @param productTitle
     * @param description
     * @param unitPricePounds
     * @param unitPricePence
     * @param quantityInStock
     */
    public ASCStockItem(String productCode, String productTitle, String description, int unitPricePounds, int unitPricePence, int quantityInStock){
        super(productCode, productTitle, description, unitPricePounds, unitPricePence, quantityInStock);

    }

    public String calculatePrice(int newQuantity) {
        int poundsPrice = newQuantity * unitPricePounds;
        int pencePrice = newQuantity * unitPricePence;
        int penceToPounds = (int) Math.floor((double) pencePrice / 100);
        int remainderPence = pencePrice % 100;
        return String.format("%d.%02d", poundsPrice + penceToPounds, remainderPence);
    }

    /**
     * Load a list of Ashers Sports Collectives from a CSV into a list
     * @return the list of sports collectives
     */
    public static List<ASCStockItem> loadStockCSV() {
        final String INPUT_FILE_PATH = "AshersSportsCollective.csv";
        File inputFile = new File(INPUT_FILE_PATH);

        ArrayList<ASCStockItem> stocks = new ArrayList<>();

        if (inputFile.exists() && inputFile.isFile()) {
            try {
                //load file into scanner to the file object
                Scanner fileScanner = new Scanner(inputFile);
                while (fileScanner.hasNextLine()) {
                    // read each line of the csv and trim out white spaces
                    String line = fileScanner.nextLine().trim();
                    // check whether the line is empty
                    if (!line.isEmpty()) {
                        final String[] columns = line.split(DELIMITER);
                        if (columns.length == csvColumnLength) {
                            ASCStockItem stock = getAscStockItem(columns);
                            stocks.add(stock);
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                Logger.getLogger(Module.class.getName()).log(Level.SEVERE, null, e);
                System.out.println("\n\n!!! Unable to open file !!!!!\n" + e.getMessage() + "\n");
                System.exit(0);
            }

        } else {
            System.out.println("\n!!! Error: '" + inputFile.getName() + "' does not exist as a CSV file. !!!!\n");
            System.exit(0);
        }

        return stocks;
    }

    public static void saveASCContentToFile(List<ASCStockItem> stocks) throws IOException, FileNotFoundException {
        final String OUTPUT_FILE_PATH = "asc_item.csv";
        //create path object
        Path path = Paths.get(OUTPUT_FILE_PATH);

        Files.deleteIfExists(path);

        BufferedOutputStream buffer = new BufferedOutputStream(
                Files.newOutputStream(path, CREATE, WRITE)
        );

        String stockReport = "";

//        moduleReport += "Module Code " + DELIMITER;
//        moduleReport += "Module Title " + DELIMITER;
//        moduleReport += "Highest score " + DELIMITER;
//        moduleReport += "Lowest score " + DELIMITER;
//        moduleReport += "Average score " + DELIMITER;
//        moduleReport += "Total passed " + DELIMITER;
//        moduleReport += "Total failed " + "\r\n";
        for (int i = 0; i < stocks.size(); i++) {
            ASCStockItem stock = stocks.get(i);
            stockReport += stock.getProductCode() + DELIMITER;
            stockReport += stock.getProductTitle() + DELIMITER;
            stockReport += stock.getDescription() + DELIMITER;
            stockReport += stock.getUnitPricePounds() + DELIMITER;
            stockReport += stock.getUnitPricePence() + DELIMITER;
            stockReport += stock.getQuantityInStock() + "\r\n";
        }// end of for loop

        byte data[] = stockReport.getBytes();

        buffer.write(data, 0, data.length);
        // close buffer so stream writes to file
        buffer.close();

        //confirm data written
        System.out.println("\n\nASCStocks updated to file at: " + path.toAbsolutePath().toString());
    } // end of saveModuleStaticsToFile method


    /**
     * use the columns in CSV file to generate a stock row
     * @param columns
     * @return
     */
    private static ASCStockItem getAscStockItem(String[] columns) {
        String code = columns[0];
        String title = columns[1];
        String description = columns[2];
        int unitPricePounds = Integer.parseInt(columns[3]);
        int unitPricePence = Integer.parseInt(columns[4]);
        int quantity = Integer.parseInt(columns[5]);
        return new ASCStockItem(code, title, description, unitPricePounds, unitPricePence, quantity);
    }

    @Override
    public void update() {
        if(quantityInStock < 5) {
            String message = productTitle + " Quantity left: " + quantityInStock;
            JOptionPane.showMessageDialog(null, message,
                    "Low stock alert ", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
