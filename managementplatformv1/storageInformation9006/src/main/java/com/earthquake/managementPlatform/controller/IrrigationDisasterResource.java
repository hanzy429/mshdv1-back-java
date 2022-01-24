package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.CommDisasterMapper;
import com.earthquake.managementPlatform.mapper.IrrigationDisasterMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@RestController
public class IrrigationDisasterResource {
    @Resource
    IrrigationDisasterMapper irrigationDisasterMapper;
    @GetMapping("/v1/irrigationDisaster")
    public GetVo irrigationDisasterAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = irrigationDisasterMapper.getAllIrrigationDisaster().size();
        List<IrrigationDisaster> irrigationDisaster = irrigationDisasterMapper.getIrrigationDisasterByPage((page-1)*limit,limit);
        GetVo<IrrigationDisaster> getVo = new GetVo<>(0,"获取数据成功！",size,irrigationDisaster);
        return getVo;
    }

    @GetMapping("/v1/irrigationDisaster/{time}")
    public GetVo irrigationDisasterByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = irrigationDisasterMapper.getRecentIrrigationDisaster(timestamp).size();
        List<IrrigationDisaster> irrigationDisasters = irrigationDisasterMapper.getRecentIrrigationDisasterByPage((page-1)*limit,limit,timestamp);
        GetVo<IrrigationDisaster> getVo = new GetVo<>(0,"获取数据成功！",size,irrigationDisasters);
        return getVo;
    }

    @GetMapping("/v1/byhourirrigationDisaster/{time}")
    public GetVo irrigationDisasterByHour(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = irrigationDisasterMapper.getRecentHourIrrigationDisaster(time).size();
        List<IrrigationDisaster> irrigationDisasters =irrigationDisasterMapper.getRecentHourIrrigationDisasterByPage((page-1)*limit,limit,time);
        GetVo<IrrigationDisaster> getVo = new GetVo<>(0,"获取数据成功！",size,irrigationDisasters);
        return  getVo;
    }


    @GetMapping("/v1/byhourirrigationDisaster")
    public  GetVo drawirrigationDisasterpicture(){

        int size1 = irrigationDisasterMapper.getRecentHourIrrigationDisaster(1).size();
        int size2= irrigationDisasterMapper.getRecentHourIrrigationDisaster(3).size();
        int size3= irrigationDisasterMapper.getRecentHourIrrigationDisaster(5).size();
        int size4= irrigationDisasterMapper.getRecentHourIrrigationDisaster(12).size();
        List<IrrigationDisaster> irrigationDisasters=irrigationDisasterMapper.getRecentHourIrrigationDisaster(12);
        List<Integer> number=new LinkedList<>();
        int num1=0;
        int num2=0;
        int num3=0;
        int num4=0;
        for(int j=0;j<size1;j++){
            num1+=1;

        }
        number.add(num1);
        for(int j=size1;j<size2;j++){
            num2+=1;
        }
        number.add(num2);
        for(int j=size2;j<size3;j++){
            num3+=1;
        }
        number.add(num3);
        for(int j=size3;j<size4;j++){
            num4+=1;
        }
        number.add(num4);

        GetVo<Integer> getVo=new GetVo<>(0,"获取数据成功！",4,number);
        return getVo;
    }

    @GetMapping("/v1/lastIrrigationDisasterStatistics")
    public GetVo getLastIrrigationDisasterStatistics(){
        List<LifeLineStatistics> lifeLineStatistics = irrigationDisasterMapper.getIrrigationStatistics();
        GetVo<LifeLineStatistics> getVo = new GetVo<>(0,"获取数据成功！",lifeLineStatistics.size(),lifeLineStatistics);
        return getVo;
    }

    @PutMapping("/v1/irrigationDisaster/{id}")
    public PostVo editIrrigationDisaster(HttpServletRequest request, @PathVariable("id") String id){
        IrrigationDisaster irrigationDisaster = new IrrigationDisaster();
        irrigationDisaster.setId(id);
        irrigationDisaster.setDate(request.getParameter("date"));
        irrigationDisaster.setLocation(request.getParameter("location"));
        irrigationDisaster.setType(request.getParameter("type"));
        irrigationDisaster.setGrade(request.getParameter("grade"));
        irrigationDisaster.setPicture(request.getParameter("picture"));
        irrigationDisaster.setNote(request.getParameter("note"));
        irrigationDisaster.setReportingUnit(request.getParameter("reportingUnit"));
        irrigationDisaster.setEarthquakeId(request.getParameter("earthquakeId"));
        irrigationDisasterMapper.update(irrigationDisaster);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/irrigationDisaster/{id}")
    public PostVo delIrrigationDisaster(@PathVariable("id")String id){
        irrigationDisasterMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/irrigationDisasterCopy/{time}")
    public List<IrrigationDisaster> irrigationDisasterCopy(@PathVariable("time") int time){
        List<IrrigationDisaster> irrigationDisasters = irrigationDisasterMapper.getCopyIrrigationDisaster(time*24);
        return irrigationDisasters;
    }

    @GetMapping("/v1/singleIrrigationDisasterInfo/{id}")
    public IrrigationDisaster getIrrigationDisasterInfoById(@PathVariable("id")String id){
        IrrigationDisaster irrigationDisasterInfo = irrigationDisasterMapper.getIrrigationDisasterById(id);
        return irrigationDisasterInfo;
    }
}
