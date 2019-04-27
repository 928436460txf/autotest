package com.course.code;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "这是我的全部post请求")
public class MyPostMethod {
    private static Cookie cookie;
    private User u;

    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录成功后获取cookie信息",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName",required = true) String userName,
                        @RequestParam(value = "pwd",required = true) String pwd){

        if("zhangsan".equals(userName)&&"123".equals(pwd)) {
            cookie=new Cookie("login","true");
            response.addCookie(cookie);
            return "登录成功，获取cookie信息成功";
        }
        return "登录用户或密码错误！";

    }

    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "验证cookie并返回用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,@RequestBody User u){
         User user ;
        //获取cookies
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login") &&
                    cookie.getValue().equals("true") &&
                    u.getUserName().equals("zhangsan") &&
                    u.getPassword().equals("123456")) {
                user=new User();
                user.setName("lisi");
                user.setSex("man");
                user.setAge("10");
                return user.toString();
            }
        }

         return "验证失败";

    }


}
