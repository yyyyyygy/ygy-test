package org.test.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.test.po.AuthInfo;
import org.test.vo.AddUserVO;

import java.io.IOException;
import java.util.List;

public interface UserDao {
//    @Select("select count(1) from auth_table where user_id = #{userId} and resource = #{resource} and status = 1")
     Boolean hasAuth( Long userId,String resource);

//    @Insert({"<script>",
//            "INSERT INTO auth_table (user_id, resource,status) VALUES",
//            "<foreach collection='list.txt' item='item' index='index' separator=','>",
//            "(#{item.userId}, #{item.resource},#{item.status})",
//            "</foreach>",
//            "ON DUPLICATE KEY UPDATE status = 1",
//            "</script>"})
    Boolean insert(List<AuthInfo> list) ;
}
