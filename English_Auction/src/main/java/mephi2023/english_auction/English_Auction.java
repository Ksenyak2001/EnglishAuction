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
        DataManipulation dm = new DataManipulation();
        dm.modelingAuction(true, 0);
    }
        /*String fileName = ".\\resources\\Вар6_приложение1.xlsx";
        
        //ЗАГРУЗКА ПАРАМЕТРОВ
        ArrayList<ArrayList<Object>> ar1 = null, ar2= null, ar3= null;
        try {            
            ar1 = DataReader.ReadXLSX(fileName, 0, 1);
            ar2 = DataReader.ReadXLSX(fileName, 1, 1);
            ar3 = DataReader.ReadXLSX(fileName,2, 2);
        } catch (IOException | InvalidFormatException ex) {
            Logger.getLogger(English_Auction.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 1 : имя/спекулянт/деньги/скупость/риск/самоуверенность
        // 2 : лот/чел - необх. в товаре
        // 3 : лот/древность/состояние/редкость : 0/2/4/6
        ar3.remove(5);
        ar3.remove(3);
        ar3.remove(1);
        
        ArrayList<Person> persons = initPerson(ar1, ar2);
        int n = persons.size();
        //РАСЧЁТ ПРЕДПОЛАГАЕМОЙ ЦЕНЫ
        ArrayList<Lot> lots = initLot(ar3, ".\\resources\\fcl\\count_price.fcl");
        
        //НАЧАЛО ЦИКЛА
        int lot_id = 0, count_pass = 0;
        //lots.get(lot_id).getNow_price() != lots.get(lot_id).getPrev_price() &&
        Auction.init(lots.get(lot_id).getNow_price()); 
        //while(Auction.getCount_participants() != 0){
        while(count_pass != n){
                //РАСЧЁТ ПРИЕМЛИМОСТИ ЦЕНЫ

            for (Person p : persons){
                CountingForLotWithPerson.countAcceptabilityPrice(".\\resources\\fcl\\count_acceptability.fcl", lots.get(lot_id), p);
                System.out.println("=====");
            }

            //БЛОК РАСЧЁТА ЭФФЕКТОВ АУКЦИОНА
            //активность игроков, текущая-стартовая цены, как быстро растёт цена
            //Auction.init(lots.get(lot_id).getNow_price());
            System.out.println(Auction.getActivity());
            System.out.println(Auction.getAuction_growth());
            System.out.println(lots.get(lot_id).getNow_price()-Auction.getZero_price());

            //БЛОК РАСЧЁТА ЭМОЦИЙ
            System.out.println(":) :| :(");
            for (Person p : persons){
                //System.out.println(lots.get(lot_id).getPrev_price());
                System.out.print("Страх бедности : ");
                CountingForPerson.countFear_of_poverty(".\\resources\\fcl\\count_fear_of_poverty.fcl", p);
                System.out.print("Неуверенность в товаре : ");
                CountingForLotWithPerson.countLackOfConfidence(".\\resources\\fcl\\count_lack_of_confidence.fcl", lots.get(lot_id), p);
                System.out.print("Уверенность в себе : ");
                CountingForLotWithPerson.countAssurance(".\\resources\\fcl\\count_assurance.fcl", lots.get(lot_id), p);
                // ...<3            
                System.out.print("Азарт : ");
                CountingForPerson.countExcitement(".\\resources\\fcl\\count_excitement.fcl",  p);
                System.out.print("Необходимость в товаре : ");
                CountingForLotWithPerson.countNeedLot(lots.get(lot_id), p);
                System.out.println(p.getNeed_one_lot());
                System.out.print("Приемлимость цены : ");
                System.out.println(p.getAcceptability());
                System.out.print("Страх потери : ");
                CountingForPerson.countFear_of_loss(".\\resources\\fcl\\count_fear_of_loss.fcl",  p);
                System.out.println("=====");
            }
            //РЕШЕНИЕ ОБ АКТИВНОМ УЧАСТИИ
            double F=0, U=0, S=0, E=0, N=0, A=0, L=0;
            double M = 0, Value_of_Activity = 23;
            ArrayList<Person> participants = new ArrayList<>();
            for (Person p : persons){
                F = p.getFear_of_poverty();
                U = p.getLack_of_confidence();
                S = p.getAssurance();
                E = p.getExcitement();
                N = p.getNeed_one_lot();
                A = p.getNeed_one_lot();
                L = p.getFear_of_loss();
                M = F + U + S - E - N + A - L;
                if ((Value_of_Activity - M)<0 || p.isFlag_leader()){
                    p.setFlag_leader(false);
                    // не участвует
                } else {                
                    // участвует
                    participants.add(p);
                }
            }
            //БЛОК РАСЧЁТА ЭФФЕКТОВ АУКЦИОНА
            //кол-во игроков участвующих
            Auction.setCount_participants(participants.size());
            System.out.println(participants.size());

            //РЕШЕНИЕ ЗАДАЧИ РАСЧЁТА ПОВЫШЕНИЯ ЦЕН
            double max_rate_of_price_increase = -1;
            for (Person p : participants){
                System.out.print("Степень повышения цены : ");
                CountingForPerson.countRate_of_price_increase(".\\resources\\fcl\\count_rate_of_price_increase.fcl",  p);
                if (p.getRate_of_price_increase() > max_rate_of_price_increase){
                    max_rate_of_price_increase = p.getRate_of_price_increase();
                }
            }
            System.out.println("=====");

            //ОПРЕДЕЛЕНИЕ ПОБЕДИТЕЛЯ
            for (Person p : participants){
                if (p.getRate_of_price_increase() == max_rate_of_price_increase){
                    System.out.println(p.getName() +" : "+ p.getRate_of_price_increase());

                    double now_price = lots.get(lot_id).getNow_price();
                    System.out.println(now_price);

                    p.setFlag_leader(true);
                    
                    Auction.setLeader(p);
                    Auction.setAvg_grow_prev2_price(Auction.getAvg_grow_prev_price());
                    Auction.setAvg_grow_prev_price(p.getRate_of_price_increase());
                    Auction.setPrev_price(now_price);

                    lots.get(lot_id).setPrev_price(now_price);
                    lots.get(lot_id).setNow_price(now_price*max_rate_of_price_increase);
                    break;
                }

            }
            System.out.println(Auction.getLeader().getName());
            if (participants.isEmpty()){
                count_pass++;
            } else {
                count_pass = 0;
            }
            System.out.println("=====");
        //КОНЕЦ ЦИКЛА
        }
        System.out.println(Auction.getLeader().getName());
    }
    
    private static ArrayList<Lot> initLot(ArrayList<ArrayList<Object>> ar3, String file_name){
        ArrayList<Lot> lots = new ArrayList<>();
        for (int i = 0; i < ar3.get(0).size(); i++){
            Lot lot = new Lot(file_name);
            lot.setLot_name((String)ar3.get(0).get(i));
            lot.setFame(Integer.parseInt((String)ar3.get(1).get(i)));
            lot.setSignificance(Integer.parseInt((String)ar3.get(2).get(i)));
            lot.setRarity(Integer.parseInt((String)ar3.get(3).get(i)));            
            lots.add(lot);
            CountingForLot.countPrice(file_name, lot);
            //System.out.println("&&&" + lot.getNow_price() + "&" + lot.getPrev_price());
        }
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
        return persons;
    }*/
}
