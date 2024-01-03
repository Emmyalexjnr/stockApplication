/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stockcontrolapplication;

import java.time.LocalDateTime;

/**
 *
 * @author chukwuemeka
 */
public class Transactions {
    private String productCode;
    private int quantitySold;
    private double unitPrice;
    private LocalDateTime dateTime;

    public void Transactions(String code, int quantitySold, double unitPrice, LocalDateTime dateTime){
        this.productCode = code;
        this.quantitySold = quantitySold;
        this.unitPrice = unitPrice;
        this.dateTime = dateTime;
    }

    public String getProductCode() {
        return productCode;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
