package com.earthquake.managementPlatform.mapper2;

import com.earthquake.managementPlatform.entities.MissingStatistics;
import com.earthquake.managementPlatform.entities.PersonStatistics;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface MissingStatisticsMapper {


    @Insert("INSERT INTO `sentdata`.`missingstatistics` (`ID`, `location`, `date`, `number`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{location},#{date},#{number},#{reportingUnit},#{earthquakeId})")
    int save(MissingStatistics missingStatistics);

    public List<MissingStatistics> getCopyMissingStatistics(@Param("time") int time);
}
