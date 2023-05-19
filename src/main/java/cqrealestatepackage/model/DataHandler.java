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
//    public FileManager<Buyer> buyers;
//    public FileManager<Seller> sellers;
    private FileManager<Clients> clients;
    public FileManager<Land> properties;
    
    private BarChartInfo barChartInfo;
    
    public DataHandler(){
        sales  = new FileManager<>("PalesData");
//        buyers = new FileManager<>("buyersData");
//        sellers = new FileManager<>("sellersData");
        clients = new FileManager<>("UserData");
        properties = new FileManager<>("PropertyData");
        defaultData();
        
        
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
    
    private void defaultData(){
        clients.addNewItem(new Buyer("Bob", "first street", "4545454545"));
        clients.addNewItem(new Seller("Alice", "first street", "4545454545"));
        
        properties.addNewItem(new Land(10000001, 2000, "hello world"));
        properties.addNewItem(new Land(10000002, 1000, "world"));
        properties.addNewItem(new HouseAndLand(10000003, 2000, "128 helloworld street",1000,4,2));
        properties.addNewItem(new HouseAndLand(10000004, 4000, "64 bit rd",2000,8,4));
        properties.addNewItem(new HouseAndLand(10000005, 8000, "32 bunny street",4000,16,8));
        properties.addNewItem(new HouseAndLand(10000006, 2000, "16 carrot street",1000,4,2));
        properties.addNewItem(new Land(10000007, 2000, "123 that st"));
        
        sales.addNewItem( new Sale(properties.getItem(0), "17/05/2023",300000 , "Bob", "Alice"));
        sales.addNewItem( new Sale(properties.getItem(1), "18/05/2023",150000 , "Alice", "Jeff"));
        sales.addNewItem( new Sale(properties.getItem(2), "19/05/2023",650000 , "Mike", "John Doe"));
        sales.addNewItem( new Sale(properties.getItem(3), "20/05/2023",1200000 , "Miller", "Kira"));
    }
    
    
    
    private ArrayList<Double> getSalePricesOf(String className){
        ArrayList<Double> temp = new ArrayList<>();
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

