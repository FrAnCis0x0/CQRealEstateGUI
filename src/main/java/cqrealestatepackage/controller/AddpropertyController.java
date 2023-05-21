/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cqrealestatepackage.controller;

import cqrealestatepackage.model.BorderPaneInfo;
import cqrealestatepackage.model.NavigateToScene;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
/**
 * FXML Controller class
 *
 * @author renza
 */
public class AddpropertyController implements Initializable {


    @FXML
    private Button btnLand;
    @FXML
    private Button btnLandAndHouse;
    
    private NavigateToScene navToScene;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       navToScene = new NavigateToScene();
    }    
    
    @FXML
    private void gotoLand(ActionEvent event){
        navigateTo("land");

    }
    @FXML
    private void gotoLandAndHouse(ActionEvent event){
        navigateTo("landandhouse");
    }
    
    private void navigateTo(String name){
         try{
            
            Pane view = navToScene.getFxml(name);
            BorderPaneInfo.borderPane.setCenter(view);
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
