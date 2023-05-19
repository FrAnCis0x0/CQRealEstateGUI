/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cqrealestatepackage.controller;

import cqrealestatepackage.model.BorderPaneInfo;
import cqrealestatepackage.model.NavigateToScene;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        navButtonArray = new ArrayList<>();
        navToScene = new NavigateToScene();
        navigateTo("dashboard");
        navButtonArray.add(btnDashboard);
        navButtonArray.add(btnMakeASale);
        navButtonArray.add(btnAddBuyer);
        navButtonArray.add(btnAddSeller);
        navButtonArray.add(btnAddProperty);
        
        
    }    

    @FXML
    private void gotoDashboard(ActionEvent event) {
        navigateTo("dashboard");
    }

    @FXML
    private void gotoMakeASale(ActionEvent event) {
        //Todo check if properties and users exist
        
        navigateTo("makeasale");

    }

    @FXML
    private void gotoAddBuyer(ActionEvent event) {
        navigateTo("addbuyer");

    }

    @FXML
    private void gotoAddSeller(ActionEvent event) {
        navigateTo("addseller");

    }

    @FXML
    private void gotoAddProperty(ActionEvent event) {
        navigateTo("addproperty");
        BorderPaneInfo.borderPane = mainBorderPane;


    }
    
    private void navigateTo(String name){
        Pane view = navToScene.getFxml(name);
        mainBorderPane.setCenter(view);
    }
    
}
