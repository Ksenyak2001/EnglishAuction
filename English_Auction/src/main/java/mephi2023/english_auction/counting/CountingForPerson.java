/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi2023.english_auction.counting;

import java.util.ArrayList;
import mephi2023.english_auction.auction.Auction;
import mephi2023.english_auction.person.Person;

/**
 *
 * @author Kseny
 */
public class CountingForPerson extends CountingForSmth{
    
    private static double returnTrueValue(double value){
        if (value < 0){
            return 0;
        } else if (value > 10) {
            return 10;
        } else {
            return value;
        }
    }
    
    private static void initFear_of_poverty(Person person){
        res_params = new ArrayList<>();
        res_params.add("stinginess");
        res_params.add("acceptability");
        res_params.add("welfare");
        res_name = "fear_of_poverty";
        values = new ArrayList<>();
        values.add(person.getStinginess());
        values.add(person.getAcceptability());
        values.add(person.getWelfare());
        min_value = 0;
        max_value = 10;
        count = 10;
    }
    public static void countFear_of_poverty(String file_name, Person person) {
        double temp_fear_of_poverty = person.getFear_of_poverty();
        initFear_of_poverty(person);
        double tfop = CountFCL.countCoef(file_name, res_name, res_params, values);
        temp_fear_of_poverty += doRightView(tfop);
        //temp_fear_of_poverty += tfop;
        person.setFear_of_poverty(temp_fear_of_poverty);   
        
        try {
            CountFCL.checkCount(tfop, "countFear_of_poverty");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    } 
    
    private static void initExcitement(Person person){
        res_params = new ArrayList<>();
        res_params.add("risk_appetite");
        res_params.add("auction_growth");
        res_params.add("activity");
        res_params.add("type");
        res_name = "excitement";
        values = new ArrayList<>();
        values.add(person.getRisk_appetite());
        values.add(Auction.getAuction_growth());
        values.add(Auction.getActivity());
        values.add(person.getPlayer_type());
        min_value = -1;
        max_value = 1;
        count = 10;
    }
    public static void countExcitement(String file_name, Person person) {
        double temp_excitement = person.getExcitement();
        initExcitement(person);
        double te = CountFCL.countCoef(file_name, res_name, res_params, values);
        temp_excitement += doRightView(te);
        person.setExcitement(temp_excitement);  
        
        try {
            CountFCL.checkCount(te, "countExcitement");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    } 
    
    private static void initFear_of_loss(Person person){
        res_params = new ArrayList<>();
        res_params.add("need_product");
        res_params.add("self_confidence");
        res_params.add("activity");
        res_params.add("auction_growth");
        res_params.add("type");
        res_name = "fear_of_loss";
        values = new ArrayList<>();
        values.add(person.getNeed_one_lot());
        values.add(person.getSelf_confidence());
        values.add(Auction.getActivity());
        values.add(Auction.getAuction_growth());
        values.add(person.getPlayer_type());        
        min_value = -1;
        max_value = 1;
        count = 10;
    }
    public static void countFear_of_loss(String file_name, Person person) {
        double temp_fear_of_loss = person.getFear_of_loss();
        initFear_of_loss(person);
        double tfol = CountFCL.countCoef(file_name, res_name, res_params, values);
        temp_fear_of_loss += doRightView(tfol);
        person.setFear_of_loss(temp_fear_of_loss);  
        
        try {
            CountFCL.checkCount(tfol, "countFear_of_loss");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    private static void initRate_of_price_increase(Person person){
        res_params = new ArrayList<>();
        res_params.add("excitement");
        res_params.add("fear_of_loss");
        res_name = "rate_of_price_increase";
        values = new ArrayList<>();
        values.add(returnTrueValue(person.getExcitement()));
        values.add(returnTrueValue(person.getFear_of_loss()));
        //min_value = 1;
        //max_value = 2;
        min_value = 0;
        max_value = 1;
        count = 10;
    }
    public static void countRate_of_price_increase(String file_name, Person person) {
        initRate_of_price_increase(person);
        double rate_of_price_increase = CountFCL.countCoef(file_name, res_name, res_params, values);
        
        try {
            CountFCL.checkCount(rate_of_price_increase, "countRate_of_price_increase");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        person.setRate_of_price_increase(doRightView(rate_of_price_increase));  
        
    } 
}
