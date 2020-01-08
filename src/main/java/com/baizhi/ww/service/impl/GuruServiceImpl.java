package com.baizhi.ww.service.impl;

import com.baizhi.ww.dao.GuruDao;
import com.baizhi.ww.entity.Guru;
import com.baizhi.ww.service.GuruService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class GuruServiceImpl implements GuruService {
    @Autowired
    GuruDao guruDao;
    @Override
    public Guru queryById(String id) {
        return this.guruDao.selectByPrimaryKey(id);
    }

    @Override
    public Map queryAllMap() {
        Map<String, Object> map = new HashMap<>();
        List<Guru> guruList = guruDao.selectAll();
        map.put("rows",guruList);
        return map;
    }

    @Override
    public List<Guru> queryAll() {
        List<Guru> guruList = guruDao.selectAll();
        return guruList;
    }

    @Override
    public Map queryAllByPage(Integer page, Integer rows) {
        Map map = new HashMap<>();
        //获取数据库中的起始条
        Integer begin=(page-1)*rows;
        //轮播图分页查
        List<Guru> bannerList = guruDao.selectByRowBounds(null,new RowBounds(begin,rows));
        //查询出总条数
        Integer records = guruDao.selectCount(null);
        //总页数
        Integer total=records%rows==0 ? records/rows : records/rows+1;
        map.put("total",total);     //总页数
        map.put("records",records); //总条数
        map.put("page",page);       //当前页
        map.put("rows",bannerList);//轮播图分页查
        return map;
    }

    @Override
    public Guru insert(Guru guru) {
        guru.setId(UUID.randomUUID().toString());
        this.guruDao.insert(guru);
        return guru;
    }

    @Override
    public Guru update(Guru guru) {
        this.guruDao.updateByPrimaryKeySelective(guru);
        return this.queryById(guru.getId());
    }

    @Override
    public boolean deleteById(String id) {
        return this.guruDao.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public int deleteByIdList(List<String> list) {
        int i = guruDao.deleteByIdList(list);
        return i;
    }
    //查询所有上师
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Guru> queryAll1() {
        List<Guru> gurus = guruDao.selectAll();
        return gurus;
    }
}
