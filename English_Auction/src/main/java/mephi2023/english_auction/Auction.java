/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi2023.english_auction;

/**
 *
 * @author Kseny
 */
public class Auction {

    public static double getActivity() {
        if (avg_grow_prev2_price == 0){
            return 0;
        }
        return (avg_grow_prev_price-avg_grow_prev2_price)/avg_grow_prev2_price;
    }
    
    public static double getAuction_growth() {
        return (prev_price-zero_price)/zero_price;
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
    
    public static void init() {
        prev_price = 0;
        zero_price = 0;
        avg_grow_prev_price = 0;
        avg_grow_prev2_price = 0;
    }
    public static void init(double price) {
        init();
        zero_price = price;
    }
    private static double prev_price;
    private static double zero_price;
    private static double avg_grow_prev_price;
    private static double avg_grow_prev2_price;
}
