/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqrealestatepackage.model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.scene.chart.BarChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 *
 * @author renza
 */
public class DataHandler {
    public FileManager<Sale> sales;
    public FileManager<Buyer> buyers;
    public FileManager<Seller> sellers;
    public FileManager<Land> properties;
    
    private BarChartInfo barChartInfo;
    
    public DataHandler(){
        sales  = new FileManager<>("salesData");
        buyers = new FileManager<>("buyersData");
        sellers = new FileManager<>("sellersData");
        properties = new FileManager<>("propertyData");
        defaultData();
        
        
    }
    
        //collects data and adds new buyer object to buyers
    public void createNewBuyer(TextField tfName, TextField tfAddress, TextField tfPhoneNumber){
        buyers.addNewItem(new Buyer(tfName.getText(), tfAddress.getText(), tfPhoneNumber.getText()));
    }
    //collects data and adds new seller object to sellers
    public void createNewSeller(TextField tfName, TextField tfAddress, TextField tfPhoneNumber){
        sellers.addNewItem(new Seller(tfName.getText(), tfAddress.getText(), tfPhoneNumber.getText()));
    }
    //collects data and adds new sale object to sales
    public void makeASale(Land property, DatePicker datepicker,String SellerName, TextField tfPrice, String BuyerName){
        sales.addNewItem(new Sale(property, datepicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), Integer.parseInt(tfPrice.getText()), SellerName, BuyerName));
    }
    //collects data and adds new land object to properties
    public void AddNewLand(TextField tfLotNumber, TextField tfLandArea, TextField tfAddress){
        properties.addNewItem(new Land(Integer.parseInt(tfLotNumber.getText()), Integer.parseInt(tfLandArea.getText()), tfAddress.getText()));
    }
    //collects data and adds new houseAndLand object to properties
    public void AddNewHouseAndLand(TextField tfLotNumber, TextField tfLandArea, TextField tfAddress, TextField tfConstructionArea, TextField tfBedrooms, TextField tfToilets){
        properties.addNewItem(new HouseAndLand(Integer.parseInt(tfLotNumber.getText()), Integer.parseInt(tfLandArea.getText()), tfAddress.getText(), Integer.parseInt(tfConstructionArea.getText()), Integer.parseInt(tfBedrooms.getText()), Integer.parseInt(tfToilets.getText())));
    }
    
    private void defaultData(){
        buyers.addNewItem(new Buyer("Bob", "first street", "4545454545"));
        sellers.addNewItem(new Seller("alice", "first street", "4545454545"));
        
        properties.addNewItem(new Land(10000001, 2000, "hello world"));
        properties.addNewItem(new Land(10000002, 1000, "world"));
        properties.addNewItem(new HouseAndLand(10000003, 2000, "hello world",1000,4,2));
        properties.addNewItem(new HouseAndLand(10000004, 4000, "hello world",2000,8,4));
        properties.addNewItem(new HouseAndLand(10000005, 8000, "hello world",4000,16,8));
        
        sales.addNewItem( new Sale(properties.getItem(0), "18/05/2023",300000 , "Bob", "Alce"));
        sales.addNewItem( new Sale(properties.getItem(1), "18/05/2026",150000 , "Bob2", "Alce2"));
        sales.addNewItem( new Sale(properties.getItem(2), "18/05/2026",650000 , "Bob3", "Alce3"));
        sales.addNewItem( new Sale(properties.getItem(3), "18/05/2026",1200000 , "Bob", "Alce"));
    }
    
    
    
    private ArrayList<Double> getSalePricesOf(String className){
        ArrayList<Double> temp = new ArrayList<>();
        for(Sale sale : sales.getArrayList()){
            if(sale.getProperty().getClassInstanceName().equals(className)){
                temp.add(sale.getSoldPrice());
            }
           
        }
        return temp;
    }
    
    public void getStatitics(BarChart barChart){
        barChartInfo = new BarChartInfo(barChart, getSalePricesOf("Land"), getSalePricesOf("HouseAndLand"));
    }
    

}

