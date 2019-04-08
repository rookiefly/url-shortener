package com.rookiefly.open.urlshortener.mapper;

import com.rookiefly.open.urlshortener.model.Links;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface LinksMapper {

    @Select("select * from links where keyword = #{keyword}")
    Links findByKeyword(@Param("keyword") String keyword);

    @Select("select * from links where url = #{url}")
    Links findByUrl(@Param("url") String url);

    @Insert("insert into links(url, keyword) values(#{url}, #{keyword})")
    //添加该行，links中的id将被自动设置
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertLinks(Links links);

    @Update("update links set keyword=#{keyword} WHERE id=#{id}")
    int update(@Param("keyword") String keyword, @Param("id") Long id);
}