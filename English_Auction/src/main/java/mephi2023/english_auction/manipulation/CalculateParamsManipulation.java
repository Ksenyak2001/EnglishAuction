/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi2023.english_auction.manipulation;

import java.util.ArrayList;
import java.util.Collections;
import mephi2023.english_auction.MainDataOperations;
import mephi2023.english_auction.auction.Auction;
import mephi2023.english_auction.person.Person;

/**
 *
 * @author Kseny
 */
public class CalculateParamsManipulation {
    
    //для вычисления параметров (если всё таки не константа)
    private static double changeBounds(double num, double max, double min){
        return (num*(max-min)+min);
    }
    
    private static double findMin_ceiling_price(){        
        ArrayList<Person> persons = MainDataOperations.getPersons(); 
        double min_ceiling_price = 1000000; 
        for (Person p : persons){
            double ceiling_price = p.getTemp_max_price()/Auction.getPrev_price();
            if (ceiling_price < min_ceiling_price){
                min_ceiling_price = ceiling_price;
            }
        }
        return min_ceiling_price;
    }    
    private static double findMax_ceiling_price(){        
        ArrayList<Person> persons = MainDataOperations.getPersons(); 
        double max_ceiling_price = 0; 
        for (Person p : persons){
            double ceiling_price = p.getTemp_max_price()/Auction.getPrev_price();
            if (ceiling_price > max_ceiling_price){
                max_ceiling_price = ceiling_price;
            }
        }
        return max_ceiling_price;
    }       
    private static double findMin_count_take_part_lots(){        
        ArrayList<Person> persons = MainDataOperations.getPersons(); 
        int min_count_take_part_lots = 1000000;
        for (Person p : persons){
            int count_take_part_lots = p.getCount_take_part();
            if (count_take_part_lots < min_count_take_part_lots){
                min_count_take_part_lots = count_take_part_lots;
            }
        }
        return min_count_take_part_lots;
    }    
    private static double findMax_count_take_part_lots(){        
        ArrayList<Person> persons = MainDataOperations.getPersons(); 
        double max_count_take_part_lots = 0; 
        for (Person p : persons){
            int count_take_part_lots = p.getCount_take_part();
            if (count_take_part_lots > max_count_take_part_lots){
                max_count_take_part_lots = count_take_part_lots;
            }
        }
        return max_count_take_part_lots;
    }
    private static double findMin_max_level_rise_price(){        
        ArrayList<Person> persons = MainDataOperations.getPersons(); 
        double min_max_level_rise_price = 1000000;
        for (Person p : persons){
            double max_level_rise_price = Collections.max(p.getTemp_rise_prices())/Auction.getPrev_price();
            if (max_level_rise_price < min_max_level_rise_price){
                min_max_level_rise_price = max_level_rise_price;
            }
        }
        return min_max_level_rise_price;
    }    
    private static double findMax_max_level_rise_price(){        
        ArrayList<Person> persons = MainDataOperations.getPersons(); 
        double max_max_level_rise_price = 0; 
        for (Person p : persons){
            double max_level_rise_price = Collections.max(p.getTemp_rise_prices())/Auction.getPrev_price();
            if (max_level_rise_price > max_max_level_rise_price){
                max_max_level_rise_price = max_level_rise_price;
            }
        }
        return max_max_level_rise_price;
    }
    private static double findMin_share_rates(){        
        ArrayList<Person> persons = MainDataOperations.getPersons(); 
        double min_share_rates = 1000000;
        for (Person p : persons){
            double share_rates = p.getCount_temp_rates()/Auction.getCount_temp_rates();
            if (share_rates < min_share_rates){
                min_share_rates = share_rates;
            }
        }
        return min_share_rates;
    }    
    private static double findMax_share_rates(){        
        ArrayList<Person> persons = MainDataOperations.getPersons(); 
        double max_share_rates = 0; 
        for (Person p : persons){
            double share_rates = p.getCount_temp_rates()/Auction.getCount_temp_rates();
            if (share_rates > max_share_rates){
                max_share_rates = share_rates;
            }
        }
        return max_share_rates;
    }
    private static double findMin_money(){        
        ArrayList<Person> persons = MainDataOperations.getPersons(); 
        double min_money = 1000000;
        for (Person p : persons){
            double money = p.getSum_max_prices();
            if (money < min_money){
                min_money = money;
            }
        }
        return min_money;
    }    
    private static double findMax_money(){        
        ArrayList<Person> persons = MainDataOperations.getPersons(); 
        double max_money = 0; 
        for (Person p : persons){
            double money = p.getSum_max_prices();
            if (money > max_money){
                max_money = money;
            }
        }
        return max_money;
    }
    private static double findMin_avarice(){        
        ArrayList<Person> persons = MainDataOperations.getPersons(); 
        double min_avarice = 1000000; 
        for (Person p : persons){
            double avarice = p.getTemp_rise_prices().stream().reduce((x, y) -> x + y).get()/p.getCount_temp_rates();
            if (avarice < min_avarice){
                min_avarice = avarice;
            }
        }
        return min_avarice;
    }    
    private static double findMax_avarice(){        
        ArrayList<Person> persons = MainDataOperations.getPersons(); 
        double max_avarice = 0; 
        for (Person p : persons){
            double avarice = p.getTemp_rise_prices().stream().reduce((x, y) -> x + y).get()/p.getCount_temp_rates();
            if (avarice > max_avarice){
                max_avarice = avarice;
            }
        }
        return max_avarice;
    }
    private static double findMin_count_lose_lots(){        
        ArrayList<Person> persons = MainDataOperations.getPersons(); 
        double min_count_lose_lots = 1000000; 
        for (Person p : persons){
            double count_lose_lots = p.getCount_take_lose_part();
            if (count_lose_lots < min_count_lose_lots){
                min_count_lose_lots = count_lose_lots;
            }
        }
        return min_count_lose_lots;
    }    
    private static double findMax_count_lose_lots(){        
        ArrayList<Person> persons = MainDataOperations.getPersons(); 
        double max_count_lose_lots = 0; 
        for (Person p : persons){
            double count_lose_lots = p.getCount_take_lose_part();
            if (count_lose_lots > max_count_lose_lots){
                max_count_lose_lots = count_lose_lots;
            }
        }
        return max_count_lose_lots;
    }
    private static double findMin_share_win_lots(){        
        ArrayList<Person> persons = MainDataOperations.getPersons(); 
        double min_share_win_lots = 1000000; 
        for (Person p : persons){
            double share_win_lots = p.getCount_win()/p.getCount_take_part();
            if (share_win_lots < min_share_win_lots){
                min_share_win_lots = share_win_lots;
            }
        }
        return min_share_win_lots;
    }    
    private static double findMax_share_win_lots(){        
        ArrayList<Person> persons = MainDataOperations.getPersons(); 
        double max_share_win_lots = 0; 
        for (Person p : persons){
            double share_win_lots = p.getCount_win()/p.getCount_take_part();
            if (share_win_lots > max_share_win_lots){
                max_share_win_lots = share_win_lots;
            }
        }
        return max_share_win_lots;
    }    
    private static double findMin_avg_level_rise_price(){        
        ArrayList<Person> persons = MainDataOperations.getPersons(); 
        double min_avg_level_rise_price = 1000000; 
        for (Person p : persons){
            double avg_level_rise_price = p.getAll_rise_prices().stream().reduce((x, y) -> x + y).get()/p.getAll_rise_prices().size();
            if (avg_level_rise_price < min_avg_level_rise_price){
                min_avg_level_rise_price = avg_level_rise_price;
            }
        }
        return min_avg_level_rise_price;
    }    
    private static double findMax_avg_level_rise_price(){        
        ArrayList<Person> persons = MainDataOperations.getPersons(); 
        double max_avg_level_rise_price = 0; 
        for (Person p : persons){
            double avg_level_rise_price = p.getAll_rise_prices().stream().reduce((x, y) -> x + y).get()/p.getAll_rise_prices().size();
            if (avg_level_rise_price > max_avg_level_rise_price){
                max_avg_level_rise_price = avg_level_rise_price;
            }
        }
        return max_avg_level_rise_price;
    }
    
