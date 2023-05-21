/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cqrealestatepackage.controller;

import cqrealestatepackage.App;
import cqrealestatepackage.model.BorderPaneInfo;
import cqrealestatepackage.model.NavigateToScene;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author renza
 */
public class MainController implements Initializable {

    @FXML
    private BorderPane mainBorderPane;
    
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnMakeASale;
    @FXML
    private Button btnAddBuyer;
    @FXML
    private Button btnAddSeller;
    @FXML
    private Button btnAddProperty;
    
    private NavigateToScene navToScene;
    private ArrayList<Button> navButtonArray;
    private int currentBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        navButtonArray = new ArrayList<>();//stored all buttons
        navToScene = new NavigateToScene();//holds navigation functions
        BorderPaneInfo.borderPane = mainBorderPane;

        currentBtn = -1;//stores btn index
        navButtonArray.add(btnDashboard);
        navButtonArray.add(btnMakeASale);
        navButtonArray.add(btnAddBuyer);
        navButtonArray.add(btnAddSeller);
        navButtonArray.add(btnAddProperty);
        navigateTo("dashboard");
        changeButtonColor(0);
        salesDataAvailable();//chech if sales data is accessible
        
        
    }    

    @FXML
    private void gotoDashboard(ActionEvent event) {
        changeButtonColor(0);
        navigateTo("dashboard");

    }

    @FXML
    private void gotoMakeASale(ActionEvent event) {
        
        changeButtonColor(1);
        
        navigateTo("makeasale");

    }

    @FXML
    private void gotoAddBuyer(ActionEvent event) {
        changeButtonColor(2);
        navigateTo("addbuyer");

    }

    @FXML
    private void gotoAddSeller(ActionEvent event) {
        changeButtonColor(3);
        navigateTo("addseller");

    }

    @FXML
    private void gotoAddProperty(ActionEvent event) {
        changeButtonColor(4);
        navigateTo("addproperty");
        

    }
    
    private void navigateTo(String name){
        Pane view = navToScene.getFxml(name);
        mainBorderPane.setCenter(view);
    }
    
    private void changeButtonColor(int btnIndex){
        //makes sure the clicked button is not the same 
        //and if so, change button style
        if(currentBtn != btnIndex){
            navButtonArray.get(btnIndex).getStyleClass().add("btnClick");
            navButtonArray.get(btnIndex).getStyleClass().remove("btn");

        }
        //when a new button is clicked change styling of the old button
        if(currentBtn >= 0 && currentBtn != btnIndex){
            navButtonArray.get(currentBtn).getStyleClass().remove("btnClick");
            navButtonArray.get(currentBtn).getStyleClass().add("btn");
        }
        currentBtn = btnIndex;//make the new button the current button

    }
    //Alert Message
    //check if sales data exist
    private void salesDataAvailable(){
        if(App.dataHandler.sales.getArrayList().isEmpty()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("File Error");
            alert.setHeaderText("Sales Data Not Found");
            alert.setContentText("File \"SaleData.txt\" does not exist or it's empty.");

            // Show the alert
            alert.showAndWait();
         
        }
        
    }
    
}
