/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqrealestatepackage.model;

import java.time.format.DateTimeFormatter;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 *
 * @author renza
 */
public class DataHandler {
    public FileManager<Sale> sales = new FileManager<>("salesData");
    public FileManager<Buyer> buyers = new FileManager<>("buyersData");
    public FileManager<Seller> sellers = new FileManager<>("sellersData");
    public FileManager<Land> properties = new FileManager<>("propertyData");
    
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

}

