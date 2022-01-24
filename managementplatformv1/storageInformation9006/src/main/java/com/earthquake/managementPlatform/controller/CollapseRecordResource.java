package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.CollapseRecordMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@RestController
public class CollapseRecordResource {
    @Resource
    CollapseRecordMapper collapseRecordMapper;
    @GetMapping("/v1/collapseRecord")
    public GetVo collapseRecordAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = collapseRecordMapper.getAllCollapseRecord().size();
        List<CollapseRecord> collapseRecord = collapseRecordMapper.getCollapseRecordByPage((page-1)*limit,limit);
        GetVo<CollapseRecord> getVo = new GetVo<>(0,"获取数据成功！",size,collapseRecord);
        return getVo;
    }

    @GetMapping("/v1/collapseRecord/{time}")
    public GetVo collapseRecordByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = collapseRecordMapper.getRecentCollapseRecord(timestamp).size();
        List<CollapseRecord> collapseRecords = collapseRecordMapper.getRecentCollapseRecordByPage((page-1)*limit,limit,timestamp);
        GetVo<CollapseRecord> getVo = new GetVo<>(0,"获取数据成功！",size,collapseRecords);
        return getVo;
    }


    @GetMapping("/v1/byhourcollapseRecord/{time}")
    public GetVo civilStructureByHour(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = collapseRecordMapper.getRecentHourCollapseRecord(time).size();
        List<CollapseRecord> collapseRecords =collapseRecordMapper.getRecentHourCollapseRecordByPage((page-1)*limit,limit,time);
        GetVo<CollapseRecord> getVo = new GetVo<>(0,"获取数据成功！",size,collapseRecords);
        return  getVo;
    }

    @GetMapping("/v1/byhourcollapseRecord")
    public  GetVo drawcollapsepicture(){

        int size1 = collapseRecordMapper.getRecentHourCollapseRecord(1).size();
        int size2= collapseRecordMapper.getRecentHourCollapseRecord(3).size();
        int size3= collapseRecordMapper.getRecentHourCollapseRecord(5).size();
        int size4= collapseRecordMapper.getRecentHourCollapseRecord(12).size();
        List<CollapseRecord> collapseRecords=collapseRecordMapper.getRecentHourCollapseRecord(12);
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

    @GetMapping("/v1/lastCollapseRecordStatistics")
    public GetVo getLastCollapseRecordStatistics(){
        List<SecondaryDisasterStatistics> secondaryDisasterStatistics = collapseRecordMapper.getCollapseStatistics();
        GetVo<SecondaryDisasterStatistics> getVo = new GetVo<>(0,"获取数据成功！",secondaryDisasterStatistics .size(),secondaryDisasterStatistics );
        return getVo;
    }

    @PutMapping("/v1/collapseRecord/{id}")
    public PostVo editCollapseRecord(HttpServletRequest request, @PathVariable("id") String id){
        CollapseRecord collapseRecord = new CollapseRecord();
        collapseRecord.setId(id);
        collapseRecord.setDate(request.getParameter("date"));
        collapseRecord.setLocation(request.getParameter("location"));
        collapseRecord.setType(request.getParameter("type"));
        collapseRecord.setStatus(request.getParameter("status"));
        collapseRecord.setPicture(request.getParameter("picture"));
        collapseRecord.setNote(request.getParameter("note"));
        collapseRecord.setReportingUnit(request.getParameter("reportingUnit"));
        collapseRecord.setEarthquakeId(request.getParameter("earthquakeId"));
        collapseRecordMapper.update(collapseRecord);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/collapseRecord/{id}")
    public PostVo delCollapseRecord(@PathVariable("id")String id){
        collapseRecordMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/collapseRecordCopy/{time}")
    public List<CollapseRecord> collapseRecordCopy(@PathVariable("time") int time){
        List<CollapseRecord> collapseRecords = collapseRecordMapper.getCopyCollapseRecord(time*24);
        return collapseRecords;
    }

    @GetMapping("/v1/singleCollapseRecordInfo/{id}")
    public CollapseRecord getCollapseRecordInfoById(@PathVariable("id")String id){
        CollapseRecord collapseRecordInfo = collapseRecordMapper.getCollapseRecordById(id);
        return collapseRecordInfo;
    }
}
