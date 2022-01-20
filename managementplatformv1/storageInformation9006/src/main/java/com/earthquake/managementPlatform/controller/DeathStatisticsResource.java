package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.DeathStatisticsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@RestController
public class DeathStatisticsResource {
    @Resource
    DeathStatisticsMapper deathStatisticsMapper;
    @GetMapping("/v1/deathStatistics")
    public GetVo deathStatisticsAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = deathStatisticsMapper.getAllDeathStatistics().size();
        List<DeathStatistics> deathStatistics = deathStatisticsMapper.getDeathStatisticsByPage((page-1)*limit,limit);
        GetVo<DeathStatistics> getVo = new GetVo<>(0,"获取数据成功！",size,deathStatistics);
        return getVo;
    }

    @GetMapping("/v1/deathStatistics/{time}")
    public GetVo deathStatisticsByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = deathStatisticsMapper.getRecentDeathStatistics(timestamp).size();
        List<DeathStatistics> deathStatistics = deathStatisticsMapper.getRecentDeathStatisticsByPage((page-1)*limit,limit,timestamp);
        GetVo<DeathStatistics> getVo = new GetVo<>(0,"获取数据成功！",size,deathStatistics);
        return getVo;
    }

    @GetMapping("/v1/byhourdeathStatistics/{time}")
    public GetVo deathStatisticsByHour(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = deathStatisticsMapper.getRecentHourDeathStatistics(time).size();
        List<DeathStatistics> deathStatistics =deathStatisticsMapper.getRecentHourDeathStatisticsByPage((page-1)*limit,limit,time);
        GetVo<DeathStatistics> getVo = new GetVo<>(0,"获取数据成功！",size,deathStatistics);
        return  getVo;
    }

    @GetMapping("/v1/byhourdeathStatisticspicture")
    public  GetVo drawdeathpicture(){

        int size1 = deathStatisticsMapper.getRecentHourDeathStatistics(1).size();
        int size2= deathStatisticsMapper.getRecentHourDeathStatistics(3).size();
        int size3= deathStatisticsMapper.getRecentHourDeathStatistics(5).size();
        int size4= deathStatisticsMapper.getRecentHourDeathStatistics(12).size();
        List<DeathStatistics> deathStatistics=deathStatisticsMapper.getRecentHourDeathStatistics(12);
        List<Integer> number=new LinkedList<>();
        int num1=0;
        int num2=0;
        int num3=0;
        int num4=0;
        for(int j=0;j<size1;j++){
            num1+=deathStatistics.get(j).getNumber();

        }
        number.add(num1);
        for(int j=size1;j<size2;j++){
            num2+=deathStatistics.get(j).getNumber();
        }
        number.add(num2);
        for(int j=size2;j<size3;j++){
            num3+=deathStatistics.get(j).getNumber();
        }
        number.add(num3);
        for(int j=size3;j<size4;j++){
            num4+=deathStatistics.get(j).getNumber();
        }
        number.add(num4);

        GetVo<Integer> getVo=new GetVo<>(0,"获取数据成功！",4,number);
        return getVo;
    }
    @GetMapping("/v1/lastDeathStatisticsByTime")
    public GetVo provinceAid(){
        List<PersonStatistics> personStatistics = deathStatisticsMapper.getLastDeathStatisticsByTime();
        GetVo<PersonStatistics> getVo = new GetVo<>(0,"获取数据成功！",personStatistics.size(),personStatistics);
        return getVo;
    }

    @PutMapping("/v1/deathStatistics/{id}")
    public PostVo editDeathStatistics(HttpServletRequest request, @PathVariable("id") String id){
        DeathStatistics deathStatistics = new DeathStatistics();
        deathStatistics.setId(id);
        deathStatistics.setDate(request.getParameter("date"));
        deathStatistics.setLocation(request.getParameter("location"));
        deathStatistics.setNumber(Integer.valueOf(request.getParameter("number")));
        deathStatistics.setReportingUnit(request.getParameter("reportingUnit"));
        deathStatistics.setEarthquakeId(request.getParameter("earthquakeId"));
        deathStatisticsMapper.update(deathStatistics);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/deathStatistics/{id}")
    public PostVo delDeathStatistics(@PathVariable("id")String id){
        deathStatisticsMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/deathStatisticsCopy/{time}")
    public List<DeathStatistics> deathStatisticsCopy(@PathVariable("time") int time){
        List<DeathStatistics> deathStatistics = deathStatisticsMapper.getCopyDeathStatistics(time*24);
        return deathStatistics;
    }

    @GetMapping("/v1/singleDeathStatisticsInfo/{id}")
    public DeathStatistics getDeathStatisticsInfoById(@PathVariable("id")String id){
        DeathStatistics deathStatisticsInfo = deathStatisticsMapper.getDeathStatisticsById(id);
        return deathStatisticsInfo;
    }
}
