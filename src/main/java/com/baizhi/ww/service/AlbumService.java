package com.baizhi.ww.service;

import com.baizhi.ww.entity.Album;

import java.util.List;
import java.util.Map;

public interface AlbumService {
    Album queryById(String id);

    Map<String,Object> queryAllByPage(Integer page, Integer rows);

    Album insert(Album album);

    Album update(Album album);

    boolean deleteById(String id);

    int deleteByIdList(List<String> list);
    //查询所有专辑
    List<Album> queryAll();

}
