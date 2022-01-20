package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.InjuredStatisticsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@RestController
public class InjuredStatisticsResource {
    @Resource
    InjuredStatisticsMapper injuredStatisticsMapper;
    @GetMapping("/v1/injuredStatistics")
    public GetVo injuredStatisticsAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = injuredStatisticsMapper.getAllInjuredStatistics().size();
        List<InjuredStatistics> injuredStatistics = injuredStatisticsMapper.getInjuredStatisticsByPage((page-1)*limit,limit);
        GetVo<InjuredStatistics> getVo = new GetVo<>(0,"获取数据成功！",size,injuredStatistics);
        return getVo;
    }

    @GetMapping("/v1/injuredStatistics/{time}")
    public GetVo injuredStatisticsByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = injuredStatisticsMapper.getRecentInjuredStatistics(timestamp).size();
        List<InjuredStatistics> injuredStatistics = injuredStatisticsMapper.getRecentInjuredStatisticsByPage((page-1)*limit,limit,timestamp);
        GetVo<InjuredStatistics> getVo = new GetVo<>(0,"获取数据成功！",size,injuredStatistics);
        return getVo;
    }

    @GetMapping("/v1/byhourinjuredStatistics/{time}")
    public GetVo injuredStatisticsByHour(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = injuredStatisticsMapper.getRecentHourInjuredStatistics(time).size();
        List<InjuredStatistics> injuredStatistics =injuredStatisticsMapper.getRecentHourInjuredStatisticsByPage((page-1)*limit,limit,time);
        GetVo<InjuredStatistics> getVo = new GetVo<>(0,"获取数据成功！",size,injuredStatistics);
        return  getVo;
    }

    @GetMapping("/v1/byhourinjuredStatisticspicture")
    public  GetVo drawinhuredpicture(){

        int size1 = injuredStatisticsMapper.getRecentHourInjuredStatistics(1).size();
        int size2= injuredStatisticsMapper.getRecentHourInjuredStatistics(3).size();
        int size3= injuredStatisticsMapper.getRecentHourInjuredStatistics(5).size();
        int size4= injuredStatisticsMapper.getRecentHourInjuredStatistics(12).size();
        List<InjuredStatistics> injuredStatistics=injuredStatisticsMapper.getRecentHourInjuredStatistics(12);
        List<Integer> number=new LinkedList<>();
        int num1=0;
        int num2=0;
        int num3=0;
        int num4=0;
        for(int j=0;j<size1;j++){
            num1+=injuredStatistics.get(j).getNumber();

        }
        number.add(num1);
        for(int j=size1;j<size2;j++){
            num2+=injuredStatistics.get(j).getNumber();
        }
        number.add(num2);
        for(int j=size2;j<size3;j++){
            num3+=injuredStatistics.get(j).getNumber();
        }
        number.add(num3);
        for(int j=size3;j<size4;j++){
            num4+=injuredStatistics.get(j).getNumber();
        }
        number.add(num4);

        GetVo<Integer> getVo=new GetVo<>(0,"获取数据成功！",4,number);
        return getVo;
    }

    @GetMapping("/v1/lastInjuredStatisticsByTime")
    public GetVo getLastInjuredStatisticsByTime(){
        List<PersonStatistics> personStatistics = injuredStatisticsMapper.getLastInjuredStatisticsByTime();
        GetVo<PersonStatistics> getVo = new GetVo<>(0,"获取数据成功！",personStatistics.size(),personStatistics);
        return getVo;
    }

    @PutMapping("/v1/injuredStatistics/{id}")
    public PostVo editInjuredStatistics(HttpServletRequest request, @PathVariable("id") String id){
        InjuredStatistics injuredStatistics = new InjuredStatistics();
        injuredStatistics.setId(id);
        injuredStatistics.setDate(request.getParameter("date"));
        injuredStatistics.setLocation(request.getParameter("location"));
        injuredStatistics.setNumber(Integer.valueOf(request.getParameter("number")));
        injuredStatistics.setReportingUnit(request.getParameter("reportingUnit"));
        injuredStatistics.setEarthquakeId(request.getParameter("earthquakeId"));
        injuredStatisticsMapper.update(injuredStatistics);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/injuredStatistics/{id}")
    public PostVo delInjuredStatistics(@PathVariable("id")String id){
        injuredStatisticsMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/injuredStatisticsCopy/{time}")
    public List<InjuredStatistics> injuredStatisticsCopy(@PathVariable("time") int time){
        List<InjuredStatistics> injuredStatistics = injuredStatisticsMapper.getCopyInjuredStatistics(time*24);
        return injuredStatistics;
    }

    @GetMapping("/v1/singleInjuredStatisticsInfo/{id}")
    public InjuredStatistics getInjuredStatisticsInfoById(@PathVariable("id")String id){
        InjuredStatistics injuredStatisticsInfo = injuredStatisticsMapper.getInjuredStatisticsById(id);
        return injuredStatisticsInfo;
    }
}
