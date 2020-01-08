package com.baizhi.ww.service;

import com.baizhi.ww.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {
    User queryById(String id);

    Map queryAll();

    Map queryAllByPage(Integer page, Integer rows);

    User insert(User user);

    User update(User user);

    boolean deleteById(String id);

    int deleteByIdList(List<String> list);

    User queryOne(User user);

    User update1(User user);

    //随机查询五个用户，除自己
    Set<User> queryAll(String id);

}
