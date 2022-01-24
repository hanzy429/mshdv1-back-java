package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.LandslideRecordMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@RestController
public class LandslideRecordResource {
    @Resource
    LandslideRecordMapper landslideRecordMapper;
    @GetMapping("/v1/landslideRecord")
    public GetVo landslideRecordAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = landslideRecordMapper.getAllLandslideRecord().size();
        List<LandslideRecord> landslideRecord = landslideRecordMapper.getLandslideRecordByPage((page-1)*limit,limit);
        GetVo<LandslideRecord> getVo = new GetVo<>(0,"获取数据成功！",size,landslideRecord);
        return getVo;
    }

    @GetMapping("/v1/landslideRecord/{time}")
    public GetVo landslideRecordByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = landslideRecordMapper.getRecentLandslideRecord(timestamp).size();
        List<LandslideRecord> landslideRecords = landslideRecordMapper.getRecentLandslideRecordByPage((page-1)*limit,limit,timestamp);
        GetVo<LandslideRecord> getVo = new GetVo<>(0,"获取数据成功！",size,landslideRecords);
        return getVo;
    }

    @GetMapping("/v1/byhourlandslideRecord/{time}")
    public GetVo landslideRecordByHour(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = landslideRecordMapper.getRecentHourLandslideRecord(time).size();
        List<LandslideRecord> landslideRecords =landslideRecordMapper.getRecentHourLandslideRecordByPage((page-1)*limit,limit,time);
        GetVo<LandslideRecord> getVo = new GetVo<>(0,"获取数据成功！",size,landslideRecords);
        return  getVo;
    }


    @GetMapping("/v1/byhourlandslideRecord")
    public  GetVo drawlideRecordpicture(){
        int size1 = landslideRecordMapper.getRecentHourLandslideRecord(1).size();
        int size2= landslideRecordMapper.getRecentHourLandslideRecord(3).size();
        int size3= landslideRecordMapper.getRecentHourLandslideRecord(5).size();
        int size4= landslideRecordMapper.getRecentHourLandslideRecord(12).size();
        List<LandslideRecord> landslideRecords=landslideRecordMapper.getRecentHourLandslideRecord(12);
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
    @GetMapping("/v1/lastLandslideRecordStatistics")
    public GetVo getLastLandslideRecordStatistics(){
        List<SecondaryDisasterStatistics> secondaryDisasterStatistics = landslideRecordMapper.getLandslideStatistics();
        GetVo<SecondaryDisasterStatistics> getVo = new GetVo<>(0,"获取数据成功！",secondaryDisasterStatistics .size(),secondaryDisasterStatistics );
        return getVo;
    }

    @PutMapping("/v1/landslideRecord/{id}")
    public PostVo editLandslideRecord(HttpServletRequest request, @PathVariable("id") String id){
        LandslideRecord landslideRecord = new LandslideRecord();
        landslideRecord.setId(id);
        landslideRecord.setDate(request.getParameter("date"));
        landslideRecord.setLocation(request.getParameter("location"));
        landslideRecord.setType(request.getParameter("type"));
        landslideRecord.setStatus(request.getParameter("status"));
        landslideRecord.setPicture(request.getParameter("picture"));
        landslideRecord.setNote(request.getParameter("note"));
        landslideRecord.setReportingUnit(request.getParameter("reportingUnit"));
        landslideRecord.setEarthquakeId(request.getParameter("earthquakeId"));
        landslideRecordMapper.update(landslideRecord);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/landslideRecord/{id}")
    public PostVo delLandslideRecord(@PathVariable("id")String id){
        landslideRecordMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }
    @GetMapping("/v1/landslideRecordCopy/{time}")
    public List<LandslideRecord> landslideRecordCopy(@PathVariable("time") int time){
        List<LandslideRecord> landslideRecords = landslideRecordMapper.getCopyLandslideRecord(time*24);
        return landslideRecords;
    }


    @GetMapping("/v1/singleLandslideRecordInfo/{id}")
    public LandslideRecord getLandslideRecordInfoById(@PathVariable("id")String id){
        LandslideRecord landslideRecordInfo = landslideRecordMapper.getLandslideRecordById(id);
        return landslideRecordInfo;
    }
}
