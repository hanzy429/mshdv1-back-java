package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.BrickwoodStructureMapper;
import com.earthquake.managementPlatform.mapper.CivilStructureMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@RestController
public class BrickwoodStructureResource {
    @Resource
    BrickwoodStructureMapper brickwoodStructureMapper;
    @GetMapping("/v1/brickwoodStructure")
    public GetVo brickwoodStructureAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = brickwoodStructureMapper.getAllBrickwoodStructure().size();
        List<BrickwoodStructure> brickwoodStructure = brickwoodStructureMapper.getBrickwoodStructureByPage((page-1)*limit,limit);
        GetVo<BrickwoodStructure> getVo = new GetVo<>(0,"获取数据成功！",size,brickwoodStructure);
        return getVo;
    }

    @GetMapping("/v1/brickwoodStructure/{time}")
    public GetVo brickwoodStructureByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = brickwoodStructureMapper.getRecentBrickwoodStructure(timestamp).size();
        List<BrickwoodStructure> brickwoodStructures = brickwoodStructureMapper.getRecentBrickwoodStructureByPage((page-1)*limit,limit,timestamp);
        GetVo<BrickwoodStructure> getVo = new GetVo<>(0,"获取数据成功！",size,brickwoodStructures);
        return getVo;

    }

    @GetMapping("/v1/byhourbrickwoodStructure/{time}")
    public GetVo brickwoodStructureByHour(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = brickwoodStructureMapper.getRecentHourBrickwoodStructure(time).size();
        List<BrickwoodStructure> brickwoodStructures =brickwoodStructureMapper.getRecentHourBrickwoodStructureByPage((page-1)*limit,limit,time);
        GetVo<BrickwoodStructure> getVo = new GetVo<>(0,"获取数据成功！",size,brickwoodStructures);
        return  getVo;
    }

    @GetMapping("/v1/byhourbrickwoodStructurepicture")
    public  GetVo drawbrickwoodStructurepicture(){

        int size1 = brickwoodStructureMapper.getRecentHourBrickwoodStructure(1).size();
        int size2= brickwoodStructureMapper.getRecentHourBrickwoodStructure(3).size();
        int size3= brickwoodStructureMapper.getRecentHourBrickwoodStructure(5).size();
        int size4= brickwoodStructureMapper.getRecentHourBrickwoodStructure(12).size();
        List<BrickwoodStructure> brickwoodStructures=brickwoodStructureMapper.getRecentHourBrickwoodStructure(12);
        List<Float> number=new LinkedList<>();
        float num1=0;
        float num2=0;
        float num3=0;
        float num4=0;
        for(int j=0;j<size1;j++){
            num1+=brickwoodStructures.get(j).getDestroyedSquare();
            num1+=brickwoodStructures.get(j).getDamagedSquare();
        }
        number.add(num1);
        for(int j=size1;j<size2;j++){
            num2+=brickwoodStructures.get(j).getDestroyedSquare();
            num2+=brickwoodStructures.get(j).getDamagedSquare();
        }
        number.add(num2);
        for(int j=size2;j<size3;j++){
            num3+=brickwoodStructures.get(j).getDestroyedSquare();
            num3+=brickwoodStructures.get(j).getDamagedSquare();
        }
        number.add(num3);
        for(int j=size3;j<size4;j++){
            num4+=brickwoodStructures.get(j).getDestroyedSquare();
            num4+=brickwoodStructures.get(j).getDamagedSquare();
        }
        number.add(num4);

        GetVo<Float> getVo=new GetVo<>(0,"获取数据成功！",4,number);
        return getVo;
    }

    @GetMapping("/v1/lastBrickwoodStructureByTime")
    public GetVo getLastBrickwoodStructureByTime(){
        List<SquareStatistics> squareStatistics = brickwoodStructureMapper.getLastBrickwoodStructureByTime();
        GetVo<SquareStatistics> getVo = new GetVo<>(0,"获取数据成功！",squareStatistics.size(),squareStatistics);
        return getVo;
    }

    @GetMapping("/v1/lastBrickwoodStructure")
    public GetVo getLastBrickwoodStructure(){
        List<BrickwoodStructure> brickwoodStructure = brickwoodStructureMapper.getLastBrickwoodStructure();
        GetVo<BrickwoodStructure> getVo = new GetVo<>(0,"获取数据成功！",brickwoodStructure.size(),brickwoodStructure);
        return getVo;
    }

    @PutMapping("/v1/brickwoodStructure/{id}")
    public PostVo editBrickwoodStructure(HttpServletRequest request, @PathVariable("id") String id){
        BrickwoodStructure brickwoodStructure = new BrickwoodStructure();
        brickwoodStructure.setId(id);
        brickwoodStructure.setDate(request.getParameter("date"));
        brickwoodStructure.setLocation(request.getParameter("location"));
        brickwoodStructure.setBasicallyIntactSquare(Double.valueOf(request.getParameter("basicallyIntactSquare")));
        brickwoodStructure.setDamagedSquare(Double.valueOf(request.getParameter("damagedSquare")));
        brickwoodStructure.setDestroyedSquare(Double.valueOf(request.getParameter("destroyedSquare")));
        brickwoodStructure.setNote(request.getParameter("note"));
        brickwoodStructure.setReportingUnit(request.getParameter("reportingUnit"));
        brickwoodStructure.setEarthquakeId(request.getParameter("earthquakeId"));
        brickwoodStructureMapper.update(brickwoodStructure);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/brickwoodStructure/{id}")
    public PostVo delBrickwoodStructure(@PathVariable("id")String id){
        brickwoodStructureMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/brickwoodStructureCopy/{time}")
    public List<BrickwoodStructure> brickwoodStructureCopy(@PathVariable("time") int time){
        List<BrickwoodStructure> brickwoodStructures = brickwoodStructureMapper.getCopyBrickwoodStructure(time*24);
        return brickwoodStructures;
    }

    @GetMapping("/v1/singleBrickwoodStructureInfo/{id}")
    public BrickwoodStructure getCivilStructureInfoById(@PathVariable("id")String id){
        BrickwoodStructure brickwoodStructuresInfo = brickwoodStructureMapper.getBrickwoodStructureById(id);
        return brickwoodStructuresInfo;
    }
}
