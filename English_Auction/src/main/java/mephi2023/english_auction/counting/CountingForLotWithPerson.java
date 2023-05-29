/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi2023.english_auction.counting;

import mephi2023.english_auction.counting.CountFCL;
import java.util.ArrayList;
import mephi2023.english_auction.auction.Auction;
import mephi2023.english_auction.MainDataOperations;
import mephi2023.english_auction.person.Person;
import mephi2023.english_auction.lot.Lot;

/**
 *
 * @author Kseny
 */
public class CountingForLotWithPerson {
    private static ArrayList<String> res_params;
    private static ArrayList<Object> values;
    private static String res_name;
    private static int min_value;
    private static int max_value;
    private static int count;
    
    private static double doRightView(double value){
        return (value*(max_value - min_value)/count + min_value);
    }
    
    private static void initAcceptabilityPrice(Lot lot, Person person){
        res_params = new ArrayList<>();
        res_params.add("welfare");
        res_params.add("fame");
        res_params.add("dif");
        res_name = "acceptability";
        values = new ArrayList<>();
        values.add(person.getWelfare());
        values.add(lot.getFame());
        values.add(lot.countDifPrice());
    }
    public static void countAcceptabilityPrice(String file_name, Lot lot, Person person) {
        initAcceptabilityPrice(lot, person);
        lot.countDifPrice();
        double temp_acceptability = CountFCL.countCoef(file_name, res_name, res_params, values);
        person.setAcceptability(temp_acceptability);   
        try {
            CountFCL.checkCount(temp_acceptability, "countAcceptabilityPrice");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(CountingForLot.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    public static void countNeedLot(Lot lot, Person person) {
        int lot_id = MainDataOperations.getLot_id();
        person.countNeed_one_lot(lot_id);   
    } 
    
    private static void initLackOfConfidence(Lot lot, Person person){
        res_params = new ArrayList<>();
        res_params.add("acceptability");
        res_params.add("significance");
        res_params.add("fame");
        res_params.add("type");
        res_name = "lack_of_confidence";
        values = new ArrayList<>();
        values.add(person.getAcceptability());
        values.add(lot.getSignificance());
        values.add(lot.getFame());
        values.add(person.getPlayer_type());
        min_value = -1;
        max_value = 1;
        count = 10;
    }
    public static void countLackOfConfidence(String file_name, Lot lot, Person person) {
        initLackOfConfidence(lot, person);
        //System.out.println(lot.getPrev_price() + " ; " + lot.getNow_price());
        double temp_lack_of_confidence = person.getLack_of_confidence(); 
        double tloc = CountFCL.countCoef(file_name, res_name, res_params, values);
        temp_lack_of_confidence += doRightView(tloc);
        person.setLack_of_confidence(temp_lack_of_confidence); 
        
        try {
            CountFCL.checkCount(tloc, "countLackOfConfidence");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private static void initAssurance(Lot lot, Person person){
        res_params = new ArrayList<>();
        res_params.add("welfare");
        res_params.add("self_confidence");
        res_params.add("activity");
        res_params.add("auction_growth");
        res_params.add("dif");
        res_name = "assurance";
        values = new ArrayList<>();
        values.add(person.getWelfare());
        values.add(person.getSelf_confidence());
        values.add(Auction.getActivity());
        values.add(Auction.getAuction_growth());
        values.add(lot.countDifPrice());        
        min_value = -1;
        max_value = 1;
        count = 10;
    }
    public static void countAssurance(String file_name, Lot lot, Person person) {
        initAssurance(lot, person);
        double temp_assurance = person.getAssurance();   
        double ta = CountFCL.countCoef(file_name, res_name, res_params, values);     
        //temp_assurance += doRightView(ta);
        temp_assurance += ta;
        person.setAssurance(temp_assurance);    
        
        try {
            CountFCL.checkCount(ta, "countAssurance");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }  
}
