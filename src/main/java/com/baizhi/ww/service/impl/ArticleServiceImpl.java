package com.baizhi.ww.service.impl;

import com.baizhi.ww.dao.ArticleDao;
import com.baizhi.ww.entity.Article;
import com.baizhi.ww.service.ArticleService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleDao articleDao;
    @Override
    public Article queryById(String id) {
        Article article = articleDao.selectByPrimaryKey(id);
        return article;
    }

    @Override
    public Map queryAllByPage(Integer page, Integer rows) {
        Map map = new HashMap<>();
        //获取数据库中的起始条
        Integer begin=(page-1)*rows;
        //轮播图分页查
        List<Article> bannerList = articleDao.selectByRowBounds(null,new RowBounds(begin,rows));
        //查询出总条数
        Integer records = articleDao.selectCount(null);
        //总页数
        Integer total=records%rows==0 ? records/rows : records/rows+1;
        map.put("total",total);     //总页数
        map.put("records",records); //总条数
        map.put("page",page);       //当前页
        map.put("rows",bannerList);//轮播图分页查
        return map;
    }

    @Override
    public Map queryAll() {
        Map<String, Object> map = new HashMap<>();
        List<Article> articleList = articleDao.selectAll();
        map.put("rows",articleList);
        return map;
    }



    @Override
    public Article insert(Article article) {
        article.setId(UUID.randomUUID().toString());
        article.setCreateDate(new Date());
        article.setPublishDate(new Date());
        this.articleDao.insert(article);
        return article;
    }

    @Override
    public Article update(Article article) {
        this.articleDao.updateByPrimaryKeySelective(article);
        return this.queryById(article.getId());
    }

    @Override
    public boolean deleteById(String id) {
        return this.articleDao.deleteByPrimaryKey(id) > 0;
    }

    //批量删除
    @Override
    public int deleteByIdList(List<String> list) {
        int i = articleDao.deleteByIdList(list);
        return i;
    }
    //查询所有文章
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Article> queryAll1() {
        List<Article> articles = articleDao.selectAll();
        return articles;
    }
}
