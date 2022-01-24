package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.FrameworkStructureMapper;
import com.earthquake.managementPlatform.mapper.OtherStructureMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@RestController
public class OtherStructureResource {
    @Resource
    OtherStructureMapper otherStructureMapper;
    @GetMapping("/v1/otherStructure")
    public GetVo otherStructureAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = otherStructureMapper.getAllOtherStructure().size();
        List<OtherStructure> otherStructure = otherStructureMapper.getOtherStructureByPage((page-1)*limit,limit);
        GetVo<OtherStructure> getVo = new GetVo<>(0,"获取数据成功！",size,otherStructure);
        return getVo;
    }

    @GetMapping("/v1/otherStructure/{time}")
    public GetVo otherStructureByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = otherStructureMapper.getRecentOtherStructure(timestamp).size();
        List<OtherStructure> otherStructures = otherStructureMapper.getRecentOtherStructureByPage((page-1)*limit,limit,timestamp);
        GetVo<OtherStructure> getVo = new GetVo<>(0,"获取数据成功！",size,otherStructures);
        return getVo;
    }

    @GetMapping("/v1/byhourotherStructurepicture")
    public  GetVo drawotheryStructurepicture(){

        int size1 = otherStructureMapper.getRecentHourOtherStructure(1).size();
        int size2= otherStructureMapper.getRecentHourOtherStructure(3).size();
        int size3= otherStructureMapper.getRecentHourOtherStructure(5).size();
        int size4= otherStructureMapper.getRecentHourOtherStructure(12).size();
        List<OtherStructure> otherStructures=otherStructureMapper.getRecentHourOtherStructure(12);
        List<Float> number=new LinkedList<>();
        float num1=0;
        float num2=0;
        float num3=0;
        float num4=0;
        for(int j=0;j<size1;j++){
            num1+=otherStructures.get(j).getDestroyedSquare();
            num1+=otherStructures.get(j).getSlightDamagedSquare();
            num1+=otherStructures.get(j).getModerateDamagedSquare();
            num1+=otherStructures.get(j).getSeriousDamagedSquare();
        }
        number.add(num1);
        for(int j=size1;j<size2;j++){
            num2+=otherStructures.get(j).getDestroyedSquare();
            num2+=otherStructures.get(j).getSlightDamagedSquare();
            num2+=otherStructures.get(j).getModerateDamagedSquare();
            num2+=otherStructures.get(j).getSeriousDamagedSquare();
        }
        number.add(num2);
        for(int j=size2;j<size3;j++){
            num3+=otherStructures.get(j).getDestroyedSquare();
            num3+=otherStructures.get(j).getSlightDamagedSquare();
            num3+=otherStructures.get(j).getModerateDamagedSquare();
            num3+=otherStructures.get(j).getSeriousDamagedSquare();
        }
        number.add(num3);
        for(int j=size3;j<size4;j++){
            num4+=otherStructures.get(j).getDestroyedSquare();
            num4+=otherStructures.get(j).getSlightDamagedSquare();
            num4+=otherStructures.get(j).getModerateDamagedSquare();
            num4+=otherStructures.get(j).getSeriousDamagedSquare();
        }
        number.add(num4);

        GetVo<Float> getVo=new GetVo<>(0,"获取数据成功！",4,number);
        return getVo;
    }


    @GetMapping("/v1/byhourotherStructure/{time}")
    public GetVo otherStructureByHour(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = otherStructureMapper.getRecentOtherStructure(time).size();
        List<OtherStructure> otherStructures =otherStructureMapper.getRecentHourOtherStructureByPage((page-1)*limit,limit,time);
        GetVo<OtherStructure> getVo = new GetVo<>(0,"获取数据成功！",size,otherStructures);
        return  getVo;
    }

    @GetMapping("/v1/lastOtherStructureByTime")
    public GetVo getLastOtherStructureByTime(){
        List<SquareStatistics> squareStatistics = otherStructureMapper.getLastOtherStructureByTime();
        GetVo<SquareStatistics> getVo = new GetVo<>(0,"获取数据成功！",squareStatistics.size(),squareStatistics);
        return getVo;
    }

    @GetMapping("/v1/lastOtherStructure")
    public GetVo getLastOtherStructure(){
        List<OtherStructure> otherStructure = otherStructureMapper.getLastOtherStructure();
        GetVo<OtherStructure> getVo = new GetVo<>(0,"获取数据成功！",otherStructure.size(),otherStructure);
        return getVo;
    }

    @PutMapping("/v1/otherStructure/{id}")
    public PostVo editOtherStructure(HttpServletRequest request, @PathVariable("id") String id){
        OtherStructure otherStructure = new OtherStructure();
        otherStructure.setId(id);
        otherStructure.setDate(request.getParameter("date"));
        otherStructure.setLocation(request.getParameter("location"));
        otherStructure.setBasicallyIntactSquare(Double.valueOf(request.getParameter("basicallyIntactSquare")));
        otherStructure.setSlightDamagedSquare(Double.valueOf(request.getParameter("slightDamagedSquare")));
        otherStructure.setModerateDamagedSquare(Double.valueOf(request.getParameter("moderateDamagedSquare")));
        otherStructure.setSeriousDamagedSquare(Double.valueOf(request.getParameter("seriousDamagedSquare")));
        otherStructure.setDestroyedSquare(Double.valueOf(request.getParameter("destroyedSquare")));
        otherStructure.setNote(request.getParameter("note"));
        otherStructure.setReportingUnit(request.getParameter("reportingUnit"));
        otherStructure.setEarthquakeId(request.getParameter("earthquakeId"));
        otherStructureMapper.update(otherStructure);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/otherStructure/{id}")
    public PostVo delOtherStructure(@PathVariable("id")String id){
        otherStructureMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/otherStructureCopy/{time}")
    public List<OtherStructure> otherStructureCopy(@PathVariable("time") int time){
        List<OtherStructure> otherStructures = otherStructureMapper.getCopyOtherStructure(time*24);
        return otherStructures;
    }


    @GetMapping("/v1/singleOtherStructureInfo/{id}")
    public OtherStructure getOtherStructureInfoById(@PathVariable("id")String id){
        OtherStructure otherStructuresInfo = otherStructureMapper.getOtherStructureById(id);
        return otherStructuresInfo;
    }
}
