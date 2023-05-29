/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi2023.english_auction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mephi2023.english_auction.lot.Lot;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author Kseny
 */
public class DataManipulation {
    public DataManipulation(){
        
    }
    
    private void loadData(){
        String fileName = FileNamesStorage.getVar6_fileName();
        
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
        
        MainDataOperations.initPersons(ar1, ar2);
        //РАСЧЁТ ПРЕДПОЛАГАЕМОЙ ЦЕНЫ
        MainDataOperations.initLots(ar3, FileNamesStorage.getPrice_fileName());
    }
    
    private void startAuction(boolean auto, int lot_id){
        MainDataOperations.setAuto(auto);
        MainDataOperations.setLot_id(lot_id);
        MainDataOperations.setCount_pass(0);
        Auction.init(MainDataOperations.getLots().get(lot_id).getNow_price()); 
    }
    
    private void priceAcceptabilityCalculation(){
        //РАСЧЁТ ПРИЕМЛИМОСТИ ЦЕНЫ
        ArrayList<Person> persons = MainDataOperations.getPersons();
        Lot lot = MainDataOperations.getLots().get(MainDataOperations.getLot_id());
        for (Person p : persons){
            CountingForLotWithPerson.countAcceptabilityPrice(FileNamesStorage.getAcceptability_fileName(), lot, p);
            System.out.println("=====");
        }
    }
    
    private void auctionEffectsCalculation(){
        //БЛОК РАСЧЁТА ЭФФЕКТОВ АУКЦИОНА
        //активность игроков, текущая-стартовая цены, как быстро растёт цена
        System.out.println(Auction.getActivity());
        System.out.println(Auction.getAuction_growth());
        System.out.println(MainDataOperations.getLots().get(MainDataOperations.getLot_id()).getNow_price()-Auction.getZero_price());
    }
    
    private void emotionsCalculation(){
        //БЛОК РАСЧЁТА ЭМОЦИЙ
        System.out.println(":) :| :(");
        ArrayList<Person> persons = MainDataOperations.getPersons();
        Lot lot = MainDataOperations.getLots().get(MainDataOperations.getLot_id());
        for (Person p : persons){
            //System.out.println(lots.get(lot_id).getPrev_price());
            System.out.print("Страх бедности : ");
            CountingForPerson.countFear_of_poverty(FileNamesStorage.getFear_of_poverty_fileName(), p);
            System.out.print("Неуверенность в товаре : ");
            CountingForLotWithPerson.countLackOfConfidence( FileNamesStorage.getLack_of_confidence_fileName(), lot, p);
            System.out.print("Уверенность в себе : ");
            CountingForLotWithPerson.countAssurance( FileNamesStorage.getAssurance_fileName(), lot, p);
            // ...<3            
            System.out.print("Азарт : ");
            CountingForPerson.countExcitement( FileNamesStorage.getExcitement_fileName(),  p);
            System.out.print("Необходимость в товаре : ");
            CountingForLotWithPerson.countNeedLot(lot, p);
            System.out.println(p.getNeed_one_lot());
            System.out.print("Приемлимость цены : ");
            System.out.println(p.getAcceptability());
            System.out.print("Страх потери : ");
            CountingForPerson.countFear_of_loss( FileNamesStorage.getFear_of_loss_fileName(),  p);
            System.out.println("=====");
        }    
    }
    
    private void activeParicipationDecision(){
        //РЕШЕНИЕ ОБ АКТИВНОМ УЧАСТИИ
        double F=0, U=0, S=0, E=0, N=0, A=0, L=0;
        double M = 0, Value_of_Activity = 23;
        ArrayList<Person> persons = MainDataOperations.getPersons();
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
        MainDataOperations.setParticipants(participants);
        
        //БЛОК РАСЧЁТА ЭФФЕКТОВ АУКЦИОНА
        //кол-во игроков участвующих
        Auction.setCount_participants(participants.size());
        System.out.println(participants.size());
    }
    
    private void foundLeader(double max_rate_of_price_increase){
        //ОПРЕДЕЛЕНИЕ ПОБЕДИТЕЛЯ
        ArrayList<Person> participants = MainDataOperations.getParticipants();
        Lot lot = MainDataOperations.getLots().get(MainDataOperations.getLot_id());
        for (Person p : participants){
            if (p.getRate_of_price_increase() == max_rate_of_price_increase){
                System.out.println(p.getName() +" : "+ p.getRate_of_price_increase());
                double now_price = lot.getNow_price();
                System.out.println(now_price);
                p.setFlag_leader(true);                
                Auction.setLeader(p);
                Auction.setAvg_grow_prev2_price(Auction.getAvg_grow_prev_price());
                Auction.setAvg_grow_prev_price(p.getRate_of_price_increase());
                Auction.setPrev_price(now_price);
                lot.setPrev_price(now_price);
                lot.setNow_price(now_price*max_rate_of_price_increase);
                break;
            }
        }        
    }
    
    private void checkParticipants(){
        ArrayList<Person> participants = MainDataOperations.getParticipants();
        if (participants.isEmpty()){
            MainDataOperations.addCount_pass();
        } else {
            MainDataOperations.setCount_pass(0);
        }
        System.out.println("=====");
    }
    
    private void rateOfPriceIncreaseDecision(){
        //РЕШЕНИЕ ЗАДАЧИ РАСЧЁТА ПОВЫШЕНИЯ ЦЕН
        ArrayList<Person> participants = MainDataOperations.getParticipants();
        double max_rate_of_price_increase = -1;
        for (Person p : participants){
            System.out.print("Степень повышения цены : ");
            CountingForPerson.countRate_of_price_increase(".\\resources\\fcl\\count_rate_of_price_increase.fcl",  p);
            if (p.getRate_of_price_increase() > max_rate_of_price_increase){
                max_rate_of_price_increase = p.getRate_of_price_increase();
            }
        }
        System.out.println("=====");
        foundLeader(max_rate_of_price_increase);
        System.out.println(Auction.getLeader().getName());
        checkParticipants();
    }
    
    public void modelingAuction(boolean auto, int lot_id){
        loadData();
        
        //НАЧАЛО ЦИКЛА
        startAuction(auto, lot_id);
        int n = MainDataOperations.getPersons().size();
        
        while(MainDataOperations.getCount_pass() != n){
            priceAcceptabilityCalculation();
            auctionEffectsCalculation();
            emotionsCalculation();
            activeParicipationDecision();
            rateOfPriceIncreaseDecision();
            
        //КОНЕЦ ЦИКЛА
        }
        System.out.println(Auction.getLeader().getName());
    }
    
}
