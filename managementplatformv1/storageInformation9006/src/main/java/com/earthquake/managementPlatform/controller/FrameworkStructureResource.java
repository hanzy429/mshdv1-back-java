package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.FrameworkStructureMapper;
import com.earthquake.managementPlatform.mapper.MasonryStructureMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@RestController
public class FrameworkStructureResource {
    @Resource
    FrameworkStructureMapper frameworkStructureMapper;
    @GetMapping("/v1/frameworkStructure")
    public GetVo frameworkStructureAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = frameworkStructureMapper.getAllFrameworkStructure().size();
        List<FrameworkStructure> frameworkStructure = frameworkStructureMapper.getFrameworkStructureByPage((page-1)*limit,limit);
        GetVo<FrameworkStructure> getVo = new GetVo<>(0,"获取数据成功！",size,frameworkStructure);
        return getVo;
    }

    @GetMapping("/v1/frameworkStructure/{time}")
    public GetVo frameworkStructureByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = frameworkStructureMapper.getRecentFrameworkStructure(timestamp).size();
        List<FrameworkStructure> frameworkStructures = frameworkStructureMapper.getRecentFrameworkStructureByPage((page-1)*limit,limit,timestamp);
        GetVo<FrameworkStructure> getVo = new GetVo<>(0,"获取数据成功！",size,frameworkStructures);
        return getVo;
    }

    @GetMapping("/v1/byhourframeworkStructure/{time}")
    public GetVo frameworkStructureByHour(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = frameworkStructureMapper.getRecentHourFrameworkStructure(time).size();
        List<FrameworkStructure> frameworkStructures =frameworkStructureMapper.getRecentHourFrameworkStructureByPage((page-1)*limit,limit,time);
        GetVo<FrameworkStructure> getVo = new GetVo<>(0,"获取数据成功！",size,frameworkStructures);
        return  getVo;
    }

    @GetMapping("/v1/byhourframeworkStructurepicture")
    public  GetVo drawframeworkStructurepicture(){

        int size1 = frameworkStructureMapper.getRecentHourFrameworkStructure(1).size();
        int size2= frameworkStructureMapper.getRecentHourFrameworkStructure(3).size();
        int size3= frameworkStructureMapper.getRecentHourFrameworkStructure(5).size();
        int size4= frameworkStructureMapper.getRecentHourFrameworkStructure(12).size();
        List<FrameworkStructure> frameworkStructures=frameworkStructureMapper.getRecentHourFrameworkStructure(12);
        List<Float> number=new LinkedList<>();
        float num1=0;
        float num2=0;
        float num3=0;
        float num4=0;
        for(int j=0;j<size1;j++){
            num1+=frameworkStructures.get(j).getDestroyedSquare();
            num1+=frameworkStructures.get(j).getSlightDamagedSquare();
            num1+=frameworkStructures.get(j).getModerateDamagedSquare();
            num1+=frameworkStructures.get(j).getSeriousDamagedSquare();
        }
        number.add(num1);
        for(int j=size1;j<size2;j++){
            num2+=frameworkStructures.get(j).getDestroyedSquare();
            num2+=frameworkStructures.get(j).getSlightDamagedSquare();
            num2+=frameworkStructures.get(j).getModerateDamagedSquare();
            num2+=frameworkStructures.get(j).getSeriousDamagedSquare();
        }
        number.add(num2);
        for(int j=size2;j<size3;j++){
            num3+=frameworkStructures.get(j).getDestroyedSquare();
            num3+=frameworkStructures.get(j).getSlightDamagedSquare();
            num3+=frameworkStructures.get(j).getModerateDamagedSquare();
            num3+=frameworkStructures.get(j).getSeriousDamagedSquare();
        }
        number.add(num3);
        for(int j=size3;j<size4;j++){
            num4+=frameworkStructures.get(j).getDestroyedSquare();
            num4+=frameworkStructures.get(j).getSlightDamagedSquare();
            num4+=frameworkStructures.get(j).getModerateDamagedSquare();
            num4+=frameworkStructures.get(j).getSeriousDamagedSquare();
        }
        number.add(num4);

        GetVo<Float> getVo=new GetVo<>(0,"获取数据成功！",4,number);
        return getVo;
    }


    @GetMapping("/v1/lastFrameworkStructureByTime")
    public GetVo getLastFrameworkStructureByTime(){
        List<SquareStatistics> squareStatistics = frameworkStructureMapper.getLastFrameworkStructureByTime();
        GetVo<SquareStatistics> getVo = new GetVo<>(0,"获取数据成功！",squareStatistics.size(),squareStatistics);
        return getVo;
    }

    @GetMapping("/v1/lastFrameworkStructure")
    public GetVo getLastFrameworkStructure(){
        List<FrameworkStructure> frameworkStructure = frameworkStructureMapper.getLastFrameworkStructure();
        GetVo<FrameworkStructure> getVo = new GetVo<>(0,"获取数据成功！",frameworkStructure.size(),frameworkStructure);
        return getVo;
    }

    @PutMapping("/v1/frameworkStructure/{id}")
    public PostVo editFrameworkStructure(HttpServletRequest request, @PathVariable("id") String id){
        FrameworkStructure frameworkStructure = new FrameworkStructure();
        frameworkStructure.setId(id);
        frameworkStructure.setDate(request.getParameter("date"));
        frameworkStructure.setLocation(request.getParameter("location"));
        frameworkStructure.setBasicallyIntactSquare(Double.valueOf(request.getParameter("basicallyIntactSquare")));
        frameworkStructure.setSlightDamagedSquare(Double.valueOf(request.getParameter("slightDamagedSquare")));
        frameworkStructure.setModerateDamagedSquare(Double.valueOf(request.getParameter("moderateDamagedSquare")));
        frameworkStructure.setSeriousDamagedSquare(Double.valueOf(request.getParameter("seriousDamagedSquare")));
        frameworkStructure.setDestroyedSquare(Double.valueOf(request.getParameter("destroyedSquare")));
        frameworkStructure.setNote(request.getParameter("note"));
        frameworkStructure.setReportingUnit(request.getParameter("reportingUnit"));
        frameworkStructure.setEarthquakeId(request.getParameter("earthquakeId"));
        frameworkStructureMapper.update(frameworkStructure);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/frameworkStructure/{id}")
    public PostVo delFrameworkStructure(@PathVariable("id")String id){
        frameworkStructureMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/frameworkStructureCopy/{time}")
    public List<FrameworkStructure> frameworkStructureCopy(@PathVariable("time") int time){
        List<FrameworkStructure> frameworkStructures = frameworkStructureMapper.getCopyFrameworkStructure(time*24);
        return frameworkStructures;
    }


    @GetMapping("/v1/singleFrameworkStructureInfo/{id}")
    public FrameworkStructure getFrameworkStructureInfoById(@PathVariable("id")String id){
        FrameworkStructure frameworkStructuresInfo = frameworkStructureMapper.getFrameworkStructureById(id);
        return frameworkStructuresInfo;
    }
}
