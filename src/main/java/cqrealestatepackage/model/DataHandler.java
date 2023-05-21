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
    private FileManager<Clients> clients;
    public FileManager<Land> properties;
    
    private BarChartInfo barChartInfo;
    
    public DataHandler() throws ClassNotFoundException{
        sales  = new FileManager<>("SalesData.txt");
        clients = new FileManager<>("UserData.txt");
        properties = new FileManager<>("PropertyData.txt");
        
        
    }
    
        //collects data and adds new buyer object to buyers
    public void createNewBuyer(TextField tfName, TextField tfAddress, TextField tfPhoneNumber){
        clients.addNewItem(new Buyer(tfName.getText(), tfAddress.getText(), tfPhoneNumber.getText()));
    }
    //collects data and adds new seller object to sellers
    public void createNewSeller(TextField tfName, TextField tfAddress, TextField tfPhoneNumber){
        clients.addNewItem(new Seller(tfName.getText(), tfAddress.getText(), tfPhoneNumber.getText()));
    }
    //collects data and adds new sale object to sales
    public void makeASale(Land property, DatePicker datepicker,String SellerName, TextField tfPrice, String BuyerName){
        sales.addNewItem(new Sale(property, datepicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), Integer.parseInt(tfPrice.getText()), SellerName, BuyerName));
    }
    //collects data and adds new land object to properties
    public void AddNewLand(TextField tfLotNumber, TextField tfLandArea, TextField tfAddress){
        properties.addNewItem(new Land(Integer.parseInt(tfLotNumber.getText()), Integer.parseInt(tfLandArea.getText()), tfAddress.getText()));
    }
    //collects data and adds new houseAndLand object to properties
    public void AddNewHouseAndLand(TextField tfLotNumber, TextField tfLandArea, TextField tfAddress, TextField tfConstructionArea, TextField tfBedrooms, TextField tfToilets){
        properties.addNewItem(new HouseAndLand(Integer.parseInt(tfLotNumber.getText()), Integer.parseInt(tfLandArea.getText()), tfAddress.getText(), Integer.parseInt(tfConstructionArea.getText()), Integer.parseInt(tfBedrooms.getText()), Integer.parseInt(tfToilets.getText())));
    }
    
    //get all prices of Property Type
    private ArrayList<Double> getSalePricesOf(String className){
        ArrayList<Double> temp = new ArrayList<>();
        //add sales of className to temp
        for(Sale sale : sales.getArrayList()){
            if(sale.getProperty().getPropertyType().equals(className)){
                temp.add(sale.getSoldPrice());
            }
           
        }
        return temp;
    }
   
    
    public void getStatitics(BarChart barChart){
        barChartInfo = new BarChartInfo(barChart, getSalePricesOf("Land"), getSalePricesOf("HouseAndLand"));
    }
    
    
    public ArrayList<Land> getAllAvailableProperties(){
        ArrayList<Land> tempArray = new ArrayList<>();
        for(Land property : properties.getArrayList()){
            if(!property.isSold()){
                tempArray.add(property);
            }
        }
        return tempArray;
    }
    
    public ArrayList<String> getAllUsers(){
        ArrayList<String> tempUserArray = new ArrayList<>();
        
        clients.getArrayList().forEach(user ->{
            tempUserArray.add(user.getName());
        });
        
        
        return tempUserArray;
    
    }
    
    

}

