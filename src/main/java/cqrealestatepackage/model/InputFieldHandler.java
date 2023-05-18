/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqrealestatepackage.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.control.DatePicker;
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
    
    //checks if text field has only strings
    public boolean tfIsString(TextField tf){
        return tf.getText().matches("[a-zA-Z]+");
    }
    //checks if text field has only numbers
    public boolean tfIsInteger(TextField tf){
        return tf.getText().matches("[0-9]+");
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
        LocalDate selectedDate = dp.getValue();
        return !(selectedDate == null);
    }
    
    //
    public void showEmptyTextField(){
        emptyTextField.forEach(tf -> {
            changeBorderColor(tf);
        });
        
    }
    
    public void changeBorderColor(TextField tf){
        tf.getStyleClass().add("invalidInput");
        System.out.println("Change Input Color ERROR - Emp");
    }
   
    //clears data from all Text Fields in tfArray
    public void clear(){
        tfArray.forEach(tf ->{
            tf.clear();
        });
    }
    
}
