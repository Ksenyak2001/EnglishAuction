/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi2023.english_auction.counting;

import java.util.ArrayList;
import mephi2023.english_auction.work_with_files.DataReader;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

/**
 *
 * @author Kseny
 */
public class CountFCL {
    /*private static FIS ReadFCL(String fileName){
        FIS fis = FIS.load(fileName,true);
        if( fis == null ) { 
            System.err.println("Can't load file: '" + fileName + "'");
            return null;
        }
        return fis;
    }*/
    
    public static double countCoef(String file_name, String res_name, 
            ArrayList<String> param_names, ArrayList<Object> values){
        FIS fis = DataReader.ReadFCL(file_name);      
        System.out.print(" { ");
        for (int j = 0; j < param_names.size(); j++) {
            System.out.print(values.get(j) + " ");
            fis.setVariable(param_names.get(j), Double.parseDouble(String.valueOf(values.get(j))));
        }
        fis.evaluate();   
        System.out.print("} => ");
        System.out.println(fis.getVariable(res_name).getValue()); 
        return fis.getVariable(res_name).getValue();
    }
    
    public static void checkCount(double value, String name) throws Exception{
        if (value == (-2.0)){
            throw new Exception("WRONG VALUE IN " + name);
        }
    }
    /*
    public static void draw(FIS fis){
        JFuzzyChart.get().chart(fis);
        // Set inputs
        fis.setVariable("rarity", 3);
        fis.setVariable("fame", 4);
        fis.setVariable("significance", 6);
        // Evaluate
        fis.evaluate();
        // Show output variable's chart
        //Variable price_d = fis.getVariable("price_d");
        //JFuzzyChart.get().chart(price_d, price_d.getDefuzzifier(), true);

        // Print ruleSet
        //System.out.println(fis);
        System.out.println("Output value:" + fis.getVariable("D").getValue());
        System.out.println("Output value:" + fis.getVariable("coef_rarity").getValue());
        System.out.println("Output value:" + fis.getVariable("coef_fame").getValue());
        System.out.println("Output value:" + fis.getVariable("coef_significance").getValue());
    
    }
    
    public static void draw2(FIS fis){
        JFuzzyChart.get().chart(fis);
        fis.setVariable("welfare", 3);
        fis.setVariable("fame", 4);
        fis.setVariable("dif", -0.75);
        fis.evaluate();
        System.out.println("Output value:" + fis.getVariable("acceptability").getValue());    
    }*/
    
/*    public static double countCoefD(String file_name, String res_name, 
            ArrayList<String> param_names, ArrayList<Object> values){
        FIS fis = ReadFCL(file_name);       
        for (int j = 0; j < param_names.size(); j++) {
            fis.setVariable(param_names.get(j), Integer.parseInt(String.valueOf(values.get(j))));
        }
        fis.evaluate();
        System.out.println(fis.getVariable(res_name).getValue()); 
        return fis.getVariable(res_name).getValue();
    }*/
    /*public static double countCoefPrice(String file_name, ArrayList<String> res_names, 
            ArrayList<String> param_names, ArrayList<Object> values){
        FIS fis = ReadFCL(file_name);       
        for (int j = 0; j < param_names.size(); j++) {
            fis.setVariable(param_names.get(j), Integer.parseInt(String.valueOf(values.get(j))));
        }
        fis.evaluate();
        double res_coef = 1;
        for (String n : res_names){
            res_coef *= fis.getVariable(n).getValue();
        }
        System.out.println(res_coef); 
        return res_coef;
    }
    

    public static ArrayList<Double> countByCoef(FIS fis, double base, ArrayList<String> res_names, ArrayList<String> names, ArrayList<ArrayList<Object>> values){
        System.out.println("###");
        ArrayList<Double> res = new ArrayList<>();
        for (int i = 0; i < values.get(0).size(); i++){
            for (int j = 0; j < names.size(); j++) {
                //System.out.println(values.get(j).get(i));
                fis.setVariable(names.get(j), Integer.parseInt((String)values.get(j).get(i)));
            }
            fis.evaluate();
            double res_coef = 1;
            for (String n : res_names){
                res_coef *= fis.getVariable(n).getValue();
                System.out.print(fis.getVariable(n).getValue() + " ");
            }
            System.out.println(res_coef);
            res.add(base*res_coef);
        }
        System.out.println("###");
        return res;
    }
    
    public static ArrayList<Double> count(FIS fis, String res_name, ArrayList<String> names, ArrayList<ArrayList<Object>> values){
        System.out.println("***");
        ArrayList<Double> res = new ArrayList<>();
        for (int i = 0; i < values.get(0).size(); i++){
            for (int j = 0; j < names.size(); j++) {
                //System.out.println(values.get(j).get(i));
                fis.setVariable(names.get(j), Integer.parseInt((String)values.get(j).get(i)));
            }
            fis.evaluate();
            res.add(fis.getVariable(res_name).getValue());
        }
        System.out.println("***");
        return res;
    }
    */
}
