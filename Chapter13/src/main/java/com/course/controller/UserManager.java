package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Log4j
@RestController
@Api(value = "v1",description = "用户管理系统")
@RequestMapping("v1")
public class UserManager {
    @Autowired
    private SqlSessionTemplate template;

    @ApiOperation(value = "登录接口",httpMethod = "POST")
    @RequestMapping(value = "/login", method= RequestMethod.POST)
    public boolean login(HttpServletResponse response, @RequestBody User user){
        int i=Integer.parseInt(template.selectOne("login",user).toString());


        Cookie cookie=new Cookie("login","true");
        response.addCookie(cookie);
        log.info("查询结果是"+i);

        if(i==1){
            log.info("用户是："+user.getUserName());
            return true;
        }

        return false;
    }

    @ApiOperation(value = "获取用户接口",httpMethod = "POST")
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    public List<User> getUserInfo(HttpServletRequest request, @RequestBody User user){
        Boolean iscookie=verlifyCookie(request);
        List<User> users=null;
        if(iscookie==true) {
            users= (List<User>) template.selectList("getUserInfo",user);
        }
        if(users==null){
            log.info("获取用户失败！");
            return null;
        }
        else{
            log.info("添加用户成功！");
            log.info("用户信息为"+users.size());
            return users;
        }
    }



    @ApiOperation(value = "添加用户接口",httpMethod = "POST")
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public boolean addUser(HttpServletRequest request, @RequestBody User user){
        Boolean iscookie=verlifyCookie(request);
        int result=0;
        if(iscookie==true) {
            result=template.insert("addUser",user);
        }
        if(result==0){
            log.info("添加用户失败！");
            return false;
        }
        else{
            log.info("添加用户成功！");
            log.info("用户信息为"+user.toString());
            return true;
        }
    }

    @ApiOperation(value = "删除用户接口",httpMethod = "POST")
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public boolean deleteUser(HttpServletRequest request, @RequestBody User user){
        Boolean iscookie=verlifyCookie(request);
        int result=0;
        if(iscookie==true) {
            result=template.delete("",user);
        }
        if(result==0){
            log.info("删除用户失败！");
            return false;
        }
        else{
            log.info("删除用户成功！");
            log.info("用户信息为"+user.toString());
            return true;
        }
    }


    @ApiOperation(value = "修改用户接口",httpMethod = "POST")
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public boolean updateUser(HttpServletRequest request, @RequestBody User user){
        Boolean iscookie=verlifyCookie(request);
        int result=0;
        if(iscookie==true) {
            result=template.update("",user);
        }
        if(result==0){
            log.info("修改用户失败！");
            return false;
        }
        else{
            log.info("修改用户成功！");
            log.info("用户信息为"+user.toString());
            return true;
        }
    }




    private Boolean verlifyCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (Objects.isNull(cookies)) {
            log.info("cookie is null!!!");
            return false;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                log.info("cookie验证成功");
                return true;
            }
        }

        return false;
    }
}
