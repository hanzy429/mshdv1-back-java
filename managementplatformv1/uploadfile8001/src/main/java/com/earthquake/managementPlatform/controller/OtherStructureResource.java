package com.earthquake.managementPlatform.controller;

import com.alibaba.fastjson.JSON;
import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.OtherStructure;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.entities.SquareStatistics;
import com.earthquake.managementPlatform.mapper2.OtherStructureMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@RestController
public class OtherStructureResource {
    @Resource
    OtherStructureMapper otherStructureMapper;
    @Resource
    RestTemplate restTemplate;
    @Value("${disasterInfoCode.url}")
    private String disasterInfoCodeUrl;
    @GetMapping("/v1/otherStructure")
    public PostVo otherStructureAll(HttpServletRequest request)throws IOException {
        List<OtherStructure> otherStructure = otherStructureMapper.getAllOtherStructure();
        String json= JSON.toJSONString(otherStructure);
        json=json.substring(1,json.length());
        json=json.substring(0,json.length()-1);
        json="{\"source\":\"401\","+json;
        json= URLEncoder.encode(json,"utf-8");
        return restTemplate.postForObject(disasterInfoCodeUrl+"/v1/disasterInfoCodeToRecode/",json, PostVo.class);
    }

}
