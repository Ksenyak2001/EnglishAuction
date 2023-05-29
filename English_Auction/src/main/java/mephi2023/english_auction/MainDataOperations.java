/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi2023.english_auction;

import java.util.ArrayList;
import mephi2023.english_auction.lot.Lot;

/**
 *
 * @author Kseny
 */
public class MainDataOperations {

    public static ArrayList<Lot> getLots() {
        return lots;
    }


    public static ArrayList<Person> getPersons() {
        return persons;
    }
    
    public static void initLots(ArrayList<ArrayList<Object>> ar3, String file_name){
        lots = new ArrayList<>();
        for (int i = 0; i < ar3.get(0).size(); i++){
            Lot lot = new Lot();
            lot.setLot_name((String)ar3.get(0).get(i));
            lot.setFame(Integer.parseInt((String)ar3.get(1).get(i)));
            lot.setSignificance(Integer.parseInt((String)ar3.get(2).get(i)));
            lot.setRarity(Integer.parseInt((String)ar3.get(3).get(i)));            
            lots.add(lot);
            CountingForLot.countPrice(file_name, lot);
        }
    }
    
    public static void initPersons(ArrayList<ArrayList<Object>> ar1, ArrayList<ArrayList<Object>> ar2){
        persons = new ArrayList<>();
        for (int i = 0; i < ar1.get(0).size(); i++){
            Person p = new Person();
            p.setName((String)ar1.get(0).get(i));
            p.setWelfare(Integer.parseInt((String)ar1.get(2).get(i)));
            p.setStinginess(Integer.parseInt((String)ar1.get(3).get(i)));
            p.setPlayer_type(Integer.parseInt((String)ar1.get(1).get(i)));
            p.setSelf_confidence(Integer.parseInt((String)ar1.get(5).get(i)));
            p.setRisk_appetite(Integer.parseInt((String)ar1.get(4).get(i)));
            p.setNeed_product(ar2.get(i+1));            
            persons.add(p);
        }
    }
    
    private static ArrayList<Lot> lots;
    private static ArrayList<Person> persons; 
    private static ArrayList<Person> participants;
    private static boolean auto;  
    private static int lot_id; 
    private static int count_pass;

    public static ArrayList<Person> getParticipants() {
        return participants;
    }

    public static void setParticipants(ArrayList<Person> participants) {
        MainDataOperations.participants = participants;
    }

    public static int getLot_id() {
        return lot_id;
    }

    public static void setLot_id(int lot_id) {
        MainDataOperations.lot_id = lot_id;
    }

    public static int getCount_pass() {
        return count_pass;
    }

    public static void setCount_pass(int count_pass) {
        MainDataOperations.count_pass = count_pass;
    }

    public static void addCount_pass() {
        MainDataOperations.count_pass += 1;
    }    
    public static void addCount_pass(int count_pass) {
        MainDataOperations.count_pass += count_pass;
    }    
    
    public static boolean isAuto() {
        return auto;
    }

    public static void setAuto(boolean auto) {
        MainDataOperations.auto = auto;
    }
}
