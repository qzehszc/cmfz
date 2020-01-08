package com.baizhi.ww;

import com.baizhi.ww.dao.AdminDao;
import com.baizhi.ww.entity.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class CmfzApplicationTests {

    @Autowired
    AdminDao adminDao;
    @Test
   public void contextLoads() {
        Admin admin = new Admin(null,"admin","admin");
        Admin admin1 = adminDao.selectOne(admin);
        System.out.println(admin);
        System.out.println(admin1);
    }

}
