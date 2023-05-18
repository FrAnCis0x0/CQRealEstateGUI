/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cqrealestatepackage.controller;

import cqrealestatepackage.App;
import cqrealestatepackage.model.HouseAndLand;
import cqrealestatepackage.model.InputFieldHandler;
import cqrealestatepackage.model.Land;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author renza
 */
public class LandandhouseController implements Initializable {

    @FXML
    private TextField tfLotNumber;
    @FXML
    private TextField tfLandArea;
    @FXML
    private TextField tfAddress;
    @FXML
    private TextField tfConstructionArea;
    @FXML
    private TextField tfBedrooms;
    @FXML
    private TextField tfToilets;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClear;

    private InputFieldHandler inputHandler;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inputHandler = new InputFieldHandler();
        inputHandler.addAllTextFields(tfAddress, tfLotNumber, tfLandArea, tfConstructionArea, tfBedrooms, tfToilets); //add all text fields to inputhandler

    }    

    @FXML
    private void save(ActionEvent event) {
         //check if all textfields have valid data
        if(inputHandler.textFieldsHaveValue() && inputHandler.tfMustBeInteger(tfLotNumber, tfLandArea, tfConstructionArea, tfBedrooms, tfToilets)){
           App.dataHandler.AddNewHouseAndLand(tfLotNumber, tfLandArea, tfAddress, tfConstructionArea, tfBedrooms, tfToilets); //save data to file
        }else{
           
            inputHandler.showEmptyTextField(); // change borders to red
          
                
                //TODO - Show tooltip
            
        }
    }

    @FXML
    private void clearInputFields(ActionEvent event) {
        inputHandler.clear(); // clear data in text fields

        
    }
    
}  
