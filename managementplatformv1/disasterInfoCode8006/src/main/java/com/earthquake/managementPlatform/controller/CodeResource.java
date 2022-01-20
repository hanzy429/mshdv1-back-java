package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.Aid;
import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.mapper.AdministrativeRegionCode12Mapper;
import com.earthquake.managementPlatform.service.DisasterInfoCodeService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class CodeResource {
    @Resource
    DisasterInfoCodeService disasterInfoCodeService;

    @Resource
    AdministrativeRegionCode12Mapper administrativeRegionCode12Mapper;

    @Resource
    RestTemplate restTemplate;

    @Value("${storageInformation.url}")
    private String storageInformationUrl;

    @PostMapping("/v1/disasterInfoCode")
    public PostVo assignDisasterInfoCode(@RequestBody Map<JSONArray,String> data){
        ArrayList<String> codes = new ArrayList<>();
        if(data!=null){
        for (Map.Entry<JSONArray,String> entry : data.entrySet()) {
          for(int i = 0;i<entry.getKey().length();i++){
              JSONObject jsonObject = entry.getKey().getJSONObject(i);
              String code = disasterInfoCodeService.assignDisasterInfoCode(jsonObject);
              Map<String,String> map = new HashMap<String,String>();
              map.put("code",code);
              map.put("source",entry.getValue());
              map.put("data",jsonObject.toString());
              log.info(restTemplate.postForObject(storageInformationUrl+"/v1/informationStorage",map,String.class));
              codes.add(code);
          }
        }
        return new PostVo(0,"录入成功",codes);
        }

        return new PostVo(0,"录入失败",codes);
    }

    @PostMapping("/v1/disasterInfoCodeToRecode")
    public PostVo disasterInfoCodeToRecode(@RequestBody String data) throws UnsupportedEncodingException {
        data = URLDecoder.decode(data,"utf-8");
        JSONObject jsonObject = new JSONObject(data);
        ArrayList<String> codes = new ArrayList<>();
        if(jsonObject!=null){
        String code = disasterInfoCodeService.assignDisasterInfoCode(jsonObject);
        Map<String,String> map = new HashMap<String,String>();
        map.put("code",code);
        map.put("source",jsonObject.getString("source"));
        map.put("data",jsonObject.toString());
            File fi=null;

            String urlPath= "http://"+(String)jsonObject.get("influencefield");
            try{
                URL url = new URL(urlPath);
                // 连接类的父类，抽象类
                URLConnection urlConnection = url.openConnection();
                // http的连接类
                HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
                //设置超时
                httpURLConnection.setConnectTimeout(1000*5);
                //设置请求方式，默认是GET
                httpURLConnection.setRequestProperty("Charset", "UTF-8");
                // 打开到此 URL引用的资源的通信链接（如果尚未建立这样的连接）。
                httpURLConnection.connect();
                // 文件大小
                int fileLength = httpURLConnection.getContentLength();

                // 控制台打印文件大小
                System.out.println("您要下载的文件大小为:" + fileLength / (1024 * 1024) + "MB");

                // 建立链接从请求中获取数据
                URLConnection con = url.openConnection();
                BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());
                // 指定文件名称(有需求可以自定义)
                String fileFullName = ((String) jsonObject.get("influencefield")).split("/")[((String) jsonObject.get("influencefield")).split("/").length-1];
                // 指定存放位置(有需求可以自定义)
                String path = "E:" + File.separatorChar + fileFullName;
                fi = new File(path);
                // 校验文件夹目录是否存在，不存在就创建一个目录
                if (!fi.getParentFile().exists()) {
                    fi.getParentFile().mkdirs();
                }

                OutputStream out = new FileOutputStream(fi);
                int size = 0;
                int len = 0;
                byte[] buf = new byte[2048];
                while ((size = bin.read(buf)) != -1) {
                    len += size;
                    out.write(buf, 0, size);
                    // 控制台打印文件下载的百分比情况
                    System.out.println("下载了-------> " + len * 100 / fileLength + "%\n");
                }
                // 关闭资源
                bin.close();
                out.close();
                System.out.println("文件下载成功！");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("文件下载失败！");
            }
        log.info(restTemplate.postForObject(storageInformationUrl+"/v1/informationStorage",map,String.class));
        codes.add(code);
        return new PostVo(0,"录入成功",codes);
        }
        return new PostVo(-1,"录入失败",codes);
    }

    @GetMapping("/v1/hello")
    public String helloWorld(){
        return "HelloWorld";
    }

    @GetMapping("/v1/provinceAid")
    public GetVo getProvinceAid(){
        GetVo<Aid> getVo = new GetVo<>();
        getVo.setCode(0);
        getVo.setMsg("获取省份信息成功！");
        getVo.setData(administrativeRegionCode12Mapper.getProvinceAid());
        getVo.setCount(administrativeRegionCode12Mapper.getProvinceAid().size());
        return getVo;
    }
    @GetMapping("/v1/cityAid/{provinceId}")
    public GetVo getCityAid(@PathVariable("provinceId") String provinceId){
        String provinceCode = provinceId;
        //String provinceCode = "120000000000";
        GetVo<Aid> getVo = new GetVo<>();
        getVo.setCode(0);
        getVo.setMsg("获取城市信息成功！");
        getVo.setData(administrativeRegionCode12Mapper.getCityAid(provinceCode));
        getVo.setCount(administrativeRegionCode12Mapper.getCityAid(provinceCode).size());
        return getVo;
    }
    @GetMapping("/v1/countryAid/{cityId}")
    public GetVo getCountryAid(@PathVariable("cityId") String cityId){
        String cityCode = cityId;
        //String cityCode = "120100000000";
        GetVo<Aid> getVo = new GetVo<>();
        getVo.setCode(0);
        getVo.setMsg("获取区域信息成功！");
        getVo.setData(administrativeRegionCode12Mapper.getCountryAid(cityCode));
        getVo.setCount(administrativeRegionCode12Mapper.getCountryAid(cityCode).size());
        return getVo;
    }
    @GetMapping("/v1/townAid/{countryId}")
    public GetVo getTownAid(@PathVariable("countryId") String countryId){
        String countryCode = countryId;
        //String countryCode = "120102000000";
        GetVo<Aid> getVo = new GetVo<>();
        getVo.setCode(0);
        getVo.setMsg("获取城镇信息成功！");
        getVo.setData(administrativeRegionCode12Mapper.getTownAid(countryCode));
        getVo.setCount(administrativeRegionCode12Mapper.getTownAid(countryCode).size());
        return getVo;
    }
    @GetMapping("/v1/villageAid/{townId}")
    public GetVo getVillageAid(@PathVariable("townId") String townId){
        String townCode = townId;
        //String countryCode = "120102000000";
        GetVo<Aid> getVo = new GetVo<>();
        getVo.setCode(0);
        getVo.setMsg("获取村/社区信息成功！");
        getVo.setData(administrativeRegionCode12Mapper.getVillageAid(townCode));
        getVo.setCount(administrativeRegionCode12Mapper.getVillageAid(townCode).size());
        return getVo;
    }



}
