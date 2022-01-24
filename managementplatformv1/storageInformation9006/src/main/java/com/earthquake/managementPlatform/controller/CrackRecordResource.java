package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.CrackRecordMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@RestController
public class CrackRecordResource {
    @Resource
    CrackRecordMapper crackRecordMapper;
    @GetMapping("/v1/crackRecord")
    public GetVo crackRecordAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = crackRecordMapper.getAllCrackRecord().size();
        List<CrackRecord> crackRecord = crackRecordMapper.getCrackRecordByPage((page-1)*limit,limit);
        GetVo<CrackRecord> getVo = new GetVo<>(0,"获取数据成功！",size,crackRecord);
        return getVo;
    }

    @GetMapping("/v1/crackRecord/{time}")
    public GetVo crackRecordByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = crackRecordMapper.getRecentCrackRecord(timestamp).size();
        List<CrackRecord> crackRecords = crackRecordMapper.getRecentCrackRecordByPage((page-1)*limit,limit,timestamp);
        GetVo<CrackRecord> getVo = new GetVo<>(0,"获取数据成功！",size,crackRecords);
        return getVo;
    }

    @GetMapping("/v1/byhourcrackRecord/{time}")
    public GetVo crackRecordByHour(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = crackRecordMapper.getRecentHourCrackRecord(time).size();
        List<CrackRecord> crackRecords =crackRecordMapper.getRecentHourCrackRecordByPage((page-1)*limit,limit,time);
        GetVo<CrackRecord> getVo = new GetVo<>(0,"获取数据成功！",size,crackRecords);
        return  getVo;
    }

    @GetMapping("/v1/byhourcrackRecord")
    public  GetVo drawcrackRecordpicture(){

        int size1 = crackRecordMapper.getRecentHourCrackRecord(1).size();
        int size2= crackRecordMapper.getRecentHourCrackRecord(3).size();
        int size3= crackRecordMapper.getRecentHourCrackRecord(5).size();
        int size4= crackRecordMapper.getRecentHourCrackRecord(12).size();
        List<CrackRecord> crackRecords=crackRecordMapper.getRecentHourCrackRecord(12);
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

    @GetMapping("/v1/lastCrackRecordStatistics")
    public GetVo getLastCrackRecordStatistics(){
        List<SecondaryDisasterStatistics> secondaryDisasterStatistics = crackRecordMapper.getCrackStatistics();
        GetVo<SecondaryDisasterStatistics> getVo = new GetVo<>(0,"获取数据成功！",secondaryDisasterStatistics .size(),secondaryDisasterStatistics );
        return getVo;
    }

    @PutMapping("/v1/crackRecord/{id}")
    public PostVo editCrackRecord(HttpServletRequest request, @PathVariable("id") String id){
        CrackRecord crackRecord = new CrackRecord();
        crackRecord.setId(id);
        crackRecord.setDate(request.getParameter("date"));
        crackRecord.setLocation(request.getParameter("location"));
        crackRecord.setType(request.getParameter("type"));
        crackRecord.setStatus(request.getParameter("status"));
        crackRecord.setPicture(request.getParameter("picture"));
        crackRecord.setNote(request.getParameter("note"));
        crackRecord.setReportingUnit(request.getParameter("reportingUnit"));
        crackRecord.setEarthquakeId(request.getParameter("earthquakeId"));
        crackRecordMapper.update(crackRecord);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/crackRecord/{id}")
    public PostVo delCrackRecord(@PathVariable("id")String id){
        crackRecordMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/crackRecordCopy/{time}")
    public List<CrackRecord> crackRecordCopy(@PathVariable("time") int time){
        List<CrackRecord> crackRecords = crackRecordMapper.getCopyCrackRecord(time*24);
        return crackRecords;
    }

    @GetMapping("/v1/singleCrackRecordInfo/{id}")
    public CrackRecord getCrackRecordInfoById(@PathVariable("id")String id){
        CrackRecord crackRecordInfo = crackRecordMapper.getCrackRecordById(id);
        return crackRecordInfo;
    }
}
