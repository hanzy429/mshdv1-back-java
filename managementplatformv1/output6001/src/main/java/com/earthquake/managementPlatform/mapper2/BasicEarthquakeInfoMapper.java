package com.earthquake.managementPlatform.mapper2;


import com.earthquake.managementPlatform.entities.BasicEarthquakeInfo;
import com.earthquake.managementPlatform.entities.ProvEarthquakeFrequency;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface BasicEarthquakeInfoMapper{



    @Insert("INSERT INTO `sentdata`.`disasterinfo` (`D_ID`, `date`, `location`, `longitude`, `latitude`, `depth`, `magnitude`, `picture`, `reporting_unit`) VALUES (#{id},#{date},#{location},#{longitude},#{latitude},#{depth},#{magnitude},#{picture},#{reportingUnit})")
    int save(BasicEarthquakeInfo basicEarthquakeInfo);

}
