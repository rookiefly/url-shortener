package com.rookiefly.open.urlshortener.mapper;

import com.rookiefly.open.urlshortener.model.Links;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LinksMapper {

    @Select("select * from links where keyword = #{keyword}")
    Links findByKeyword(@Param("keyword") String keyword);

}