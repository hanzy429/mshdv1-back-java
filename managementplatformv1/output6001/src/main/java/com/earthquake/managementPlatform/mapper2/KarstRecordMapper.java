package com.earthquake.managementPlatform.mapper2;

import com.earthquake.managementPlatform.entities.KarstRecord;
import com.earthquake.managementPlatform.entities.SecondaryDisasterStatistics;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface KarstRecordMapper {

    @Insert("INSERT INTO `sentdata`.`karstrecord` (`ID`, `date`, `location`, `type`, `status`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{status},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(KarstRecord karstRecord);

}
