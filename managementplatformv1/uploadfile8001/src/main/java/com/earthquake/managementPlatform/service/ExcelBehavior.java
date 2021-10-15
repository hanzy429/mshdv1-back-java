package com.earthquake.managementPlatform.service;

import com.sun.xml.internal.fastinfoset.util.StringArray;
import jxl.Sheet;
import jxl.Workbook;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

@Slf4j
public class ExcelBehavior implements FileBehavior{
    @Override
    public JSONArray transferToJson(String filePath) throws IOException{
        File excelFile=new File("C:\\Users\\hanzy\\Desktop\\232.xls");
        System.out.println(excelFile);
        JSONArray data=new JSONArray();
        try {
            System.out.println(excelFile);
            System.out.println("kaishi");
            Workbook workbook=Workbook.getWorkbook(excelFile);
            Sheet sheet=workbook.getSheet(0);
            StringArray strIndex=new StringArray();
            JSONObject jsonObject=new JSONObject();
            for(int i=0;i<sheet.getColumns();i++){
                strIndex.add(sheet.getCell(i,0).getContents());
            }
            for(int i=0;i< sheet.getColumns();i++){
                for(int j=1;j<sheet.getRows();j++){
                    jsonObject.put(strIndex.get(i),sheet.getCell(i,j).getContents());
                }
                data.put(jsonObject);
            }
            System.out.println(jsonObject.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

}