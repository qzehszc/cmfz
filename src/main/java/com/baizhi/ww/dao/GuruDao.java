package com.baizhi.ww.dao;

import com.baizhi.ww.entity.Guru;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface GuruDao extends Mapper<Guru>, DeleteByIdListMapper<Guru,String>, InsertListMapper<Guru> {
}
