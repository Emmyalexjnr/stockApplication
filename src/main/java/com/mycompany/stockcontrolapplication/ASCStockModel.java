/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stockcontrolapplication;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 *
 * @author chukwuemeka
 */
public class ASCStockModel extends AbstractTableModel {

    private static final String[] COLUMN_NAMES = {"Code", "Title", "Description", "Unit Price(Pounds)", "Unit Price(Pence)", "Quantity"};
    private final List<ASCStockItem> stocks;

    public ASCStockModel(List<ASCStockItem> stocks) {
        this.stocks = stocks;
    }

    @Override
    public int getRowCount() {
        return stocks.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ASCStockItem stock = stocks.get(rowIndex);

        switch(columnIndex){
            case 0:
                return stock.getProductCode();
            case 1:
                return stock.getProductTitle();
            case 2:
                return stock.getDescription();
            case 3:
                return stock.getUnitPricePounds();
            case 4:
                return stock.getUnitPricePence();
            case 5:
                return stock.getQuantityInStock();
        }
        return null;
    }
}
