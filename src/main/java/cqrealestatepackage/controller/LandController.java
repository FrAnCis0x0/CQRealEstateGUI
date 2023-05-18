/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cqrealestatepackage.controller;

import cqrealestatepackage.App;
import cqrealestatepackage.model.Buyer;
import cqrealestatepackage.model.InputFieldHandler;
import cqrealestatepackage.model.Land;
import cqrealestatepackage.model.Seller;
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
public class LandController implements Initializable {


    @FXML
    private TextField tfLotNumber;
    @FXML
    private TextField tfArea;
    @FXML
    private TextField tfAddress;
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
        inputHandler.addAllTextFields(tfAddress, tfLotNumber, tfArea); //add all text fields to inputhandler
    }    
    
    @FXML
    private void save(ActionEvent event) {
        //check if all textfields have valid data
        if(inputHandler.textFieldsHaveValue() && inputHandler.tfMustBeInteger(tfLotNumber, tfArea)){
           App.dataHandler.AddNewLand(tfLotNumber, tfArea, tfAddress); //save data to file
        }else{
            
            inputHandler.showEmptyTextField(); // change borders to red
          
                
                //TODO - Show tooltip
            
        }
        
    }

    @FXML
    private void clearInputsFields(ActionEvent event) {
        inputHandler.clear(); // clear data in text fields

    }

}
