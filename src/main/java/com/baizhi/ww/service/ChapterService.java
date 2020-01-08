package com.baizhi.ww.service;

import com.baizhi.ww.entity.Chapter;

import java.util.List;
import java.util.Map;

public interface ChapterService {

    Chapter queryById(String id);

    Map<String,Object> queryAllByPage(Integer page, Integer rows,String id);

    Chapter insert(Chapter chapter);

    Chapter update(Chapter chapter);

    boolean deleteById(String id);

    int deleteByIdList(List<String> list);
}
