/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cqrealestatepackage.controller;

import cqrealestatepackage.model.InputFieldHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author renza
 */
public class MakeasaleController implements Initializable {


    @FXML
    private ComboBox<?> cbSeller;
    @FXML
    private ComboBox<?> cbBuyer;
    @FXML
    private TextField tfPrice;
    @FXML
    private DatePicker dpDate;
    @FXML
    private TableView<?> tvProperties;
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
        // TODO - combo box implemetation
        inputHandler = new InputFieldHandler();
        inputHandler.addAllTextFields(tfPrice);
    }    
    
    @FXML
    private void save(ActionEvent event) {
//        System.out.println(inputHandler.isDateValid(dpDate));
    }

    @FXML
    private void clearInputFields(ActionEvent event) {
    }

}
