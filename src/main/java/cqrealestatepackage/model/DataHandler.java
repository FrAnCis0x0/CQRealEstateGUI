/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqrealestatepackage.model;

/**
 *
 * @author renza
 */
public class DataHandler {
    public FileManager<Sale> sales = new FileManager<>("salesData");
    public FileManager<Buyer> buyers = new FileManager<>("buyersData");
    public FileManager<Seller> sellers = new FileManager<>("sellersData");
    
}
