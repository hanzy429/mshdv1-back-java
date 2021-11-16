package com.earthquake.managementPlatform.controller;

import com.alibaba.fastjson.JSON;
import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.KarstRecord;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.entities.SecondaryDisasterStatistics;
import com.earthquake.managementPlatform.mapper2.KarstRecordMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@RestController
public class KarstRecordResource {
    @Resource
    KarstRecordMapper karstRecordMapper;
    @Resource
    RestTemplate restTemplate;
    @Value("${disasterInfoCode.url}")
    private String disasterInfoCodeUrl;
    @GetMapping("/v1/karstRecord")
    public PostVo karstRecordAll(HttpServletRequest request)throws IOException {
        List<KarstRecord> karstRecord = karstRecordMapper.getAllKarstRecord();
        String json= JSON.toJSONString(karstRecord);
        json=json.substring(1,json.length());
        json=json.substring(0,json.length()-1);
        json="{\"source\":\"401\","+json;
        json= URLEncoder.encode(json,"utf-8");
        return restTemplate.postForObject(disasterInfoCodeUrl+"/v1/disasterInfoCodeToRecode/",json, PostVo.class);
    }


}
