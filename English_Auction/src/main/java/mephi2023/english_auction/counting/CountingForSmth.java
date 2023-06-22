/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi2023.english_auction.counting;

import java.util.ArrayList;

/**
 *
 * @author Kseny
 */
public class CountingForSmth {
    protected static ArrayList<String> res_params;
    protected static ArrayList<Object> values;
    protected static String res_name;
    protected static int min_value;
    protected static int max_value;
    protected static int count;
    
    protected static double doRightView(double value){
        return (value*(max_value - min_value)/count + min_value);
    }
}
