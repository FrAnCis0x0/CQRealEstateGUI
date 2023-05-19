/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqrealestatepackage.model;

import cqrealestatepackage.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 *
 * @author renza
 */
public class NavigateToScene {
    private Pane view;
    
    public Pane getFxml(String name){
        try{
            FXMLLoader loader = new FXMLLoader(App.class.getResource("view/"+name+".fxml"));
            view = loader.load();
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
        
        return view;
    }
}
