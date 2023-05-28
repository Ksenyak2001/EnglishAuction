/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi2023.english_auction;

import java.util.ArrayList;

/**
 *
 * @author Kseny
 */
public class Person {

    public double getExcitement() {
        return excitement;
    }

    public void setExcitement(double excitement) {
        this.excitement = excitement;
    }

    public double getLack_of_confidence() {
        return lack_of_confidence;
    }

    public void setLack_of_confidence(double lack_of_confidence) {
        this.lack_of_confidence = lack_of_confidence;
    }

    public int getNeed_one_lot() {
        return need_one_lot;
    }

    public void setNeed_one_lot(int need_one_lot) {
        this.need_one_lot = need_one_lot;
    }

    public double getFear_of_poverty() {
        return fear_of_poverty;
    }

    public void setFear_of_poverty(double fear_of_poverty) {
        this.fear_of_poverty = fear_of_poverty;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    public void countNeed_one_lot(int lot_id) {
        this.need_one_lot = (int) this.need_product.get(lot_id);
    }
    
    
    private String name;
    private int welfare;
    private ArrayList<Object> need_product;
    private int need_one_lot;
    private int stinginess;
    private int player_type;
    private int self_confidence;
    private int risk_appetite;
    private int acceptability;
    private double fear_of_poverty;
    private double lack_of_confidence;
    private double excitement;
    ArrayList<String> names;
    
    public Person(){        
        this.init();
    }
    
    public Person(int welfare, String name, ArrayList<Object> need_product, int stinginess, int player_type, int self_confidence, int risk_appetite) {
        this.name = name;
        this.welfare = welfare;
        this.need_product = need_product;
        this.stinginess = stinginess;
        this.player_type = player_type;
        this.self_confidence = self_confidence;
        this.risk_appetite = risk_appetite;
        this.acceptability = 0; 
        this.init();
    }
    
    private void init(){
        this.acceptability = 0; 
        this.fear_of_poverty = 0.0;
        names = new ArrayList<>();
        names.add("welfare");
        names.add("need_product");
        names.add("stinginess");
        names.add("player_type");
        names.add("self_confidence");
        names.add("risk_appetite");
        
    }

    public int getAcceptability() {
        return acceptability;
    }

    public void setAcceptability(int acceptability) {
        this.acceptability = acceptability;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public int getWelfare() {
        return welfare;
    }

    public void setWelfare(int welfare) {
        this.welfare = welfare;
    }

    public ArrayList<Object> getNeed_product() {
        return need_product;
    }

    public void setNeed_product(ArrayList<Object> need_product) {
        this.need_product = need_product;
    }

    public int getStinginess() {
        return stinginess;
    }

    public void setStinginess(int stinginess) {
        this.stinginess = stinginess;
    }

    public int getPlayer_type() {
        return player_type;
    }

    public void setPlayer_type(int player_type) {
        this.player_type = player_type;
    }

    public int getSelf_confidence() {
        return self_confidence;
    }

    public void setSelf_confidence(int self_confidence) {
        this.self_confidence = self_confidence;
    }

    public int getRisk_appetite() {
        return risk_appetite;
    }

    public void setRisk_appetite(int risk_appetite) {
        this.risk_appetite = risk_appetite;
    }
    
    public ArrayList<String> getNameParameters(){
        return names;
    }
}
