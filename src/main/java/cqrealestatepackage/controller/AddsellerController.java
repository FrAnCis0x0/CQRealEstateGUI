/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cqrealestatepackage.controller;

import cqrealestatepackage.App;
import cqrealestatepackage.model.InputFieldHandler;
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
        inputHandler = new InputFieldHandler();//holds function for validating inputs
        inputHandler.addAllTextFields(tfAddress, tfName, tfPhone);
        inputHandler.addListenerOnFocus();//add listener to all textFields
        inputHandler.addIntegerListenerOnFocus(tfPhone); //listen to make sure tfPhone is number
    }    
    
    @FXML
    private void save(ActionEvent event) {
        //make sure all inputs are valid before saving an object
        if(inputHandler.textFieldsHaveValue() && inputHandler.tfIsInteger(tfPhone)){
            App.dataHandler.createNewSeller(tfName, tfAddress, tfPhone); //save data to file
            inputHandler.clear();//clear all input fields

        }else{
           
            inputHandler.showEmptyTextField(); // change borders to red
                
        }
        
    }

    @FXML
    private void clearInputFields(ActionEvent event) {
        inputHandler.clear();//clear all input fields

    }
    
   

}
