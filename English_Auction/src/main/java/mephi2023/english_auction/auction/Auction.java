/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi2023.english_auction.auction;

import mephi2023.english_auction.person.Person;

/**
 *
 * @author Kseny
 */
public class Auction {
    
    private static double prev_price;
    private static double zero_price;
    private static double avg_grow_prev_price;
    private static double avg_grow_prev2_price;
    private static int count_participants;
    private static int count_temp_rates;

    private static Person leader;
    
    
    public static double getActivity() {
        if (avg_grow_prev2_price == 0){
            return 0;
        }
        double delta = (avg_grow_prev_price-avg_grow_prev2_price)/avg_grow_prev2_price;
        return (Math.exp(delta)/(1+Math.exp(delta)));
    }
        
    public static double getAuction_growth() {
        if (prev_price == 0){
            return -1;
        }
        return (prev_price-zero_price)/prev_price;
    }
        
    
    public static Person getLeader() {
        return leader;
    }

    public static void setLeader(Person leader) {
        Auction.leader = leader;
    }

    public static int getCount_participants() {
        return count_participants;
    }

    public static void setCount_participants(int count_participants) {
        Auction.count_participants = count_participants;
    }

    public static double getPrev_price() {
        return prev_price;
    }

    public static void setPrev_price(double prev_price) {
        Auction.prev_price = prev_price;
    }

    public static double getZero_price() {
        return zero_price;
    }

    public static void setZero_price(double zero_price) {
        Auction.zero_price = zero_price;
    }

    public static double getAvg_grow_prev_price() {
        return avg_grow_prev_price;
    }

    public static void setAvg_grow_prev_price(double avg_grow_prev_price) {
        Auction.avg_grow_prev_price = avg_grow_prev_price;
    }

    public static double getAvg_grow_prev2_price() {
        return avg_grow_prev2_price;
    }

    public static void setAvg_grow_prev2_price(double avg_grow_prev2_price) {
        Auction.avg_grow_prev2_price = avg_grow_prev2_price;
    }
    
    public static int getCount_temp_rates() {
        return count_temp_rates;
    }

    public static void setCount_temp_rates(int count_temp_rates) {
        Auction.count_temp_rates = count_temp_rates;
    }
    
    public static void addCount_temp_rates() {
        Auction.count_temp_rates++;
    }
    
    public static void init() {
        prev_price = 0;
        zero_price = 0;
        count_temp_rates = 0;
        avg_grow_prev_price = 0;
        avg_grow_prev2_price = 0;
        count_participants = -1;
        leader = null;
    }
    public static void init(double price) {
        init();
        zero_price = price;
    }
}
