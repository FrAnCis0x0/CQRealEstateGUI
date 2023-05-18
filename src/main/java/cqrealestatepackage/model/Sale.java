/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqrealestatepackage.model;

/**
 * @author Francis Renzaho
 * Student ID: 12170110
 */
public class Sale {
    private static int tempID;
    private int saleID = 1101;
    private String date;
    private double soldPrice;
    private Land property;
    private String sellerName;
    private String buyerName;

    public Sale(Land property, String date, double soldPrice,  String sellerName, String buyerName) {
        this.saleID += tempID;
        this.date = date;
        this.soldPrice = soldPrice;
        this.property = property;
        this.sellerName = sellerName;
        this.buyerName = buyerName;
        tempID += 1;
        markPropertyAsSold();
    }
    
    private void markPropertyAsSold(){
        this.property.setSold();
    }

    public int getSaleID() {
        return saleID;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(double soldPrice) {
        this.soldPrice = soldPrice;
    }

    public Land getProperty() {
        return property;
    }

    public void setProperty(Land property) {
        this.property = property;
    }

   
    
    @Override
    public String toString() {
        return  "SaleID: " + saleID +", Seller Name: " + sellerName + ", Buyer Name: " + buyerName+ ", Date: " + date + ", Sold Price: " + soldPrice + ", Property: " + property;
    }
    
    
    
    
}
