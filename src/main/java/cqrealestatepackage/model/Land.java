/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqrealestatepackage.model;

/**
 * @author Francis Renzaho
 * Student ID: 12170110
 */
public class Land {
    private int lotNumber;
    private String address;
    private double landArea;
    private boolean sold = false;
    private String propertyType;



    public Land(int lotNumber, double landArea, String address){
        this.lotNumber = lotNumber;
        this.landArea = landArea;
        this.address = address;
        this.propertyType = getClass().getSimpleName();
    
    }
    public boolean isSold() {
        return sold;
    }

    public void setSold() {
        this.sold = true;
    }
    
    public String getSoldString(){
        return sold?"YES":"NO";
    }
    
    public int getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(int lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLandArea() {
        return landArea;
    }
    
    public String getPropertyType() {
        return propertyType;
    }

    public void setLandArea(double landArea) {
        this.landArea = landArea;
    }

    @Override
    public String toString(){
        return "Property Type: "+propertyType+", Lot Number: "+lotNumber+", Land Area: "+ landArea+", Address: "+address+ ", Sold: "+getSoldString();
    }
    
}
