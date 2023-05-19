/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqrealestatepackage.model;

import java.util.ArrayList;

/**
 *
 * @author renza
 */
public class FileManager<F> {
    private String fileName; //stores file name
    private final ArrayList<F> dataArray = new ArrayList<>(); //stores older and new data

    public FileManager(String fileName) {
        this.fileName = fileName;
        readFile();
    }
    //Add object to Array and save array to file
    public void addNewItem(F item){
        dataArray.add(item);
        saveArrayToFile();
    }
    
    //get item from array with specified index
    public F getItem(int index){
       return dataArray.get(index);
    }
    
    //return arrayList
    public ArrayList<F> getArrayList(){
        return dataArray;
    }
    
    //save data to file
    private void saveArrayToFile(){
        System.out.println("Saved to File");
    }
    
    //read data from file and store it into an array
    private void readFile(){
        System.out.println("Reading... "+fileName);
    }
    
}
