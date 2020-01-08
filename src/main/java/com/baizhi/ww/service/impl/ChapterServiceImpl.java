package com.baizhi.ww.service.impl;

import com.baizhi.ww.dao.ChapterDao;
import com.baizhi.ww.entity.Chapter;
import com.baizhi.ww.service.ChapterService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    ChapterDao chapterDao;

    @Override
    public Chapter queryById(String id) {
        return this.chapterDao.selectByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> queryAllByPage(Integer page, Integer rows,String albumId) {
        HashMap hashMap = new HashMap();
        Chapter chapter = new Chapter();
        chapter.setAlbumId(albumId);
        int i = chapterDao.selectCount(chapter);
        Integer total = i%rows==0?i/rows:i/rows+1;
        List<Chapter> chapterDaos = chapterDao.selectByRowBounds(chapter, new RowBounds((page - 1) * rows, rows));
        hashMap.put("records",i);
        hashMap.put("page",page);
        hashMap.put("total",total);
        hashMap.put("rows",chapterDaos);
        return hashMap;
    }

    @Override
    public Chapter insert(Chapter chapter) {
        chapter.setId(UUID.randomUUID().toString());
        chapter.setCreateTime(new Date());
        this.chapterDao.insert(chapter);
        return chapter;
    }

    @Override
    public Chapter update(Chapter chapter) {
        if(chapter.getUrl()==""){
            chapter.setUrl(null);
            this.chapterDao.updateByPrimaryKeySelective(chapter);
        }else {
            this.chapterDao.updateByPrimaryKeySelective(chapter);
        }
        return this.queryById(chapter.getId());
    }

    @Override
    public boolean deleteById(String id) {
        return this.chapterDao.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public int deleteByIdList(List<String> list) {
        int i = chapterDao.deleteByIdList(list);
        return i;
    }
}
