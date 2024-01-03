/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stockcontrolapplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chukwuemeka
 */
public class ASCStockItem {

    private static final String DELIMITER = ",";
    private static final int csvColumnLength = 2;
    private final String productCode;
    private String productTitle;
    private String description;
    private int unitPricePounds;
    private int unitPricePence;
    private int quantityInStock;

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

        this.productCode = productCode;
        this.productTitle = productTitle;
        this.description = description;
        this.unitPricePence = unitPricePence;
        this.unitPricePounds = unitPricePounds;
        this.quantityInStock = quantityInStock;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public String getDescription() {
        return description;
    }

    public int getUnitPricePounds() {
        return unitPricePounds;
    }

    public int getUnitPricePence() {
        return unitPricePence;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

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
                            String title = columns[0];
                            String moduleCode = columns[1];
//                            Module module = new ASCStockItem(title, moduleCode);
//                            stocks.add(module);
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

}