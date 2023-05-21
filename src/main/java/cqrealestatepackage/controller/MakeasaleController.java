/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cqrealestatepackage.controller;

import cqrealestatepackage.App;
import cqrealestatepackage.model.BorderPaneInfo;
import cqrealestatepackage.model.HouseAndLand;
import cqrealestatepackage.model.InputFieldHandler;
import cqrealestatepackage.model.Land;
import cqrealestatepackage.model.NavigateToScene;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
    //table columns
    private TableColumn<Land, Integer> propertyTypeColumn; 
    private TableColumn<Land, Integer> lotNumberColumn ;
    private TableColumn<Land, Integer> landAreaColumn;
    private TableColumn<Land, String> addressColumn;
    private TableColumn<Land, Double> constructionAreaColumn;
    private TableColumn<Land, Integer> bedroomColumn;
    private TableColumn<Land, Integer> toiletsColumn;
    
    private NavigateToScene navToScene;//contains functions for button navigation

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inputHandler = new InputFieldHandler();
        navToScene = new NavigateToScene();
        inputHandler.addAllTextFields(tfPrice);
        inputHandler.addListenerOnFocus();
        inputHandler.addIntegerListenerOnFocus(tfPrice);
        Tooltip tooltip = new Tooltip("Select a valid date");
        Tooltip.install(dpDate, tooltip);
        
        
        
        observableList = FXCollections.observableArrayList(App.dataHandler.getAllUsers());//Observes buyers arraylist for changes
        cbSeller.setItems(observableList);
        cbBuyer.setItems(FXCollections.observableArrayList(observableList));
//        
        cbSeller.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Change color after selection
            if(newValue == null ? oldValue != null : !newValue.equals(oldValue)){
                    cbSeller.getStyleClass().add("correct-border");
            }
        });

        cbBuyer.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Change color after selection
                if(newValue == null ? oldValue != null : !newValue.equals(oldValue)){
                    cbBuyer.setStyle("-fx-border-color: #00ff00; -fx-border-width: 2;");
                }
            
        });
        
        //Date Picker Validation
        dpDate.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (!inputHandler.isDateValid(dpDate)) {
                Stage stage = (Stage) dpDate.getScene().getWindow();
                //get tooltip position
                Platform.runLater(() -> {
                    double x = dpDate.localToScene(dpDate.getBoundsInLocal()).getMinX(); //tooltip x position
                    double y = dpDate.localToScene(dpDate.getBoundsInLocal()).getMinY();//tooltip y possition
                    tooltip.show(dpDate, stage.getX() + x, stage.getY() + y); 
                });
//              
            }else{
                tooltip.hide();
            }
        });
        //add styling to date picker
        dpDate.setStyle("date-picker");
        
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
        cbSeller.setPromptText("Select Seller");

       

    }    
    
    @FXML
    private void save(ActionEvent event) {
        //get selected Item index
        int selectedPropertyID = tvProperties.getSelectionModel().getSelectedIndex();
        
        //validate every field is filled with appropriate data
        if(inputHandler.choiceSelected(cbSeller,cbBuyer) && inputHandler.textFieldsHaveValue() &&
        inputHandler.tfMustBeInteger(tfPrice) && inputHandler.isDateValid(dpDate) && inputHandler.numberNotTooBig(tfPrice) && inputHandler.isPropertySelected(tvProperties)){
            //create the object
           App.dataHandler.makeASale(tvProperties.getItems().get(selectedPropertyID), dpDate, cbSeller.getValue(), tfPrice, cbBuyer.getValue()); //save data to file
           //remove selected property
           tvProperties.getItems().remove(selectedPropertyID);
           //clear data from fields
            clearInputFields(event);
        }else{
            inputHandler.showEmptyTextField(); // change borders to red
            
        }
    }

    @FXML
    private void clearInputFields(ActionEvent event) {
        //this is the easiest way to clear data from input fields
        navigateTo("makeasale");
    }
    //navigate to fxml file
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
