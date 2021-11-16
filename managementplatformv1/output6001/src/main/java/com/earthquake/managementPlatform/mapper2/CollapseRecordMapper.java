package com.earthquake.managementPlatform.mapper2;

import com.earthquake.managementPlatform.entities.CollapseRecord;
import com.earthquake.managementPlatform.entities.SecondaryDisasterStatistics;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface CollapseRecordMapper {


    @Insert("INSERT INTO `sentdata`.`collapserecord` (`ID`, `date`, `location`, `type`, `status`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{status},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(CollapseRecord collapseRecord);


}
