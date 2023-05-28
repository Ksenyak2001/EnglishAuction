/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi2023.english_auction.lot;

import mephi2023.english_auction.lot.ExemplarLot;
import java.util.ArrayList;
import mephi2023.english_auction.CountFCL;

/**
 *
 * @author Kseny
 */
public class Lot extends ExemplarLot{
    private String lot_name;
    private int fame;
    private int significance;
    private int rarity;
    private double prev_price;
    private double now_price;
    public Lot(){
        this.prev_price = 0;
        this.now_price = 0;
    };
    public Lot (String file_name){
        this.prev_price = 0;
        this.now_price = 0;
        super.setFile_name(file_name);
    }
    public Lot(String lot_name, int fame, int significance, int rarity) {
        this.prev_price = 0;
        this.now_price = 0;
        this.lot_name = lot_name;
        this.fame = fame;
        this.significance = significance;
        this.rarity = rarity;
    }
    
    public String getRes_name(){
        return "D";
    }
    
    public void countPrice(String file_name) {
        int base_price = 5;
        this.prev_price = this.now_price;
        this.now_price = base_price*CountFCL.countCoefPrice(file_name, super.getRes_names(), 
                        super.getParam_names(), this.getValues());        
    }   

    public void setPrev_price(double prev_price) {
        this.prev_price = prev_price;
    }

    public void setNow_price(double now_price) {
        this.now_price = now_price;
    }  
    
    public double getPrev_price() {
        return prev_price;
    }

    public double getNow_price() {
        return now_price;
    }    
    
    public double countDifPrice(){
        return (this.prev_price-this.now_price)/this.now_price;
    }
    
    public ArrayList<Object> getValues(){
        ArrayList<Object> values = new ArrayList<>();
        values.add(this.getFame());
        values.add(this.getSignificance());
        values.add(this.getRarity());
        return values;
    }

    public String getLot_name() {
        return lot_name;
    }

    public void setLot_name(String lot_name) {
        this.lot_name = lot_name;
    }
    
    public int getFame() {
        return fame;
    }

    public void setFame(int fame) {
        this.fame = fame;
    }

    public int getSignificance() {
        return significance;
    }

    public void setSignificance(int significance) {
        this.significance = significance;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }
    
}
