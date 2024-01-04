/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stockcontrolapplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author chukwuemeka
 */
public class MSMStockToStockAdaptor extends ASCStockItem {
    private static final String DELIMITER = ",";
    private static final int csvColumnLength = 5;

    private final MSMStockItem MSMStock;

    public MSMStockToStockAdaptor(int departmentId, String code, String nameAndDescription, int unitPrice, int quantityInStock) {


        // Instantiate the base class object
//        super(departmentId == 1 ? "RUN" : departmentId == 2 ? "SWM" : "CYC" +
//                        "-" + code + "-MSM",
//                nameAndDescription.substring(0, 59).replaceAll("\u00a0", "").stripTrailing(),
//                nameAndDescription.substring(60, nameAndDescription.length()),
//                unitPrice / 100,
//                unitPrice % 100,
//                quantityInStock);
        super("",
                "",
                "",
                100,
                100,
                quantityInStock);


        MSMStock = new MSMStockItem(departmentId, code, nameAndDescription, unitPrice, quantityInStock);

    }

    public static ArrayList<ASCStockItem> loadMSMStockCSV() {
        final String INPUT_FILE_PATH = "MengdasSportyMart.csv";
        File inputFile = new File(INPUT_FILE_PATH);

        ArrayList<ASCStockItem> stocks = new ArrayList<>();

        if (inputFile.exists() && inputFile.isFile()) {
            try {
                //load file into scanner to the file object
                Scanner fileScanner = new Scanner(inputFile);
                while (fileScanner.hasNextLine()) {
                    // read each line of the csv and trim out white spaces
                    String line = fileScanner.nextLine().trim();
                    System.out.println(line);
                    // check whether the line is empty
                    if (!line.isEmpty()) {
                        final String[] columns = line.split(DELIMITER);
                        
                        if (columns.length == csvColumnLength) {
                            
                            MSMStockToStockAdaptor stock = getMSMStockItem(columns);
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

    @Override
    public String getProductCode() {
        String code = getEnumValue(MSMStock.getDepartmentId()) + "-" +MSMStock.getCode()+ "-MSM";
        return code;
    }

    @Override
    public String getProductTitle() {
        return MSMStock.getName();
    }

    @Override
    public String getDescription() {
        return MSMStock.getDescription();
    }

    @Override
    public int getUnitPricePounds() {
        return MSMStock.getUnitPrice() / 100;
    }

    @Override
    public int getUnitPricePence() {
        return MSMStock.getUnitPrice() % 100;
    }

    private static MSMStockToStockAdaptor getMSMStockItem(String[] columns) {

        int departmentId = Integer.parseInt(matchNumber(columns[0]));

        String code = columns[1];
        String nameAndDescription = columns[2];
        int unitPrice = Integer.parseInt(columns[3]);
        int quantityInStock = Integer.parseInt(columns[4]);
        return new MSMStockToStockAdaptor(departmentId, code, nameAndDescription, unitPrice, quantityInStock);
    }

    private static String matchNumber(String value) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(value);
        if (m.find()) {
            return m.group();
        }
        return "";
    }

    private DepartmentType getEnumValue(int value) {
        return switch (value) {
            case 1 -> DepartmentType.RUN;
            case 2 -> DepartmentType.SWM;
            case 3 -> DepartmentType.CYC;
            default -> DepartmentType.RUN;
        };
    }

}
