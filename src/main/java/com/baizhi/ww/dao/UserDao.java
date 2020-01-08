package com.baizhi.ww.dao;

import com.baizhi.ww.DTO.UserDTO;
import com.baizhi.ww.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDao extends Mapper<User>, InsertListMapper<User>, DeleteByIdListMapper<User,String> {
    Integer queryUserByTime(@Param("sex") String sex, @Param("day") Integer day);
    List<UserDTO> queryUserBySexLocation(String sex);
}
