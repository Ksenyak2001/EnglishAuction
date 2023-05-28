/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi2023.english_auction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import net.sourceforge.jFuzzyLogic.FIS;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Kseny
 */
public class DataReader {    
    public static ArrayList<ArrayList<Object>> ReadXLSX(String fileName, int numbList, int startRow) throws FileNotFoundException, IOException, InvalidFormatException{
        ArrayList<ArrayList<Object>> dataFromList = new ArrayList<>();
        File file = new File(fileName);
        Workbook workbook = new XSSFWorkbook(file);
        org.apache.poi.ss.usermodel.Sheet worksheet = workbook.getSheetAt(numbList);
        int cols = worksheet.getRow(0).getLastCellNum();
        for (int i = 0; i < cols; ++i){
            dataFromList.add(new ArrayList<>());
        }
        Iterator<Row> ri = worksheet.rowIterator();
        while (startRow > 0){
            ri.next();
            startRow--;
        }
                
        while(ri.hasNext()) {
            XSSFRow row = (XSSFRow) ri.next();
            Iterator<Cell> ci = row.cellIterator();
            int i = 0;
            while(ci.hasNext()) {
                XSSFCell cell = (XSSFCell) ci.next();
                switch (cell.getCellType()) {
                    case STRING -> dataFromList.get(i).add(cell.getStringCellValue());
                    case NUMERIC -> {
                        if (DateUtil.isCellDateFormatted(cell)) {
                            dataFromList.get(i).add(cell.getLocalDateTimeCellValue());
                        } else {
                            dataFromList.get(i).add(NumberToTextConverter.toText(cell.getNumericCellValue()));
                        }
                    }
                    case BOOLEAN -> dataFromList.get(i).add(cell.getBooleanCellValue());
                    case FORMULA -> dataFromList.get(i).add(cell.getCellFormula());
                    default -> dataFromList.get(i).add(" ");
                }
                //System.out.println(dataFromList.get(i).get(dataFromList.get(i).size()-1));
                i++;
            }                
        }      
        return dataFromList;
    }
    
    public static FIS ReadFCL(String fileName){
        FIS fis = FIS.load(fileName,true);
        if( fis == null ) { 
            System.err.println("Can't load file: '" + fileName + "'");
            return null;
        }
        return fis;
    }
}

