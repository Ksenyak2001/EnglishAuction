/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi2023.english_auction.person;

import java.util.ArrayList;

/**
 *
 * @author Kseny
 */
public class Person {
    private String name;
    private double welfare;
    private ArrayList<Object> need_product;
    private double stinginess;
    private double player_type;
    private double self_confidence;
    private double risk_appetite;
    
    private double acceptability;
    private double fear_of_poverty;
    private double lack_of_confidence;
    private double excitement;
    private double assurance;
    private double fear_of_loss;
    private int need_one_lot;
    private double rate_of_price_increase;
    private boolean flag_leader;
    
    private boolean flag_take_part_temp;
    private int count_take_part;
    private double temp_max_price;
    private ArrayList<Double> temp_rise_prices;
    private ArrayList<Double> all_rise_prices;
    private double temp_max_rise_price;
    private double sum_max_prices;
    private int count_take_lose_part;
    private int count_win;
    private int count_temp_rates;

    
    public Person(){        
        this.init();
    }
    
    public Person(double welfare, String name, ArrayList<Object> need_product, double stinginess, 
            double player_type, double self_confidence, double risk_appetite) {
        this.name = name;
        this.welfare = welfare;
        this.need_product = need_product;
        this.stinginess = stinginess;
        this.player_type = player_type;
        this.self_confidence = self_confidence;
        this.risk_appetite = risk_appetite;
        
        this.init();
    }
    
    private void init(){
        this.acceptability = 0; 
        this.fear_of_poverty = 0; 
        this.lack_of_confidence = 0; 
        this.excitement = 0; 
        this.assurance = 0; 
        this.fear_of_loss = 0; 
        this.need_one_lot = 0;  
        this.flag_leader = false;
        
        
        flag_take_part_temp = false;
        count_take_part = 0;
        temp_max_price = 0;
        temp_max_rise_price = 0;
        sum_max_prices = 0;
        count_take_lose_part = 0;
        count_win = 0;
        count_temp_rates = 0;
        temp_rise_prices = new ArrayList<>();
        all_rise_prices = new ArrayList<>();
        
    }

    
    
    public ArrayList<Double> getTemp_rise_prices() {
        return temp_rise_prices;
    }

    public void setTemp_rise_prices(ArrayList<Double> temp_rise_prices) {
        this.temp_rise_prices = temp_rise_prices;
    }
    public void addTemp_rise_prices(double temp_rise_price) {
        this.temp_rise_prices.add(temp_rise_price);
    }
    
    public ArrayList<Double> getAll_rise_prices() {
        return all_rise_prices;
    }

    public void setAll_rise_prices(ArrayList<Double> all_rise_prices) {
        this.all_rise_prices = all_rise_prices;
    }
    public void addall_rise_prices(double all_rise_price) {
        this.all_rise_prices.add(all_rise_price);
    }
    
    
    public int getCount_temp_rates() {
        return count_temp_rates;
    }

    public void setCount_temp_rates(int count_temp_rates) {
        this.count_temp_rates = count_temp_rates;
    }

    public void addCount_temp_rates() {
        this.count_temp_rates++;
    }    
    
    public boolean isFlag_take_part_temp() {
        return flag_take_part_temp;
    }

    public void setFlag_take_part_temp(boolean flag_take_part_temp) {
        this.flag_take_part_temp = flag_take_part_temp;
    }

    public int getCount_take_part() {
        return count_take_part;
    }

    public void addCount_take_part() {
        this.count_take_part++;
    }

    public void setCount_take_part(int count_take_part) {
        this.count_take_part = count_take_part;
    }

    public double getTemp_max_price() {
        return temp_max_price;
    }

    public void setTemp_max_price(double temp_max_price) {
        this.temp_max_price = temp_max_price;
    }

    public double getTemp_max_rise_price() {
        return temp_max_rise_price;
    }

    public void setTemp_max_rise_price(double temp_max_rise_price) {
        this.temp_max_rise_price = temp_max_rise_price;
    }

    public double getSum_max_prices() {
        return sum_max_prices;
    }

    public void setSum_max_prices(double sum_max_prices) {
        this.sum_max_prices = sum_max_prices;
    }
    public void addSum_max_prices(double sum_max_prices) {
        this.sum_max_prices += sum_max_prices;
    }

    public int getCount_take_lose_part() {
        return count_take_lose_part;
    }

    public void setCount_take_lose_part(int count_take_lose_part) {
        this.count_take_lose_part = count_take_lose_part;
    }

    public void addCount_take_lose_part() {
        this.count_take_lose_part++;
    }

    public int getCount_win() {
        return count_win;
    }

    public void setCount_win(int count_win) {
        this.count_win = count_win;
    }
    
    public void addCount_win() {
        this.count_win++;
    }
    
    public double getAcceptability() {
        return acceptability;
    }

    public void setAcceptability(double acceptability) {
        this.acceptability = acceptability;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public double getWelfare() {
        return welfare;
    }

    public void setWelfare(double welfare) {
        this.welfare = welfare;
    }

    public ArrayList<Object> getNeed_product() {
        return need_product;
    }

    public void setNeed_product(ArrayList<Object> need_product) {
        this.need_product = new ArrayList<>();
        this.need_product.addAll(need_product);
    }

    public double getStinginess() {
        return stinginess;
    }

    public void setStinginess(double stinginess) {
        this.stinginess = stinginess;
    }

    public double getPlayer_type() {
        return player_type;
    }

    public void setPlayer_type(double player_type) {
        this.player_type = player_type;
    }

    public double getSelf_confidence() {
        return self_confidence;
    }

    public void setSelf_confidence(double self_confidence) {
        this.self_confidence = self_confidence;
    }

    public double getRisk_appetite() {
        return risk_appetite;
    }

    public void setRisk_appetite(double risk_appetite) {
        this.risk_appetite = risk_appetite;
    }
    
    public boolean isFlag_leader() {
        return flag_leader;
    }

    public void setFlag_leader(boolean flag_leader) {
        this.flag_leader = flag_leader;
    }

    public double getRate_of_price_increase() {
        return rate_of_price_increase;
    }

    public void setRate_of_price_increase(double rate_of_price_increase) {
        this.rate_of_price_increase = rate_of_price_increase;
    }

    public double getFear_of_loss() {
        return fear_of_loss;
    }

    public void setFear_of_loss(double fear_of_loss) {
        this.fear_of_loss = fear_of_loss;
    }

    public double getAssurance() {
        return assurance;
    }

    public void setAssurance(double assurance) {
        this.assurance = assurance;
    }

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

    public void countNeed_one_lot(int lot_id) {
        //System.out.println(this.need_product);
        //System.out.println("* " + lot_id + " ; " + this.need_product.get(lot_id) + " *");
        this.need_one_lot = Integer.parseInt((String) this.need_product.get(lot_id));
    }    
}
