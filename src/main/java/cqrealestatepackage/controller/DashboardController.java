/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cqrealestatepackage.controller;

import cqrealestatepackage.App;
import cqrealestatepackage.model.Land;
import cqrealestatepackage.model.Sale;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.chart.BarChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author renza
 */
public class DashboardController implements Initializable {


    @FXML
    private BarChart<?, ?> dashboardBarChart;
    
    @FXML
    private TableColumn<Sale, String> columnDate;   
    @FXML
    private TableColumn<Sale, String> columnPropertyType;   
    @FXML
    private TableColumn<Sale, Double> columnPrice;   
    @FXML
    private TableColumn<Sale, String> columnSeller;   
    @FXML
    private TableColumn<Sale, String> columnBuyer;   

    @FXML
    private TableView<Sale> mainTableView;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.dataHandler.getStatitics(dashboardBarChart);//provides statistics data to BarChart
        
        
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnPropertyType.setCellValueFactory(new PropertyValueFactory<>("propertyType"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("soldPrice"));
        columnSeller.setCellValueFactory(new PropertyValueFactory<>("sellerName"));
        columnBuyer.setCellValueFactory(new PropertyValueFactory<>("buyerName"));
        
       
        
        ObservableList<Sale> salesObservableList = FXCollections.observableArrayList();
        salesObservableList.addAll(App.dataHandler.sales.getArrayList());
        
        mainTableView.setItems(salesObservableList);

    }    
    
}
