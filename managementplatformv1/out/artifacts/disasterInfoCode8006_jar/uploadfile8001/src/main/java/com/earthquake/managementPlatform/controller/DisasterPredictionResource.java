package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.DisasterPrediction;
import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.mapper2.DisasterPredictionMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class DisasterPredictionResource {
    @Resource
    DisasterPredictionMapper disasterPredictionMapper;

}
