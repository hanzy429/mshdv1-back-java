package com.earthquake.managementPlatform.controller;

import com.alibaba.fastjson.JSON;
import com.earthquake.managementPlatform.entities.BasicEarthquakeInfo;
import com.earthquake.managementPlatform.entities.PostVo;

import com.earthquake.managementPlatform.mapper2.BasicEarthquakeInfoMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;


@Slf4j
@RestController
public class InformationResource {

    @Resource
    BasicEarthquakeInfoMapper basicEarthquakeInfoMapper;
    @Resource
    RestTemplate restTemplate;
    @Value("${disasterInfoCode.url}")
    private String disasterInfoCodeUrl;
    @GetMapping("/v1/disasterInfoId")
    public PostVo getDisaster()throws IOException {
        List<BasicEarthquakeInfo> disasterInfos = basicEarthquakeInfoMapper.getAllDisasterInfo();
        String json= JSON.toJSONString(disasterInfos);
        json=json.substring(1,json.length());
        json=json.substring(1,json.length()-1);
        json="{\"source\":\"501\","+json;
//        json="{\"source\":\"401\","+json;

        json= URLEncoder.encode(json,"utf-8");
        return restTemplate.postForObject(disasterInfoCodeUrl+"/v1/disasterInfoCodeToRecode/",json, PostVo.class);
    }





}
