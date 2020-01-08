package com.baizhi.ww.service;

import com.baizhi.ww.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    Article queryById(String id);

    Map queryAll();

    Map queryAllByPage(Integer page, Integer rows);

    Article insert(Article article);

    Article update(Article article);

    boolean deleteById(String id);

    int deleteByIdList(List<String> list);
    //查询所有文章
    abstract List<Article> queryAll1();

}
