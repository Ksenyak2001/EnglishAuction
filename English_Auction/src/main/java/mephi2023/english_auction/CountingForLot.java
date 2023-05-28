/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi2023.english_auction;

import java.util.logging.Level;
import java.util.logging.Logger;
import mephi2023.english_auction.lot.Lot;

/**
 *
 * @author Kseny
 */
public class CountingForLot {
    public static void countPrice(String file_name, Lot lot) {
        int base_price = 2;
        lot.setPrev_price(lot.getNow_price());
        double temp_price = base_price*(CountFCL.countCoef(file_name, 
                lot.getRes_name(), lot.getParam_names(), lot.getValues())+1);
        lot.setNow_price((int) Math.round(temp_price));    
        try {
            CountFCL.checkCount(temp_price, "countPrice");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(CountingForLot.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
