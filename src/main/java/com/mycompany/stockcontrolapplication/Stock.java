/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stockcontrolapplication;

import com.mycompany.stockcontrolapplication.interfaces.ObserverInterface;
import com.mycompany.stockcontrolapplication.interfaces.StockInterface;

import java.util.ArrayList;

/**
 *
 * @author chukwuemeka
 */
public class Stock {

    protected final String productCode;
    protected String productTitle;
    protected String description;
    protected int unitPricePounds;
    protected int unitPricePence;
    protected int quantityInStock;



    public Stock(String productCode, String productTitle, String description, int unitPricePounds, int unitPricePence, int quantityInStock) {
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

    public void setQuanity(int newQuantity) {
        if(newQuantity >= 0) {
            quantityInStock = newQuantity;
        }
    }

}
