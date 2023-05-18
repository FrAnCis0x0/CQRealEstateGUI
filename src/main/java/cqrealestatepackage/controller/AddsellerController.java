/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cqrealestatepackage.controller;

import cqrealestatepackage.App;
import cqrealestatepackage.model.InputFieldHandler;
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
public class AddsellerController implements Initializable {


    @FXML
    private TextField tfName;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfAddress;
    @FXML
    private Button btnSave;
    @FXML
    private Button btrnClear;
    
    private InputFieldHandler inputHandler;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inputHandler = new InputFieldHandler();
        inputHandler.addAllTextFields(tfAddress, tfName, tfPhone);
    }    
    
    @FXML
    private void save(ActionEvent event) {
        if(inputHandler.textFieldsHaveValue() && inputHandler.tfIsInteger(tfPhone)){
           App.dataHandler.createNewSeller(tfName, tfAddress, tfPhone); //save data to file
        }else{
           
            inputHandler.showEmptyTextField(); // change borders to red
          
                
                //TODO - Show tooltip
        }
        
    }

    @FXML
    private void clearInputFields(ActionEvent event) {
        inputHandler.clear();//clear all input fields

    }
    
   

}
