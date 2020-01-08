package com.baizhi.ww.dao;


import com.baizhi.ww.entity.Log;

import java.util.List;

public interface LogDao {
    public void addLog(Log log);

    public List<Log> selectAllLog();
}
