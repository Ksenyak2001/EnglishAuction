/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package mephi2023.english_auction;

import mephi2023.english_auction.lot.Lot;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author Kseny
 */
public class English_Auction {

    public static void main(String[] args) {
        String fileName = ".\\resources\\Вар6_приложение1.xlsx";
        
        //ЗАГРУЗКА ПАРАМЕТРОВ
        ArrayList<ArrayList<Object>> ar1 = null, ar2= null, ar3= null;
        try {            
            ar1 = DataReader.ReadXLSX(fileName, 0, 1);
            ar2 = DataReader.ReadXLSX(fileName, 1, 1);
            ar3 = DataReader.ReadXLSX(fileName,2, 2);
        } catch (IOException | InvalidFormatException ex) {
            Logger.getLogger(English_Auction.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(ar1.size() + "x" + ar1.get(0).size());
        System.out.println(ar2.size() + "x" + ar2.get(0).size());
        System.out.println(ar3.size() + "x" + ar3.get(0).size());
        /*for (Object o : ar1.get(0)){
            System.out.println(o);
        }*/
        // 1 : имя/спекулянт/деньги/скупость/риск/самоуверенность
        // 2 : лот/чел - необх. в товаре
        // 3 : лот/древность/состояние/редкость : 0/2/4/6
        ar3.remove(5);
        ar3.remove(3);
        ar3.remove(1);
        ArrayList<String> namesLots = (ArrayList<String>) ar3.get(0).clone();
        //ar3.remove(0);
        /*for (ArrayList<Object> ar : ar3){
            System.out.println(ar.get(0));
        }*/
        ArrayList<String> namesMoneyParam = new ArrayList<>();
        namesMoneyParam.add("fame");
        namesMoneyParam.add("significance");
        namesMoneyParam.add("rarity");
        
        //CountFCL.draw(DataReader.ReadFCL(".\\resources\\fcl\\count_coef.fcl"));
        /*ArrayList<Double> r = CountFCL.count(DataReader.ReadFCL(".\\resources\\fcl\\count_supposed_price.fcl"), "price_d", namesMoneyParam, ar3);
        System.out.println(r.size());
        for (double d : r){
            if (d > 5.0){
                System.out.println(d);
            }
        }*/
        CountFCL.draw(DataReader.ReadFCL(".\\resources\\fcl\\count_price.fcl"));
        CountFCL.draw2(DataReader.ReadFCL(".\\resources\\fcl\\count_acceptability.fcl"));
        
        ArrayList<String> res_names = new ArrayList<>();
        res_names.add("coef_fame");
        res_names.add("coef_significance");
        res_names.add("coef_rarity");
        double base = 5.0 ;
        //ArrayList<Double> r2 = CountFCL.countByCoef(DataReader.ReadFCL(".\\resources\\fcl\\count_coef.fcl"), base, res_names, namesMoneyParam, ar3);
        //System.out.println(r2.size());
        //for (int i =0; i < r2.size(); i++){
            /*if (r2.get(i) > base){
                System.out.println(r2.get(i) + " - " + i);
            }*/
            //System.out.println(r2.get(i));
        //}
        
        ArrayList<Person> persons = initPerson(ar1, ar2);
        //РАСЧЁТ ПРЕДПОЛАГАЕМОЙ ЦЕНЫ
        ArrayList<Lot> lots = initLot(ar3, ".\\resources\\fcl\\count_price.fcl");
        
        //НАЧАЛО ЦИКЛА
        
        //РАСЧЁТ ПРИЕМЛИМОСТИ ЦЕНЫ
        Auction.init(lots.get(0).getNow_price());
        for (Person p : persons){
            CountingForLotWithPerson.countAcceptabilityPrice(".\\resources\\fcl\\count_acceptability.fcl", lots.get(0), p);
            System.out.println("=====");
        }
        
        //БЛОК РАСЧЁТА ЭМОЦИЙ
        System.out.println("?:?:?");
        Auction.init(lots.get(0).getNow_price());
        for (Person p : persons){
            //System.out.println(lots.get(0).getPrev_price());
            CountingForPerson.countFear_of_poverty(".\\resources\\fcl\\count_fear_of_poverty.fcl", p);
            CountingForLotWithPerson.countLackOfConfidence(".\\resources\\fcl\\count_lack_of_confidence.fcl", lots.get(0), p);
            CountingForPerson.countExcitement(".\\resources\\fcl\\count_excitement.fcl",  p);
            System.out.println("=====");
        }
        //System.out.println(ar3.get(0));
        //count_lack_of_confidence.fcl
        
        //РЕШЕНИЕ ОБ АКТИВНОМ УЧАСТИИ
        //РЕШЕНИЕ ЗАДАЧИ РАСЧЁТА ПОВЫШЕНИЯ ЦЕН
        //ОПРЕДЕЛЕНИЕ ПОБЕДИТЕЛЯ
        //БЛОК РАСЧЁТА ЭФФЕКТОВ АУКЦИОНА
        
        //КОНЕЦ ЦИКЛА
    }
    /*
        names.add("welfare");
        names.add("need_product");
        names.add("stinginess");
        names.add("player_type");
        names.add("self_confidence");
        names.add("risk_appetite");
    */
    private static ArrayList<Lot> initLot(ArrayList<ArrayList<Object>> ar3, String file_name){
        ArrayList<Lot> lots = new ArrayList<>();
        for (int i = 0; i < ar3.get(0).size(); i++){
            Lot lot = new Lot(file_name);
            lot.setLot_name((String)ar3.get(0).get(i));
            lot.setFame(Integer.parseInt((String)ar3.get(1).get(i)));
            lot.setSignificance(Integer.parseInt((String)ar3.get(2).get(i)));
            lot.setRarity(Integer.parseInt((String)ar3.get(3).get(i)));            
            lots.add(lot);
            //lot.countPrice(file_name);
            CountingForLot.countPrice(file_name, lot);
            System.out.println("&&&" + lot.getNow_price() + "&" + lot.getPrev_price());
        }
        System.out.println(lots.size());
        return lots;
    }
    
    private static ArrayList<Person> initPerson(ArrayList<ArrayList<Object>> ar1, ArrayList<ArrayList<Object>> ar2){
        ArrayList<Person> persons = new ArrayList<>();
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
        System.out.println(persons.size());
        return persons;
    }
}
