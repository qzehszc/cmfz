package com.baizhi.ww.service.impl;

import com.baizhi.ww.dao.AdminDao;
import com.baizhi.ww.entity.Admin;
import com.baizhi.ww.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDao adminDao;

    @Override
    public Admin queryByName(String name) {
        Admin admin = adminDao.selectOne(new Admin("1", name, "admin"));
        return admin;
    }
}
