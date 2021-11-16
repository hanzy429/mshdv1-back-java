package com.earthquake.managementPlatform.mapper2;

import com.earthquake.managementPlatform.entities.BrickwoodStructure;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface BrickwoodStructureMapper {


    @Insert("INSERT INTO `sentdata`.`brickwoodstructure` (`ID`, `date`, `location`, `basically_intact_square`, `damaged_square`, `destroyed_square`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{basicallyIntactSquare},#{damagedSquare},#{destroyedSquare},#{note},#{reportingUnit},#{earthquakeId})")
    int save(BrickwoodStructure brickwoodStructure);


}
