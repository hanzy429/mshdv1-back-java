package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.InjuredStatisticsMapper;
import com.earthquake.managementPlatform.mapper.MissingStatisticsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@RestController
public class MissingStatisticsResource {
    @Resource
    MissingStatisticsMapper missingStatisticsMapper;
    @GetMapping("/v1/missingStatistics")
    public GetVo missingStatisticsAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = missingStatisticsMapper.getAllMissingStatistics().size();
        List<MissingStatistics> missingStatistics = missingStatisticsMapper.getMissingStatisticsByPage((page-1)*limit,limit);
        GetVo<MissingStatistics> getVo = new GetVo<>(0,"获取数据成功！",size,missingStatistics);
        return getVo;
    }

    @GetMapping("/v1/missingStatistics/{time}")
    public GetVo missingStatisticsByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = missingStatisticsMapper.getRecentMissingStatistics(timestamp).size();
        List<MissingStatistics> missingStatistics = missingStatisticsMapper.getRecentMissingStatisticsByPage((page-1)*limit,limit,timestamp);
        GetVo<MissingStatistics> getVo = new GetVo<>(0,"获取数据成功！",size,missingStatistics);
        return getVo;
    }

    @GetMapping("/v1/byhourmissingStatistics")
    public  GetVo drawmissingpicture(){

        int size1 = missingStatisticsMapper.getRecentHourMissingStatistics(1).size();
        int size2= missingStatisticsMapper.getRecentHourMissingStatistics(3).size();
        int size3= missingStatisticsMapper.getRecentHourMissingStatistics(5).size();
        int size4= missingStatisticsMapper.getRecentHourMissingStatistics(12).size();
        List<MissingStatistics> missingStatistics=missingStatisticsMapper.getRecentHourMissingStatistics(12);
        List<Integer> number=new LinkedList<>();
        int num1=0;
        int num2=0;
        int num3=0;
        int num4=0;
        for(int j=0;j<size1;j++){
            num1+=missingStatistics.get(j).getNumber();

        }
        number.add(num1);
        for(int j=size1;j<size2;j++){
            num2+=missingStatistics.get(j).getNumber();
        }
        number.add(num2);
        for(int j=size2;j<size3;j++){
            num3+=missingStatistics.get(j).getNumber();
        }
        number.add(num3);
        for(int j=size3;j<size4;j++){
            num4+=missingStatistics.get(j).getNumber();
        }
        number.add(num4);

        GetVo<Integer> getVo=new GetVo<>(0,"获取数据成功！",4,number);
        return getVo;
    }

    @GetMapping("/v1/byhourmissingStatistics/{time}")
    public GetVo missingStatisticsByHour(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = missingStatisticsMapper.getRecentHourMissingStatistics(time).size();
        List<MissingStatistics> missingStatistics =missingStatisticsMapper.getRecentHourMissingStatisticsByPage((page-1)*limit,limit,time);
        GetVo<MissingStatistics> getVo = new GetVo<>(0,"获取数据成功！",size,missingStatistics);
        return  getVo;
    }

    @GetMapping("/v1/lastMissingStatisticsByTime")
    public GetVo getLastMissingStatisticsByTime(){
        List<PersonStatistics> personStatistics = missingStatisticsMapper.getLastMissingStatisticsByTime();
        GetVo<PersonStatistics> getVo = new GetVo<>(0,"获取数据成功！",personStatistics.size(),personStatistics);
        return getVo;
    }

    @PutMapping("/v1/missingStatistics/{id}")
    public PostVo editMissingStatistics(HttpServletRequest request, @PathVariable("id") String id){
        MissingStatistics missingStatistics = new MissingStatistics();
        missingStatistics.setId(id);
        missingStatistics.setDate(request.getParameter("date"));
        missingStatistics.setLocation(request.getParameter("location"));
        missingStatistics.setNumber(Integer.valueOf(request.getParameter("number")));
        missingStatistics.setReportingUnit(request.getParameter("reportingUnit"));
        missingStatistics.setEarthquakeId(request.getParameter("earthquakeId"));
        missingStatisticsMapper.update(missingStatistics);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/missingStatistics/{id}")
    public PostVo delMissingStatistics(@PathVariable("id")String id){
        missingStatisticsMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/missingStatisticsCopy/{time}")
    public List<MissingStatistics> missingStatisticsCopy(@PathVariable("time") int time){
        List<MissingStatistics> missingStatistics = missingStatisticsMapper.getCopyMissingStatistics(time*24);
        return missingStatistics;
    }

    @GetMapping("/v1/singleMissingStatisticsInfo/{id}")
    public MissingStatistics getInjuredStatisticsInfoById(@PathVariable("id")String id){
        MissingStatistics missingStatisticsInfo = missingStatisticsMapper.getMissingStatisticsById(id);
        return missingStatisticsInfo;
    }


}
