/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cqrealestatepackage.controller;

import cqrealestatepackage.App;
import cqrealestatepackage.model.HouseAndLand;
import cqrealestatepackage.model.InputFieldHandler;
import cqrealestatepackage.model.Land;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author renza
 */
public class MakeasaleController implements Initializable {


    @FXML
    private ComboBox<String> cbSeller;
    @FXML
    private ComboBox<String> cbBuyer;
    @FXML
    private TextField tfPrice;
    @FXML
    private DatePicker dpDate;
    @FXML
    private TableView<Land> tvProperties;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClear;
    private InputFieldHandler inputHandler; //handles inputs validation
    private ObservableList<String> observableList;//observes all username
    private TableColumn<Land, Integer> propertyTypeColumn; 
    private TableColumn<Land, Integer> lotNumberColumn ;
    private TableColumn<Land, Integer> landAreaColumn;
    private TableColumn<Land, String> addressColumn;
    private TableColumn<Land, Double> constructionAreaColumn;
    private TableColumn<Land, Integer> bedroomColumn;
    private TableColumn<Land, Integer> toiletsColumn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inputHandler = new InputFieldHandler();
        inputHandler.addAllTextFields(tfPrice);
        
        
        
        
        observableList = FXCollections.observableArrayList(App.dataHandler.getAllUsers());//Observes buyers arraylist for changes
        cbSeller.setItems(observableList);
        cbBuyer.setItems(FXCollections.observableArrayList(observableList));
        
        cbSeller.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Remove the selected name from Buyer
                cbBuyer.getItems().remove(newValue);
        });

        cbBuyer.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Remove the selected name from Seller
                cbSeller.getItems().remove(newValue);
        });
        
        //Table View Columns
        
        propertyTypeColumn = new TableColumn<>("Property Type");
        propertyTypeColumn.setCellValueFactory(new PropertyValueFactory<>("propertyType"));

        lotNumberColumn = new TableColumn<>("Lot Number");
        lotNumberColumn.setCellValueFactory(new PropertyValueFactory<>("lotNumber"));

        landAreaColumn = new TableColumn<>("Land Area");
        landAreaColumn.setCellValueFactory(new PropertyValueFactory<>("landArea"));
        
        addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        
        constructionAreaColumn = new TableColumn<>("Contruction Area");
         //Only access Construction Area from HouseAndLand class
        constructionAreaColumn.setCellValueFactory(cellData ->{
            if(cellData.getValue().getPropertyType().equals("HouseAndLand")){
                HouseAndLand tempHouse = (HouseAndLand) cellData.getValue();
                return new SimpleObjectProperty<>(tempHouse.getConstructionArea());
            }
            return new SimpleObjectProperty<>(null);
            
        });

        bedroomColumn = new TableColumn<>("Bedrooms");
         //Only access NumberOfBedrooms from HouseAndLand class
        bedroomColumn.setCellValueFactory(cellData ->{
            if(cellData.getValue().getPropertyType().equals("HouseAndLand")){
                HouseAndLand tempHouse = (HouseAndLand) cellData.getValue();
                return new SimpleObjectProperty<>(tempHouse.getNumberOfBedrooms());
            }
            return new SimpleObjectProperty<>(null);
            
        });
        
        
        toiletsColumn = new TableColumn<>("Toilets");
         //Only access NumberOfToilets from HouseAndLand class
        toiletsColumn.setCellValueFactory(cellData ->{
            if(cellData.getValue().getPropertyType().equals("HouseAndLand")){
                HouseAndLand tempHouse = (HouseAndLand) cellData.getValue();
                return new SimpleObjectProperty<>(tempHouse.getNumberOfToilets());
            }
            return new SimpleObjectProperty<>(null);
            
        });
        
        //Add all columns to tableView
        tvProperties.getColumns().addAll(propertyTypeColumn,lotNumberColumn, landAreaColumn, addressColumn, constructionAreaColumn, bedroomColumn,toiletsColumn);
        ObservableList<Land> personObservableList = FXCollections.observableArrayList();
        personObservableList.addAll(App.dataHandler.getAllAvailableProperties());

        tvProperties.setItems(personObservableList);

    }    
    
    @FXML
    private void save(ActionEvent event) {
        int selectedPropertyID = tvProperties.getSelectionModel().getSelectedIndex();
            
        if(inputHandler.choiceSelected(cbSeller,cbBuyer) && inputHandler.textFieldsHaveValue() &&
        inputHandler.tfMustBeInteger(tfPrice) && inputHandler.isDateValid(dpDate) && selectedPropertyID != -1 && inputHandler.numberNumberNotTooBig(tfPrice)){

           App.dataHandler.makeASale(tvProperties.getItems().get(selectedPropertyID), dpDate, cbSeller.getValue(), tfPrice, cbBuyer.getValue()); //save data to file
           tvProperties.getItems().remove(selectedPropertyID);
        }else{
            
           
            inputHandler.showEmptyTextField(); // change borders to red
          
                
                //TODO - Show tooltip
            
        }
    }

    @FXML
    private void clearInputFields(ActionEvent event) {
    }

}
