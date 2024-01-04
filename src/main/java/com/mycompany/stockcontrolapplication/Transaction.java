/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stockcontrolapplication;

import com.mycompany.stockcontrolapplication.interfaces.ObserverInterface;
import com.mycompany.stockcontrolapplication.interfaces.StockInterface;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author chukwuemeka
 */
public class Transaction implements StockInterface {
    private String productCode;
    private int quantitySold;
    private String unitPrice;
    private LocalDateTime dateTime;

    ArrayList<ObserverInterface> observers;

    public Transaction(String code, int quantitySold, String unitPrice, LocalDateTime dateTime){
        this.productCode = code;
        this.quantitySold = quantitySold;
        this.unitPrice = unitPrice;
        this.dateTime = dateTime;
        observers = new ArrayList<ObserverInterface>();
    }

    public String getProductCode() {
        return productCode;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }


    @Override
    public String toString() {
        return String.format("%s - UNIT PRICE: £%s - QTY: %d - Time: %s",
                getProductCode(),
                getUnitPrice(),
                getQuantitySold(),
                getDateTime());
    }

    @Override
    public void addObservers(ObserverInterface o) {
        observers.add(o);
    }

    @Override
    public void removeObservers(ObserverInterface o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update();
        }
    }
}
