/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi2023.english_auction.lot;

import mephi2023.english_auction.Exemplar;
import java.util.ArrayList;

/**
 *
 * @author Kseny
 */
public abstract class ExemplarLot extends Exemplar{
    protected ArrayList<String> param_names;
    protected ArrayList<String> res_names;
    public ExemplarLot(){        
        this.initResParamNames();
    }

    public ExemplarLot(String file_name){        
        super.setFile_name(file_name);
        this.initResParamNames();
    }
    
    private void initResParamNames(){        
        param_names = new ArrayList<>();
        param_names.add("fame");
        param_names.add("significance");
        param_names.add("rarity");
        res_names = new ArrayList<>();
        res_names.add("coef_fame");
        res_names.add("coef_significance");
        res_names.add("coef_rarity");
    }
    
    public ArrayList<String> getParam_names() {
        return param_names;
    }

    public void setParam_names(ArrayList<String> param_names) {
        this.param_names = param_names;
    }

    public ArrayList<String> getRes_names() {
        return res_names;
    }

    public void setRes_names(ArrayList<String> res_names) {
        this.res_names = res_names;
    }
}
