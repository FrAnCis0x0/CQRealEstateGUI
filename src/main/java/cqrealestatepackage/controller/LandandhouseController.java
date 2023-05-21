/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cqrealestatepackage.controller;

import cqrealestatepackage.App;
import cqrealestatepackage.model.BorderPaneInfo;
import cqrealestatepackage.model.InputFieldHandler;
import cqrealestatepackage.model.NavigateToScene;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

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
        inputHandler = new InputFieldHandler();//holds function for validating inputs
        inputHandler.addAllTextFields(tfAddress, tfLotNumber, tfLandArea, tfConstructionArea, tfBedrooms, tfToilets); //add all text fields to inputhandler
        inputHandler.addListenerOnFocus();//add listener to all inputfields
        //specify which input must be numbers
        inputHandler.addIntegerListenerOnFocus(tfLotNumber);
        inputHandler.addIntegerListenerOnFocus(tfLandArea);
        inputHandler.addIntegerListenerOnFocus(tfConstructionArea);
        inputHandler.addIntegerListenerOnFocus(tfBedrooms);
        inputHandler.addIntegerListenerOnFocus(tfToilets);
    }    

    @FXML
    private void save(ActionEvent event) {
         //check if all textfields have valid data
        if(inputHandler.textFieldsHaveValue() && inputHandler.tfMustBeInteger(tfLotNumber, tfLandArea, tfConstructionArea, tfBedrooms, tfToilets)){
            App.dataHandler.AddNewHouseAndLand(tfLotNumber, tfLandArea, tfAddress, tfConstructionArea, tfBedrooms, tfToilets); //save data to file
            inputHandler.clear(); // clear data in text fields
        }else{
            inputHandler.showEmptyTextField(); // change borders to red
                
        }
    }

    @FXML
    private void clearInputFields(ActionEvent event) {
        inputHandler.clear(); // clear data in text fields

        
    }
    
    
    //Back button uses this to go back to property menu
    @FXML
    private void gotoPropertyMenu(ActionEvent event){
        NavigateToScene navToScene = new NavigateToScene();
        try{
            //when goto back to main property menu
            Pane view = navToScene.getFxml("addProperty");
            BorderPaneInfo.borderPane.setCenter(view);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}  
