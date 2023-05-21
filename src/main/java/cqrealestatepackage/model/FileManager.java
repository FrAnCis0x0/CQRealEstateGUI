/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqrealestatepackage.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author renza
 */
public class FileManager<F> {
    private String fileName; //stores file name
    private ArrayList<F> dataArray = new ArrayList<>(); //stores older and new data

    public FileManager(String fileName) throws ClassNotFoundException {
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
        try{
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dataArray);
            oos.close();
            fos.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    //read data from file and store it into an array
    private void readFile() throws ClassNotFoundException{
        try{
            File dataFile = new File(fileName);
            if(dataFile.isFile() && dataFile.canRead()){
                
                FileInputStream fis = new FileInputStream(dataFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                dataArray = (ArrayList<F>)ois.readObject();
                ois.close();
                fis.close();
            
            }

        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
       
    }
    
}
