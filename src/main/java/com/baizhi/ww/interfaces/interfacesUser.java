package com.baizhi.ww.interfaces;

import com.baizhi.ww.entity.User;
import com.baizhi.ww.service.UserService;
import com.baizhi.ww.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("interfacesUser")
public class interfacesUser {
    @Autowired
    UserService userService;
    //---------------------------------------用户登陆接口--------------------------------------
    @RequestMapping("login")
    public Map loginUser(String phone, String password) {
        User user1 = new User();
        user1.setPhone(phone);
        User user = userService.queryOne(user1);
        HashMap map = new HashMap();
        if (user == null) {
            map.put("status", "-200");
            map.put("message", "用户不存在");
        } else if (!password.equals(user.getPassword())) {
            map.put("status", "-200");
            map.put("message", "密码错误");
        } else if (user.getStatus() == "0") {
            map.put("status", "-200");
            map.put("message", "用户已冻结");
        } else {
            map.put("status", "200");
            map.put("message", "登录成功");
            map.put("user", user);
        }
        return map;
    }
    //---------------------------------------修改用户信息--------------------------------------
    @RequestMapping("updateUser")
    public Map updateUser(User user, String uid) {
        user.setId(uid);
        HashMap map = new HashMap();
        try {
            User user1 = userService.update1(user);
            map.put("status", 200);
            map.put("user", user1);
        } catch (Exception e) {
            map.put("status", -200);
            map.put("message", "修改失败");
        }
        return map;
    }
    //---------------------------------------金刚道友--------------------------------------
    @RequestMapping("queryAll")
    public Map queryAll(String uid) {
        HashMap map = new HashMap();
        try {
            Set<User> users = userService.queryAll(uid);
            map.put("status", 200);
            map.put("user", users);
        } catch (Exception e) {
            map.put("status", -200);
            map.put("message", "查询失败");
        }
        return map;
    }
    //---------------------------------------用户注册接口--------------------------------------
    @RequestMapping("insert")
    public Map insertUser(MultipartFile multipartFile, User user, HttpServletRequest request) {
        HashMap map = new HashMap();
        user.setStatus("1");
        map.put("status", "-200");
        map.put("message", "注册失败");
        if (multipartFile == null) {
            userService.insert(user);
            map.put("status", "200");
            map.put("message", "注册成功");
            map.put("user", user);
        } else {
            String photo = HttpUtil.getHttp(multipartFile, request, "/upload/userImg");
            user.setPhoto(photo);
            map.put("status", "200");
            map.put("message", "注册成功");
            map.put("user", user);
        }
        return map;
    }
}