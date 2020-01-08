package com.baizhi.ww.controller;

import com.baizhi.ww.entity.Admin;
import com.baizhi.ww.service.AdminService;
import com.baizhi.ww.util.CreateValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @ResponseBody
    @RequestMapping("selectOne")
    //管理员登录
    public String selectOne(HttpSession session,String username,String password,String clientCode){
        String serverCode = session.getAttribute("ServerCode").toString();
        if(serverCode.equals(clientCode)){
            Admin admin = adminService.queryByName(username);
            if(admin==null){
                return "用户名不存在";
            }else if (!password.equals(admin.getPassword())){
                return "密码错误";
            }else{
                session.setAttribute("admin",admin);
                return null;
            }
        }
        return "验证码错误";
    }

    //验证码
    @RequestMapping("/createImg")
    public void createImg(HttpServletResponse response, HttpSession session) throws IOException {
        CreateValidateCode vcode = new CreateValidateCode();
        String code = vcode.getCode(); // 随机验证码
        System.out.println("验证码："+code);
        vcode.write(response.getOutputStream()); // 把图片输出client
        session.setAttribute("ServerCode", code);
    }
}
