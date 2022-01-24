package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.BrickwoodStructure;
import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.entities.SquareStatistics;
import com.earthquake.managementPlatform.mapper2.BrickwoodStructureMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import com.alibaba.fastjson.JSON;

@RestController
public class BrickwoodStructureResource {
    @Resource
    BrickwoodStructureMapper brickwoodStructureMapper;
    @Resource
    RestTemplate restTemplate;
    @Value("${disasterInfoCode.url}")
    private String disasterInfoCodeUrl;
    @GetMapping("/v1/brickwoodStructure")
    public PostVo brickwoodStructureAll(HttpServletRequest request)throws IOException {
        List<BrickwoodStructure> brickwoodStructure = brickwoodStructureMapper.getAllBrickwoodStructure();
        String json=JSON.toJSONString(brickwoodStructure);
        json=json.substring(1,json.length());
        json=json.substring(0,json.length()-1);
        json="{\"source\":\"401\","+json;
        json=URLEncoder.encode(json,"utf-8");

        return restTemplate.postForObject(disasterInfoCodeUrl+"/v1/disasterInfoCodeToRecode/",json, PostVo.class);
    }


}
