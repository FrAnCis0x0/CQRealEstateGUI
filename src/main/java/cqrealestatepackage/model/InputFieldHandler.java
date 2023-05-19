/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqrealestatepackage.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 *
 * @author renza
 */
public class InputFieldHandler {
    private ArrayList<TextField> tfArray;//Stores textField objects
    private ArrayList<TextField> emptyTextField; //stores input fields that still reqquire data
    private ComboBox[] comboList;
    public InputFieldHandler(){
        this.tfArray = new ArrayList<>();
        this.emptyTextField = new ArrayList<>();
        comboList = new ComboBox[2];
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
            if(comboBox.getValue() == null){
                changeBorderColor(comboBox);
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
    
    public boolean numberNumberNotTooBig(TextField tf){
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
            return true;
        }else{
            //date selected is in the past
            if(dp.getValue() != null && !validateDate(dp.getValue())){
                System.out.println("Show tooltip");
            }
            
            System.out.println("Invalid date.. Color change");
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
        tf.getStyleClass().remove("textInputFields:focused");
        tf.getStyleClass().add("invalidInput");
        
        System.out.println("Change Input Color ERROR - Emp");
    }
    
    
    public void changeBorderColor(ComboBox cb){
        cb.getStyleClass().remove("textInputFields");
        cb.getStyleClass().add("invalidInput");
        
        System.out.println("Change Input Color ERROR - Emp");
    }
    
    
   
    //clears data from all Text Fields in tfArray
    public void clear(){
        tfArray.forEach(tf ->{
            tf.clear();
        });
    }
    
}
