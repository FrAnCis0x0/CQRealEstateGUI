/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqrealestatepackage.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author renza
 */
public class BarChartInfo {

    BarChart myBar;
    ArrayList<Double> land;
    ArrayList<Double> landAndHouse;

    XYChart.Series series1;
    XYChart.Series series2;
    XYChart.Series series3;

    public BarChartInfo(BarChart myBar, ArrayList<Double> land, ArrayList<Double> landAndHouse) {
        this.myBar = myBar;
        this.myBar.setTitle("Sales Statitics");
        this.myBar.setCategoryGap(20);
        this.myBar.maxHeight(1000);
        
        this.land = land;
        this.landAndHouse = landAndHouse;

        series1 = new XYChart.Series();
        series2 = new XYChart.Series();
        series3 = new XYChart.Series();

        setBarChartData();

    }

    private void getMinimum() {
        series1.setName("Minimum");
        try{
            series1.getData().add(new XYChart.Data("Land", Collections.min(land)));
        }
        catch(NoSuchElementException e){
            series1.getData().add(new XYChart.Data("Land", 0));
        }
        
        
        try{
            series1.getData().add(new XYChart.Data("Land And House", Collections.min(landAndHouse)));
        }
        catch(NoSuchElementException e){
            series1.getData().add(new XYChart.Data("Land And House", 0));
        }


    }

    private void getAverage() {
        series2.setName("Average");
       
        

        series2.getData().add(new XYChart.Data("Land", calculateAverage(land)));

        series2.getData().add(new XYChart.Data("Land And House", calculateAverage(landAndHouse)));

    }

    private void getMaximum() {
        series3.setName("Maximum");
        checkAndAdd(series3, land, "Land");
        checkAndAdd(series3, landAndHouse, "Land And House");

    }
    
    private void checkAndAdd(XYChart.Series s, ArrayList<Double> myArray, String xName){
        if(myArray.isEmpty()){
             s.getData().add(new XYChart.Data(xName, 0));
        }else{
             s.getData().add(new XYChart.Data(xName, Collections.max(myArray)));
        }
        
        
    }

    private double calculateAverage(ArrayList<Double> arrayName) {
        int sum = 0;
        for (double price : arrayName) {
            sum += price;

        }
        return (arrayName.isEmpty())? 0:(double) (sum / arrayName.size());

    }

    private void setBarChartData() {
        getMinimum();
        getAverage();
        getMaximum();
        myBar.getData().addAll(series1, series2, series3);

    }

}
