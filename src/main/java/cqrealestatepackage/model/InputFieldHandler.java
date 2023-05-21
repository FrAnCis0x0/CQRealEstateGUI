/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqrealestatepackage.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author renza
 */
public class InputFieldHandler {
    private ArrayList<TextField> tfArray;//Stores textField objects
    private ArrayList<TextField> emptyTextField; //stores input fields that still reqquire data
    public InputFieldHandler(){
        this.tfArray = new ArrayList<>();
        this.emptyTextField = new ArrayList<>();
    }
    //Adds one textfield object to tfArray
    public void addTextField(TextField tf){
        this.tfArray.add(tf);
    }
    //Adds multiple textfield objects to tfArray
    public void addAllTextFields(TextField... tf){
        this.tfArray.addAll(Arrays.asList(tf));
    }
    
    //checks if all fields are not empty
    public boolean textFieldsHaveValue(){
        emptyTextField.clear();
        for(TextField tf: tfArray){
            if(tf.getText().isEmpty()){
                emptyTextField.add(tf);
            }
        }
        return (emptyTextField.isEmpty());
    }
    
    public boolean choiceSelected(ComboBox... cb){
        for(ComboBox comboBox : cb){
            changeBorderColor(comboBox);
            if(comboBox.getValue() == null){
                return false;
            }
        }
        return true;
    }
    
    //checks if text field has only strings
    public boolean tfIsString(TextField tf){
        return tf.getText().matches("[a-zA-Z]+");
    }
    //checks if text field has only numbers
    public boolean tfIsInteger(TextField tf){
        return tf.getText().matches("[0-9]+");
    }
    
    public boolean numberNotTooBig(TextField tf){
        try{
            int temp = Integer.parseInt(tf.getText());
            return true;
        }
        catch(NumberFormatException e){
            System.out.println("Number is too big");
            return false;
        }
    }
    
    //makes sure inputs that require integer have integers
    public boolean tfMustBeInteger(TextField... tf){
        //iterates through provided array 
        for (TextField txtField : Arrays.asList(tf)){
            //checks for anything that is not an integer
            if(!tfIsInteger(txtField)){
                changeBorderColor(txtField);
                System.out.println("Not Number");
                return false;
            }
           
        }
        return true;
        
    }
    
    //validates Date Picker data
    public boolean isDateValid(DatePicker dp){
        //make sure date is not null and the selected date is not a past date
        if(dp.getValue() != null && validateDate(dp.getValue())){
            dp.setStyle("-fx-border-color: #00ff00; -fx-border-width: 2;");
            return true;
        }else{
          
            dp.setStyle("-fx-border-color: red; -fx-border-width: 3;");
            return false;
            
        }

    }
    //
    private boolean validateDate(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        return !date.isBefore(currentDate);
    }
    
    //
    public void showEmptyTextField(){
        emptyTextField.forEach(tf -> {
            changeBorderColor(tf);
        });
        
    }
    
    public void changeBorderColor(TextField tf){
        tf.getStyleClass().remove("textInputFields");
        tf.getStyleClass().add("invalidInput");
        tf.applyCss();

    }
    
    
    public void changeBorderColor(ComboBox cb){
        if(cb.getValue() == null){
            
            cb.getStyleClass().remove("textInputFields");
            cb.getStyleClass().add("invalidInput");

        }else{
            cb.getStyleClass().remove("invalidInput");
            cb.getStyleClass().add("textInputFields");
            
        }
    }
    
    
   
    //clears data from all Text Fields in tfArray
    public void clear(){
        tfArray.forEach(tf ->{
            if(tf.getStyleClass().contains("invalidInput")){
                tf.getStyleClass().remove("invalidInput");
                tf.getStyleClass().add("textInputFields");
            }
            tf.clear();
            tf.setStyle("-fx-focus-color: #616161");
            tf.applyCss();
        });
    }
    
    public void addListenerOnFocus(){
        for(TextField item : tfArray){
            ChangeListener<String> textChangeListener = (observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                item.setStyle("-fx-border-color: red; -fx-border-width: 3 ;");
            } else {
                item.setStyle("-fx-border-color: #00ff00; -fx-border-width: 2;");
            }
            };
            
            item.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                item.textProperty().addListener(textChangeListener);
            } else {
                item.textProperty().removeListener(textChangeListener);
            }
            });
            
        }
    }
    
    public void addIntegerListenerOnFocus(TextField tf){
        
        ChangeListener<String> textChangeListener = (observable, oldValue, newValue) -> {
        if (newValue.isEmpty() || !tfIsInteger(tf)) {
            tf.setStyle("-fx-border-color: red; -fx-border-width: 3;");
        } else {
            tf.setStyle("-fx-border-color: #00ff00; -fx-border-width: 2;");
        }
        };

        tf.focusedProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue) {
            tf.textProperty().addListener(textChangeListener);
        } else {
            tf.textProperty().removeListener(textChangeListener);
        }
        });
            
        
    }
    
    
  public boolean isPropertySelected(TableView tvProperties){
      // Add a listener to the selected item property
            tvProperties.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
          if (newValue == null) {
              // Apply the style if no item is selected
              tvProperties.getStyleClass().remove("tvWithSelection");
              tvProperties.getStyleClass().add("tvNoSelection");
          } else {
              // Apply the style if an item is selected
              tvProperties.getStyleClass().remove("tvNoSelection");
              tvProperties.getStyleClass().add("tvWithSelection");
          }
      });
        //this does the same thing as the listener
        //but the listener can only be trigger when a user clicks the tableview
        //So this runs first
        if(tvProperties.getSelectionModel().getSelectedItem() == null){
            tvProperties.getStyleClass().add("tvNoSelection");
            return false;
        } else {
            tvProperties.getStyleClass().add("tvWithSelection");
            return true;
        }
        
  }
}
