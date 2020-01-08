package com.baizhi.ww.service.impl;

import com.baizhi.ww.aspect.LogAnnotation;
import com.baizhi.ww.dao.BannerDao;
import com.baizhi.ww.entity.Banner;
import com.baizhi.ww.service.BannerService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;
    @Override
    public Banner queryById(String id) {
        return this.bannerDao.selectByPrimaryKey(id);
    }

    @Override
    public Map<String,Object> queryAllByPage(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        //获取数据库中的起始条
        Integer begin=(page-1)*rows;
        //轮播图分页查
        List<Banner> bannerList = bannerDao.selectByRowBounds(new Banner(),new RowBounds(begin,rows));
        //查询出总条数
        Integer records = bannerDao.selectCount(new Banner());
        //总页数
        Integer total=records%rows==0 ? records/rows : records/rows+1;
        map.put("total",total);     //总页数
        map.put("records",records); //总条数
        map.put("page",page);       //当前页
        map.put("rows",bannerList);//轮播图分页查
        return map;

    }

    /**
     * 新增数据
     *
     * @param banner 实例对象
     * @return 实例对象
     */
    @Override
    @LogAnnotation(value = "添加Banner")
    public Banner insert(Banner banner) {
        banner.setId(UUID.randomUUID().toString());
        banner.setCreateDate(new Date());
        this.bannerDao.insert(banner);
        return banner;
    }

    /**
     * 修改数据
     *
     * @param banner 实例对象
     * @return 实例对象
     */
    @Override
    @LogAnnotation(value = "修改Banner")
    public Banner update(Banner banner) {
        if(banner.getUrl()==""){
            banner.setUrl(null);
            this.bannerDao.updateByPrimaryKeySelective(banner);
        }else {
            this.bannerDao.updateByPrimaryKeySelective(banner);
        }
        return this.queryById(banner.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.bannerDao.deleteByPrimaryKey(id) > 0;
    }
    //批量删除
    @Override
    @LogAnnotation(value = "删除Banner")
    public int deleteByIdList(List<String> list) {
        int i = bannerDao.deleteByIdList(list);
        return i;
    }
    //查询所有轮播图
    public List<Banner> queryAll() {
        List<Banner> banners = bannerDao.selectAll();
        return banners;
    }

}