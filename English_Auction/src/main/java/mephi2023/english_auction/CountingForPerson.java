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
public class CountingForPerson {
    private static ArrayList<String> res_params;
    private static ArrayList<Object> values;
    private static String res_name;
    private static int min_value;
    private static int max_value;
    private static int count;
    
    private static double doRightView(double value){
        return (value*(max_value - min_value)/count + min_value);
    }
    
    private static void init_fear_of_poverty(Person person){
        res_params = new ArrayList<>();
        res_params.add("stinginess");
        res_params.add("acceptability");
        res_params.add("welfare");
        res_name = "fear_of_poverty";
        values = new ArrayList<>();
        values.add(person.getStinginess());
        values.add(person.getAcceptability());
        values.add(person.getWelfare());
        min_value = -1;
        max_value = 1;
        count = 10;
    }
    public static void countFear_of_poverty(String file_name, Person person) {
        double temp_fear_of_poverty = person.getFear_of_poverty();
        init_fear_of_poverty(person);
        temp_fear_of_poverty += doRightView(CountFCL.countCoef(file_name, res_name, res_params, values));
        person.setFear_of_poverty(temp_fear_of_poverty);   
          
    } 
    
    private static void init_excitement(Person person){
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
        init_excitement(person);
        temp_excitement += doRightView(CountFCL.countCoef(file_name, res_name, res_params, values));
        person.setExcitement(temp_excitement);       
    } 
}
