package com.baizhi.ww.service.impl;

import com.baizhi.ww.dao.AlbumDao;
import com.baizhi.ww.entity.Album;
import com.baizhi.ww.service.AlbumService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    AlbumDao albumDao;

    @Override
    public Album queryById(String id) {
        return this.albumDao.selectByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> queryAllByPage(Integer page, Integer rows) {
        HashMap hashMap = new HashMap();
        int i = albumDao.selectCount(null);
        Integer total = i%rows==0?i/rows:i/rows+1;
        List<Album> albums = albumDao.selectByRowBounds(null, new RowBounds((page - 1) * rows, rows));
        hashMap.put("records",i);
        hashMap.put("total",total);
        hashMap.put("rows",albums);
        hashMap.put("page",page);
        return hashMap;
    }

    @Override
    public Album insert(Album album) {
        album.setId(UUID.randomUUID().toString());
        album.setCreateDate(new Date());
        this.albumDao.insert(album);
        return album;
    }

    @Override
    public Album update(Album album) {
        /*if(album.getCover()==""){
            album.setCover(null);
            this.albumDao.updateByPrimaryKeySelective(album);
        }else {
            this.albumDao.updateByPrimaryKeySelective(album);
        }*/
        this.albumDao.updateByPrimaryKeySelective(album);
        return this.queryById(album.getId());
    }

    @Override
    public boolean deleteById(String id) {
        return this.albumDao.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public int deleteByIdList(List<String> list) {
        int i = albumDao.deleteByIdList(list);
        return i;
    }
    //查询所有专辑
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Album> queryAll() {
        List<Album> albums = albumDao.selectAll();
        return albums;
    }
}
