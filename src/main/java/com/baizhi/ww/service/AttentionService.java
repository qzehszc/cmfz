package com.baizhi.ww.service;


import com.baizhi.ww.entity.Attention;

import java.util.List;

public interface AttentionService {
    //查询关注的上师
    public abstract List<Attention> queryGuru(String id);
    //添加关注上师
    public abstract void insert(String guruID, String uid);
}