    private static void changeCount_take_part(){        
        ArrayList<Person> persons = MainDataOperations.getPersons();
        for (Person p : persons){
            p.addSum_max_prices(p.getTemp_max_price());
            if (p.isFlag_take_part_temp()){
                p.addCount_take_part();
                if (!p.isFlag_leader()){
                    p.addCount_take_lose_part();
                }
            }
        }
    }
    
    public static void doNewRightScaleParameters(){
        CalculateParamsManipulation.changeCount_take_part();
        ArrayList<Person> persons = MainDataOperations.getPersons(); 
        
        double min_count_take_part_lots = CalculateParamsManipulation.findMin_count_take_part_lots();
        double min_ceiling_price = CalculateParamsManipulation.findMin_ceiling_price();
        double min_max_level_rise_price = CalculateParamsManipulation.findMin_max_level_rise_price();
        double min_share_rates = CalculateParamsManipulation.findMin_share_rates();
        double min_money = CalculateParamsManipulation.findMin_money();
        double min_avarice = CalculateParamsManipulation.findMin_avarice();
        double min_count_lose_lots = CalculateParamsManipulation.findMin_count_lose_lots();
        double min_share_win_lots = CalculateParamsManipulation.findMin_share_win_lots();
        double min_avg_level_rise_price = CalculateParamsManipulation.findMin_avg_level_rise_price();
        
        double max_count_take_part_lots = CalculateParamsManipulation.findMax_count_take_part_lots();
        double max_ceiling_price = CalculateParamsManipulation.findMax_ceiling_price();
        double max_max_level_rise_price = CalculateParamsManipulation.findMax_max_level_rise_price();
        double max_share_rates = CalculateParamsManipulation.findMax_share_rates();
        double max_money = CalculateParamsManipulation.findMax_money();
        double max_avarice = CalculateParamsManipulation.findMax_avarice();
        double max_count_lose_lots = CalculateParamsManipulation.findMax_count_lose_lots();
        double max_share_win_lots = CalculateParamsManipulation.findMax_share_win_lots();
        double max_avg_level_rise_price = CalculateParamsManipulation.findMax_avg_level_rise_price();
        
        for (Person p : persons){
            double count_take_part_lots = p.getCount_take_part();
            double ceiling_price = p.getTemp_max_price()/Auction.getPrev_price();
            double max_level_rise_price = Collections.max(p.getTemp_rise_prices())/Auction.getPrev_price();
            double share_rates = p.getCount_temp_rates()/Auction.getCount_temp_rates();
            double money = p.getSum_max_prices();
            double avarice = p.getTemp_rise_prices().stream().reduce((x, y) -> x + y).get()/p.getCount_temp_rates();
            double count_lose_lots = p.getCount_take_lose_part();
            double share_win_lots = p.getCount_win()/p.getCount_take_part();
            double avg_level_rise_price = p.getAll_rise_prices().stream().reduce((x, y) -> x + y).get()/p.getAll_rise_prices().size();
            count_take_part_lots = (count_take_part_lots - min_count_take_part_lots)/(max_count_take_part_lots - min_count_take_part_lots);
            ceiling_price = (ceiling_price - min_ceiling_price)/(max_ceiling_price - min_ceiling_price);
            max_level_rise_price = (max_level_rise_price - min_max_level_rise_price)/(max_max_level_rise_price - min_max_level_rise_price);
            share_rates = (share_rates - min_share_rates)/(max_share_rates - min_share_rates);
            money = (money - min_money)/(max_money - min_money);
            avarice = (avarice - min_avarice)/(max_avarice - min_avarice);
            count_lose_lots = (count_lose_lots - min_count_lose_lots)/(max_count_lose_lots - min_count_lose_lots);
            share_win_lots = (share_win_lots - min_share_win_lots)/(max_share_win_lots - min_share_win_lots);
            avg_level_rise_price = (avg_level_rise_price - min_avg_level_rise_price)/(max_avg_level_rise_price - min_avg_level_rise_price);

            //спекулянт-коллекционер
            count_take_part_lots = changeBounds(count_take_part_lots, 5, 1);
            ceiling_price = changeBounds(ceiling_price, 5, 1);
            max_level_rise_price = changeBounds(max_level_rise_price, 5, 1);
            share_rates = changeBounds(share_rates, 5, 1);
            
            if (count_take_part_lots*max_level_rise_price > ceiling_price*share_rates){
                p.setPlayer_type(1);
            } else {
                p.setPlayer_type(2);
            }
            //благосостояние
            money = changeBounds(money, 10, 0);
            p.setWelfare(money);
            //скупость
            avarice = changeBounds(avarice, 10, 0);
            p.setStinginess(avarice);
            //склонность к риску
            count_lose_lots = changeBounds(count_lose_lots, 10, 0);
            p.setRisk_appetite(count_lose_lots);
            //самоуверенность
            share_win_lots = changeBounds(share_win_lots, 5, 1);
            avg_level_rise_price = changeBounds(avg_level_rise_price, 5, 1);
            p.setSelf_confidence(share_win_lots*avg_level_rise_price/(5*5)*10);
            //необходимость в товаре
            
            p.setTemp_max_price(0);
            p.setTemp_max_rise_price(0);
            p.setFlag_take_part_temp(false);
        }
    }
    
    
    /*private void doInRightScaleParameters(){
        ArrayList<Person> persons = MainDataOperations.getPersons(); 
        double min_share_win_lots = 1000000;
        double min_avg_level_rise_price = 1000000;
        double min_speed = 1000000;
        double min_closeness_to_win = 1000000;
              
        double max_max_level_rise_price = 0;
        double max_share_rates = 0;
        double max_money = 0;
        double max_avarice = 0;
        double max_count_lose_lots = 0;
        double max_share_win_lots = 0;
        double max_avg_level_rise_price = 0;
        double max_speed = 0;
        double max_closeness_to_win = 0;
        /*
        int count_take_part_lots = 0;
        double ceiling_price = 0;
        double max_level_rise_price = 0;
        double share_rates = 0;
        double money = 0;
        double avarice = 0;
        double count_lose_lots = 0;
        double share_win_lots = 0;
        double avg_level_rise_price = 0;
        double speed = 0;
        double closeness_to_win = 0;
        */
                
