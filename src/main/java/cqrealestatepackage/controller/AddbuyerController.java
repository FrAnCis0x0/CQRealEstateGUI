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
public class AddbuyerController implements Initializable {


    @FXML
    private TextField tfName;
    @FXML
    private TextField tfPhone;
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
        inputHandler = new InputFieldHandler();//holds function for validating inputs
        inputHandler.addAllTextFields(tfAddress, tfName, tfPhone); //add all text fields to inputhandler
        inputHandler.addListenerOnFocus();
        inputHandler.addIntegerListenerOnFocus(tfPhone);
    }    
    
    @FXML
    private void save(ActionEvent event) {
        //check if all textfields have valid data
        if(inputHandler.textFieldsHaveValue() && inputHandler.tfMustBeInteger(tfPhone)){
            App.dataHandler.createNewBuyer(tfName, tfAddress, tfPhone); //save data to file
            inputHandler.clear(); // clear data in text fields

        }else{
            inputHandler.showEmptyTextField(); // change borders to red
        }
        
    }
    
    // clears all inputs 
    @FXML
    private void clearInputFields(ActionEvent event) {
        inputHandler.clear(); // clear data in text fields

    }

}
