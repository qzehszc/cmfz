package com.baizhi.ww.dao;

import com.baizhi.ww.entity.Counter;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface CounterDao extends Mapper<Counter>, DeleteByIdListMapper<Counter,String> {
}
