package com.baizhi.ww.service;


import com.baizhi.ww.entity.Log;

import java.util.List;

public interface LogService {
    public void addLog(Log log);
    public List<Log> queryAllLog();
}