        /*
        flag_take_part_temp = false;
        count_take_part = 0;
        temp_max_price = 0;
        temp_max_rise_price = 0;
        sum_max_prices = 0;
        count_take_lose_part = 0;
        count_win = 0;
        count_temp_rates = 0;
        temp_rise_prices = new ArrayList<>();
        

        for (Person p : persons){
            //System.out.println(p.getAll_rise_prices().size());
            double speed = (Math.random() * (5 - 1)) + 1;
            double closeness_to_win = p.getTemp_max_price() - Auction.getPrev_price();
            
            if (count_take_part_lots > max_count_take_part_lots){
                max_count_take_part_lots = count_take_part_lots;
            }
            if (max_level_rise_price > max_max_level_rise_price){
                max_max_level_rise_price = max_level_rise_price;
            }
            if (share_rates > max_share_rates){
                max_share_rates = share_rates;
            }
            if (money > max_money){
                max_money = money;
            }
            if (avarice > max_avarice){
                max_avarice = avarice;
            }
            if (count_lose_lots > max_count_lose_lots){
                max_count_lose_lots = count_lose_lots;
            }
            if (share_win_lots > max_share_win_lots){
                max_share_win_lots = share_win_lots;
            }
            if (avg_level_rise_price > max_avg_level_rise_price){
                max_avg_level_rise_price = avg_level_rise_price;
            }
            if (speed > max_speed){
                max_speed = speed;
            }
            if (closeness_to_win > max_closeness_to_win){
                max_closeness_to_win = closeness_to_win;
            }
            
            
            if (count_take_part_lots < min_count_take_part_lots){
                min_count_take_part_lots = count_take_part_lots;
            }
            if (max_level_rise_price < min_max_level_rise_price){
                min_max_level_rise_price = max_level_rise_price;
            }
            if (share_rates < min_share_rates){
                min_share_rates = share_rates;
            }
            if (money < min_money){
                min_money = money;
            }
            if (avarice < min_avarice){
                min_avarice = avarice;
            }
            if (count_lose_lots < min_count_lose_lots){
                min_count_lose_lots = count_lose_lots;
            }
            if (share_win_lots < min_share_win_lots){
                min_share_win_lots = share_win_lots;
            }
            if (avg_level_rise_price < min_avg_level_rise_price){
                min_avg_level_rise_price = avg_level_rise_price;
            }
            if (speed < min_speed){
                min_speed = speed;
            }
            if (closeness_to_win < min_closeness_to_win){
                min_closeness_to_win = closeness_to_win;
            }
        }
        
        System.out.println(max_closeness_to_win);
        System.out.println(min_closeness_to_win);
        for (Person p : persons){
            double count_take_part_lots = p.getCount_take_part();
            double ceiling_price = p.getTemp_max_price()/Auction.getPrev_price();
            double max_level_rise_price = Collections.max(p.getTemp_rise_prices())/Auction.getPrev_price();
            double share_rates = p.getCount_temp_rates()/Auction.getCount_temp_rates();
            double money = p.getSum_max_prices();
            double avarice = p.getTemp_rise_prices().stream().reduce((x, y) -> x + y).get()/p.getCount_temp_rates();
            double count_lose_lots = p.getCount_take_lose_part();
            double share_win_lots = p.getCount_win()/p.getCount_take_part();
            double avg_level_rise_price = p.getAll_rise_prices().stream().reduce((x, y) -> x + y).get()/p.getAll_rise_prices().size();
            double speed = (Math.random() * (5 - 1)) + 1;
            double closeness_to_win = p.getTemp_max_price() - Auction.getPrev_price();
            count_take_part_lots = (count_take_part_lots - min_count_take_part_lots)/(max_count_take_part_lots - min_count_take_part_lots);
            ceiling_price = (ceiling_price - min_ceiling_price)/(max_ceiling_price - min_ceiling_price);
            max_level_rise_price = (max_level_rise_price - min_max_level_rise_price)/(max_max_level_rise_price - min_max_level_rise_price);
            share_rates = (share_rates - min_share_rates)/(max_share_rates - min_share_rates);
            money = (money - min_money)/(max_money - min_money);
            avarice = (avarice - min_avarice)/(max_avarice - min_avarice);
            count_lose_lots = (count_lose_lots - min_count_lose_lots)/(max_count_lose_lots - min_count_lose_lots);
            share_win_lots = (share_win_lots - min_share_win_lots)/(max_share_win_lots - min_share_win_lots);
            avg_level_rise_price = (avg_level_rise_price - min_avg_level_rise_price)/(max_avg_level_rise_price - min_avg_level_rise_price);
            speed = (speed - min_speed)/(max_speed - min_speed);
            closeness_to_win = (closeness_to_win - min_closeness_to_win)/(max_closeness_to_win - min_closeness_to_win);
            
            //спекулянт-коллекционер
            count_take_part_lots = changeBounds(count_take_part_lots, 5, 1);
            ceiling_price = changeBounds(ceiling_price, 5, 1);
            max_level_rise_price = changeBounds(max_level_rise_price, 5, 1);
            share_rates = changeBounds(share_rates, 5, 1);
            
            if (count_take_part_lots*max_level_rise_price > ceiling_price*share_rates){
                p.setPlayer_type(1);
            } else {
                p.setPlayer_type(2);
            }
            //благосостояние
            money = changeBounds(money, 10, 0);
            p.setWelfare(money);
            //скупость
            avarice = changeBounds(avarice, 10, 0);
            p.setStinginess(avarice);
            //склонность к риску
            count_lose_lots = changeBounds(count_lose_lots, 10, 0);
            p.setRisk_appetite(count_lose_lots);
            //самоуверенность
            share_win_lots = changeBounds(share_win_lots, 5, 1);
            avg_level_rise_price = changeBounds(avg_level_rise_price, 5, 1);
            p.setSelf_confidence(share_win_lots*avg_level_rise_price/(5*5)*10);
            //необходимость в товаре
            speed = changeBounds(speed, 5, 1);
            closeness_to_win = changeBounds(closeness_to_win, 5, 1);
            share_rates = share_rates;
            
            p.setTemp_max_price(0);
            p.setTemp_max_rise_price(0);
            p.setFlag_take_part_temp(false);
        }
    }*/
}
