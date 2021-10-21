package com.earthquake.managementPlatform.service;






import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Slf4j
public class ExcelBehavior implements FileBehavior{
    @Override
    public JSONArray transferToJson(String filePath) throws IOException {
        Sheet sheet;
        Workbook book;
        Cell province,
                city,
                country,
                town,
                village,
                category,
                date,
                location,
                longitude,
                latitude,
                depth,
                magnitude,
                reportingUnit,
                picture;

        SimpleDateFormat originFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        JSONArray array = new JSONArray();

        try {
            //为要读取的excel文件名
            book = Workbook.getWorkbook(new File(filePath));

            //获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
            sheet = book.getSheet(0);

            for (int i = 1; i < sheet.getRows(); i++) {
                //获取每一行的单元格
                province = sheet.getCell(0, i);//（列，行）
                city = sheet.getCell(1, i);
                country = sheet.getCell(2, i);
                town = sheet.getCell(3, i);
                village = sheet.getCell(4, i);
                category = sheet.getCell(5, i);
                date = sheet.getCell(6, i);
                location = sheet.getCell(7, i);
                longitude = sheet.getCell(8, i);//（列，行）
                latitude = sheet.getCell(9, i);
                depth = sheet.getCell(10, i);
                magnitude = sheet.getCell(11, i);
                reportingUnit = sheet.getCell(12, i);
                picture = sheet.getCell(13, i);

                if ("".equals(province.getContents())) {//如果读取的数据为空
                    break;
                }
                JSONObject object = new JSONObject();
                object.put("province",province.getContents());
                object.put("city",city.getContents());
                object.put("country",country.getContents());
                object.put("town",town.getContents());
                object.put("village",village.getContents());
                object.put("category",category.getContents());

                String dateStr = "";
                try {
                    dateStr = originFormat.format(originFormat.parse(date.getContents()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                object.put("date",dateStr);

                object.put("location",location.getContents());
                object.put("longitude",Double.parseDouble(longitude.getContents()));
                object.put("latitude",Double.parseDouble(latitude.getContents()));
                object.put("depth",Double.parseDouble(depth.getContents()));
                object.put("magnitude",Double.parseDouble(magnitude.getContents()));
                object.put("reportingUnit",reportingUnit.getContents());
                object.put("picture",picture.getContents());
                array.put(object);
            }
            System.out.println(array.toString());
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

}