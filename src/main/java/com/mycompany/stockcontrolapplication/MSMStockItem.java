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
public class MSMStockItem {
    private static final String DELIMITER = ",";
    private static final int csvColumnLength = 5;

    private final int departmentId;
    private final String code;
    private final String nameAndDescription;
    private final int unitPrice;
    private int quantityInStock;
    public MSMStockItem(int departmentId, String code, String nameAndDescription, int unitPrice, int quantityInStock) {
        this.departmentId = departmentId;
        this.code = code;
        this.nameAndDescription = nameAndDescription;
        this.unitPrice = unitPrice;
        this.quantityInStock = quantityInStock;
    }

    /**
     * Returns the department ID used by Mead's Modernity
     * @return the department id.
     * @since 1.0
     */
    public int getDepartmentId() {
        return departmentId;
    }

    /**
     * Returns the code of this stock item.
     * @return a string with the 6-digit code.
     * @since 1.0
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the name of this stock item.
     * @return a string with the name.
     * @since 1.0
     */
    public String getName() {
        return nameAndDescription.substring(0, 59).replaceAll("\u00a0", "").stripTrailing();
    }

    /**
     * Return the description of this stock item.
     * @return a string with the description.
     * @since 1.0
     */
    public String getDescription() {
        return nameAndDescription.substring(60, nameAndDescription.length());
    }

    /**
     * Returns the stock item's unit price in pence
     * @return an integer that represents the unit price.
     * @since 1.0
     */
    public int getUnitPrice() {
        return unitPrice;
    }

    /**
     * Return the quantity of this stock item currently available in stock.
     * @return an integer that represents to stock quantity available.
     * @since 1.0
     */
    public int getQuantityInStock() {
        return quantityInStock;
    }

    public String getHumanFriendlyUnitPrice() {

        final int pounds = getUnitPrice() / 100;
        final int pence = getUnitPrice() % 100;
        return String.format("%d.%02d", pounds, pence);
    }

    public void setQuanity(int newQuantity) {
        if(newQuantity >= 0) {
            quantityInStock = newQuantity;
        }
    }

    @Override
    public String toString() {
        return String.format("%d-%s - %s - %s - UNIT PRICE: £%s - QTY: %d",
                getDepartmentId(),
                getCode(),
                getName(),
                getDescription(),
                getHumanFriendlyUnitPrice(),
                getQuantityInStock());
    }

    public static List<MSMStockItem> loadStockCSV() {
        final String INPUT_FILE_PATH = "MengdasSportyMart.csv";
        File inputFile = new File(INPUT_FILE_PATH);

        ArrayList<MSMStockItem> stocks = new ArrayList<>();

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
                            MSMStockItem stock = getMSMStockItem(columns);
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

    private static MSMStockItem getMSMStockItem(String[] columns) {
        int departmentId = Integer.parseInt(columns[0]);
        String code = columns[1];
        String nameAndDescription = columns[2];
        int unitPrice = Integer.parseInt(columns[3]);
        int quantityInStock = Integer.parseInt(columns[4]);
        return new MSMStockItem(departmentId, code, nameAndDescription, unitPrice, quantityInStock);
    }
}
