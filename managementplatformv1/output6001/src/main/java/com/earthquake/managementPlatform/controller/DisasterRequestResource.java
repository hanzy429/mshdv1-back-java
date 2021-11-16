
package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.DisasterRequestMapper;
import com.earthquake.managementPlatform.service.OutPutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.earthquake.managementPlatform.mapper2.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
public class DisasterRequestResource {
    @Resource
    DisasterRequestMapper disasterRequestMapper;
    @Resource
    RestTemplate restTemplate;
    @Autowired
    DeathStatisticsMapper deathStatisticsMapper;
    @Autowired
    InjuredStatisticsMapper injuredStatisticsMapper;
    @Autowired
    MissingStatisticsMapper missingStatisticsMapper;
    @Autowired
    CivilStructureMapper civilStructureMapper;
    @Autowired
    BrickwoodStructureMapper brickwoodStructureMapper;
    @Autowired
    MasonryStructureMapper masonryStructureMapper;
    @Autowired
    FrameworkStructureMapper frameworkStructureMapper;
    @Autowired
    OtherStructureMapper otherStructureMapper;
    @Autowired
    TrafficDisasterMapper trafficDisasterMapper;
    @Autowired
    WaterDisasterMapper waterDisasterMapper;
    @Autowired
    OilDisasterMapper oilDisasterMapper;
    @Autowired
    GasDisasterMapper gasDisasterMapper;
    @Autowired
    PowerDisasterMapper powerDisasterMapper;
    @Autowired
    CommDisasterMapper commDisasterMapper;
    @Autowired
    IrrigationDisasterMapper irrigationDisasterMapper;
    @Autowired
    CollapseRecordMapper collapseRecordMapper;
    @Autowired
    LandslideRecordMapper landslideRecordMapper;
    @Autowired
    DebrisRecordMapper debrisRecordMapper;
    @Autowired
    KarstRecordMapper karstRecordMapper;
    @Autowired
    CrackRecordMapper crackRecordMapper;
    @Autowired
    SettlementRecordMapper settlementRecordMapper;
    @Autowired
    OtherRecordMapper otherRecordMapper;
    @Autowired
    BasicEarthquakeInfoMapper basicEarthquakeInfoMapper;
    @Value("${storageInformation.url}")
    private String storageInformationUrl;

    @Resource
    OutPutService outPutService;

