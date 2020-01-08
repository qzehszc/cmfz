package com.baizhi.ww.service.impl;


import com.baizhi.ww.dao.AttentionDao;
import com.baizhi.ww.entity.Attention;
import com.baizhi.ww.service.AttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AttentionServiceImpl implements AttentionService {
    @Autowired
    AttentionDao attentionDao;

//    ---------------------------------------------用户和上师中间表-----------------------------------------------------------------------------
    public List<Attention> queryGuru(String id) {
        Attention attention = new Attention();
        System.out.println("11111111"+id);
        attention.setUserId(id);
        List<Attention> attentions = attentionDao.select(attention);
        for (Attention attention1 : attentions) {
            System.out.println(attention1);
        }
        return attentions;
    }

    //---------------------------------------------关注上师-------------------------------------------------------------------------
    public void insert(String guruID,String uid) {
        Attention attention = new Attention();
        attention.setId(UUID.randomUUID().toString());
        attention.setUserId(uid);
        attention.setGuruId(guruID);
        attentionDao.insert(attention);
    }
}
