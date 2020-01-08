package com.baizhi.ww.service;

import com.baizhi.ww.entity.Banner;

import java.util.List;
import java.util.Map;

public interface BannerService {

    Banner queryById(String id);

    public Map<String,Object> queryAllByPage(Integer page, Integer rows);

    Banner insert(Banner banner);

    Banner update(Banner banner);

    boolean deleteById(String id);

    int deleteByIdList(List<String> list);
    //查询所有轮播图
    public abstract List<Banner> queryAll();
}
