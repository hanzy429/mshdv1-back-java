package com.earthquake.managementPlatform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OnOffMapper {
    @Select("SELECT status FROM earthquake.on_off where name = \"ReadingFiles\";")
    boolean getReadingFiles();
    @Update("UPDATE `earthquake`.`on_off` SET `status` = '1' WHERE (`name` = 'ReadingFiles');")
    int StartReadingFiles();
    @Update("UPDATE `earthquake`.`on_off` SET `status` = '0' WHERE (`name` = 'ReadingFiles');")
    int StopReadingFiles();

}
