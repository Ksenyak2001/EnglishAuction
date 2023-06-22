/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi2023.english_auction.manipulation;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import mephi2023.english_auction.MainDataOperations;
import mephi2023.english_auction.person.Person;

/**
 *
 * @author Kseny
 */
public class DrawManipulation {    

    
    public static DefaultTableModel drawTableLotsModel(int n, ArrayList<String> prevlots_id, 
            ArrayList<Double> prev_prices, ArrayList<String> prevleaders){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Лот");
        model.addColumn("Цена");
        model.addColumn("Лидер");
        
        for (int i = 0; i < n-1; i++) {
            Object[] values = new Object[3];
            values[0] = prevlots_id.get(i);
            values[1] = DataManipulation.returnRightView(prev_prices.get(i));
            values[2] = prevleaders.get(i);
            model.addRow(values);
        }
        return model;
    }
    
    
    public static DefaultTableModel drawModel(int person_id){
        ArrayList<Person> persons = MainDataOperations.getPersons();
        Person person = persons.get(person_id);

        ArrayList<String> names = new ArrayList<>();
        names.add("Благосостояние");
        names.add("Необходимость в лоте");
        names.add("Скупость");
        names.add("Тип участника");
        names.add("Самоуверенность");
        names.add("Желание рисковать");
        names.add("Приемлимость цены");
        names.add("Страх бедности");
        names.add("Неуверенности в товаре");
        names.add("Азарт");
        names.add("Уверенность в себе");
        names.add("Страх потери");
        
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(person.getWelfare());
        parameters.add(person.getNeed_one_lot());
        parameters.add(person.getStinginess());
        parameters.add(person.getPlayer_type());
        parameters.add(person.getSelf_confidence());
        parameters.add(person.getRisk_appetite());
        parameters.add(person.getAcceptability());
        parameters.add(person.getFear_of_poverty());
        parameters.add(person.getLack_of_confidence());
        parameters.add(person.getExcitement());
        parameters.add(person.getAssurance());
        parameters.add(person.getFear_of_loss());
        
        return getModel(parameters, names);
    }
    private static DefaultTableModel getModel(ArrayList<Object> parameters, 
            ArrayList<String> names){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Параметр");
        model.addColumn("Значение");
        
        
        for (int i = 0; i < names.size(); i++) {
            Object[] values = new Object[names.size()+1];
            values[0] = names.get(i);
            values[1] = parameters.get(i);
            model.addRow(values);
        }
        return model;
    }
    
}