    @GetMapping("/v1/disasterRequest")
    public GetVo disasterInfoAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = disasterRequestMapper.getAllDisasterRequest().size();
        List<DisasterRequest> disasterInfos = disasterRequestMapper.getDisasterRequestByPage((page-1)*limit,limit);
        GetVo<DisasterRequest> getVo = new GetVo<>(0,"获取数据成功！",size,disasterInfos);
        return getVo;
    }

    @PutMapping("/v1/disasterRequest/{id}/{categoryId}")
    public PostVo editDisasterRequest(@PathVariable("id") int id, @PathVariable("categoryId") String categoryId) throws IOException {
        String code = restTemplate.getForObject(storageInformationUrl+"/v1/resource/"+categoryId,String.class);
        boolean flag1 = outPutService.output(categoryId,code);
        String codeDescription = restTemplate.getForObject(storageInformationUrl+"/v1/resourceDescription/"+categoryId,String.class);
        boolean flag2 = outPutService.output(categoryId+"Description",codeDescription);
        PostVo postVo;
        if(flag1&&flag2)
        {
            disasterRequestMapper.updateById(id);
            postVo = new PostVo(1,"发送成功！",null);
            return postVo;
        }
        else
            postVo = new PostVo(0,"发送失败！",null);
        return postVo;
    }


    @PutMapping("/v1/disasterSqlRequest/{id}/{categoryId}")
    public PostVo sentDataSql(@PathVariable("id") int id, @PathVariable("categoryId") String categoryId) throws IOException {
        String code = restTemplate.getForObject(storageInformationUrl+"/v1/resource/"+categoryId,String.class);
        //boolean flag1 = outPutService.output(categoryId,code);
        int flag2=0;
        if(categoryId.equals("111")){

            //code = deathStatisticsMapper.getNewCode();
            DeathStatistics deathStatistics=restTemplate.getForObject(storageInformationUrl+"/v1/singleDeathStatisticsInfo/"+code,DeathStatistics.class);
            flag2 = deathStatisticsMapper.save(deathStatistics);
        }
        else if(categoryId.equals("112")){
            //code = injuredStatisticsMapper.getNewCode();
            InjuredStatistics injuredStatistics=restTemplate.getForObject(storageInformationUrl+"/v1/singleInjuredStatisticsInfo/"+code,InjuredStatistics.class);
            flag2=injuredStatisticsMapper.save(injuredStatistics);
        }
        else if(categoryId.equals("113")){
            //code = missingStatisticsMapper.getNewCode();
            MissingStatistics missingStatistics=restTemplate.getForObject(storageInformationUrl+"/v1/singleMissingStatisticsInfo/"+code,MissingStatistics.class);
            flag2=missingStatisticsMapper.save(missingStatistics);
        }
        else if(categoryId.equals("221")){
            //code = civilStructureMapper.getNewCode();
            CivilStructure civilStructure=restTemplate.getForObject(storageInformationUrl+"/v1/singleCivilStructureInfo/"+code,CivilStructure.class);
            flag2=civilStructureMapper.save(civilStructure);
        }
        else if(categoryId.equals("222")){
            //code = brickwoodStructureMapper.getNewCode();
            BrickwoodStructure brickwoodStructure=restTemplate.getForObject(storageInformationUrl+"/v1/singleBrickwoodStructureInfo/"+code,BrickwoodStructure.class);
            flag2=brickwoodStructureMapper.save(brickwoodStructure);
        }
        else if(categoryId.equals("223")){
            //code = masonryStructureMapper.getNewCode();
            MasonryStructure masonryStructure=restTemplate.getForObject(storageInformationUrl+"/v1/singleMasonryStructureInfo/"+code,MasonryStructure.class);
            flag2= masonryStructureMapper.save(masonryStructure);
        }
        else if(categoryId.equals("224")){
            //code = frameworkStructureMapper.getNewCode();
            FrameworkStructure frameworkStructure =restTemplate.getForObject(storageInformationUrl+"/v1/singleFrameworkStructureInfo/"+code,FrameworkStructure.class);
            flag2=frameworkStructureMapper.save(frameworkStructure);
        }
        else if(categoryId.equals("225")){
            //code = otherStructureMapper.getNewCode();
            OtherStructure otherStructure=restTemplate.getForObject(storageInformationUrl+"/v1/singleOtherStructureInfo/"+code,OtherStructure.class);
            flag2=otherStructureMapper.save(otherStructure);
        }
        else if(categoryId.equals("331")){
            //code = trafficDisasterMapper.getNewCode();
            TrafficDisaster trafficDisaster=restTemplate.getForObject(storageInformationUrl+"/v1/singleTrafficDisasterInfo/"+code,TrafficDisaster.class);
            flag2=trafficDisasterMapper.save(trafficDisaster);
        }
        else if(categoryId.equals("332")){
            //code = waterDisasterMapper.getNewCode();
            WaterDisaster waterDisaster=restTemplate.getForObject(storageInformationUrl+"/v1/singleWaterDisasterInfo/"+code,WaterDisaster.class);
            flag2= waterDisasterMapper.save(waterDisaster);
        }
        else if(categoryId.equals("333")){
            //code = oilDisasterMapper.getNewCode();
            OilDisaster oilDisaster=restTemplate.getForObject(storageInformationUrl+"/v1/singleOilDisasterInfo/"+code,OilDisaster.class);
            flag2= oilDisasterMapper.save(oilDisaster);
        }
        else if(categoryId.equals("334")){
            //code = gasDisasterMapper.getNewCode();
            GasDisaster gasDisaster=restTemplate.getForObject(storageInformationUrl+"/v1/singleGasDisasterInfo/"+code,GasDisaster.class);
            flag2 = gasDisasterMapper.save(gasDisaster);
        }
        else if(categoryId.equals("335")){
            //code = powerDisasterMapper.getNewCode();
            PowerDisaster powerDisaster=restTemplate.getForObject(storageInformationUrl+"/v1/singlePowerDisasterInfo/"+code,PowerDisaster.class);
            flag2= powerDisasterMapper.save(powerDisaster);
        }
        else if(categoryId.equals("336")){
            //code = commDisasterMapper.getNewCode();
            CommDisaster commDisaster=restTemplate.getForObject(storageInformationUrl+"/v1/singleCommDisasterInfo/"+code,CommDisaster.class);
            flag2= commDisasterMapper.save(commDisaster);
        }
        else if(categoryId.equals("337")){
            //code = irrigationDisasterMapper.getNewCode();
            IrrigationDisaster irrigationDisaster=restTemplate.getForObject(storageInformationUrl+"/v1/singleIrrigationDisasterInfo/"+code,IrrigationDisaster.class);
            flag2= irrigationDisasterMapper.save(irrigationDisaster);
        }
        else if(categoryId.equals("441")){
            //code = collapseRecordMapper.getNewCode();
            CollapseRecord collapseRecord=restTemplate.getForObject(storageInformationUrl+"/v1/singleCollapseRecordInfo/"+code,CollapseRecord.class);
            flag2= collapseRecordMapper.save(collapseRecord);
        }
        else if(categoryId.equals("442")){
            //code = landslideRecordMapper.getNewCode();
            LandslideRecord landslideRecord=restTemplate.getForObject(storageInformationUrl+"/v1/singleLandslideRecordInfo/"+code,LandslideRecord.class);
            flag2= landslideRecordMapper.save(landslideRecord);
        }
        else if(categoryId.equals("443")){
            //code = debrisRecordMapper.getNewCode();
            DebrisRecord debrisRecord=restTemplate.getForObject(storageInformationUrl+"/v1/singleDebrisRecordInfo/"+code,DebrisRecord.class);
            flag2=debrisRecordMapper.save(debrisRecord);
        }
        else if(categoryId.equals("444")){
            //code = karstRecordMapper.getNewCode();
            KarstRecord karstRecord=restTemplate.getForObject(storageInformationUrl+"/v1/singleKarstRecordInfo/"+code,KarstRecord.class);
            flag2= karstRecordMapper.save(karstRecord);
        }
        else if(categoryId.equals("445")){
            //code = crackRecordMapper.getNewCode();
            CrackRecord crackRecord=restTemplate.getForObject(storageInformationUrl+"/v1/singleCrackRecordInfo/"+code,CrackRecord.class);
            flag2= crackRecordMapper.save(crackRecord);
        }
        else if(categoryId.equals("446")){
            //code = settlementRecordMapper.getNewCode();
            SettlementRecord settlementRecord=restTemplate.getForObject(storageInformationUrl+"/v1/singleSettlementRecordInfo/"+code,SettlementRecord.class);
            flag2= settlementRecordMapper.save(settlementRecord);
        }
        else if(categoryId.equals("447")){
            //code = otherRecordMapper.getNewCode();
            OtherRecord otherRecord=restTemplate.getForObject(storageInformationUrl+"/v1/singleOtherRecordInfo/"+code,OtherRecord.class);
            flag2= otherRecordMapper.save(otherRecord);
        }
        else if(categoryId.equals("551")){
            //code = basicEarthquakeInfoMapper.getNewCode();
            BasicEarthquakeInfo basicEarthquakeInfo=restTemplate.getForObject(storageInformationUrl+"/v1/singleDisasterInfo/"+code,BasicEarthquakeInfo.class);
            flag2= basicEarthquakeInfoMapper.save(basicEarthquakeInfo);
        }
        PostVo postVo;
        if(flag2==1)
        {
            disasterRequestMapper.updateById(id);
            postVo = new PostVo(1,"发送成功！",null);
            return postVo;
        }
        else
        {
            disasterRequestMapper.updateById(id);
            postVo = new PostVo(0,"发送失败！",null);
        }

        return postVo;
    }


    @PostMapping("/v1/disasterRequest")
    public PostVo addDisasterRequest(HttpServletRequest request)
    {
        PostVo postVo;
        String date = request.getParameter("date");
        String disasterType = request.getParameter("disasterType");
        String oURL = request.getParameter("oURL");
        String requestUnit = request.getParameter("requestUnit");
        DisasterRequest disasterRequest = new DisasterRequest(date,disasterType,"0",oURL,requestUnit);
        int result = disasterRequestMapper.save(disasterRequest);
        if(result == 1)
            postVo = new PostVo(1,"请求录入成功",null);
        else
            postVo = new PostVo(0,"请求录入失败",null);
        return postVo;
    }

    @GetMapping("/v1/processedDisasterRequest")
    public GetVo getProcessedDisasterRequest(HttpServletRequest request)
    {
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = disasterRequestMapper.getProcessedDisasterRequest().size();
        List<DisasterRequest> disasterRequest = disasterRequestMapper.getProcessedDisasterRequestByPage((page-1)*limit,limit);
        GetVo<DisasterRequest> getVo = new GetVo<>(0,"获取数据成功！",size,disasterRequest);
        return getVo;
    }

    @GetMapping("/v1/noProcessedDisasterRequest")
    public GetVo getNoProcessedDisasterRequest(HttpServletRequest request)
    {
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = disasterRequestMapper.getNoProcessedDisasterRequest().size();
        List<DisasterRequest> disasterRequest = disasterRequestMapper.getNoProcessedDisasterRequestByPage((page-1)*limit,limit);
        GetVo<DisasterRequest> getVo = new GetVo<>(0,"获取数据成功！",size,disasterRequest);
        return getVo;
    }

    @DeleteMapping("/v1/disasterRequest/{id}")
    public PostVo delDisasterRequest(@PathVariable("id")int id){
        disasterRequestMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

}
