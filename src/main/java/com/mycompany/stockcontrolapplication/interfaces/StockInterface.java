/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.stockcontrolapplication.interfaces;

/**
 *
 * @author chukwuemeka
 */
public interface StockInterface {
    void addObservers(ObserverInterface o);
    void removeObservers(ObserverInterface o);

    void notifyObservers();
}
