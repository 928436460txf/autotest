package com.course.code;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import sun.awt.SunHints;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value ="/",description ="这是我的全部get方法")
public class MyGetMethod {
    @RequestMapping(value = "/getcookies",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "这是一个获取cookies的方法",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        //HttpServletRequest 装请求信息的类
        //HttpServletResponse 装响应信息的类
        Cookie cookie=new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获得cookies成功";
    }

    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "这是一个携带cookies信息才能访问的get请求",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies= request.getCookies();

        if(Objects.isNull(cookies)){
            return "cookies信息为null";
        }

        for (Cookie cookie:cookies){
            if(cookie.getName().equals("login")&&cookie.getValue().equals("true")){
                return "这是一个携带cookies信息才能访问的get请求";
            }
        }

        return "cookies信息错误";

    }
    /*
     * 开发一个需要携带参数才能访问的get请求、
     *第一种实现防护URL：key=value&key=value
     * 模拟获取商品列表
     */
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    @ApiOperation(value = "开发一个需要携带参数才能访问的get请求",httpMethod = "GET")
    public Map<String,Integer> getlist(@RequestParam Integer start,@RequestParam Integer end){
        Map<String,Integer> mylist=new HashMap<>();
        mylist.put("鞋子",400);
        mylist.put("鼠标",40);
        mylist.put("书包",100);
        return mylist;
    }

    /*
     * 开发一个需要携带参数才能访问的get请求、
     *第一种实现防护URL：ip:port/get/with/param/10/20
     * 模拟获取商品列表
     *
     */
    @RequestMapping(value = "/get/with/param/{start}/{end}",method = RequestMethod.GET)
    @ApiOperation(value = "开发一个需要携带参数才能访问的get请求2",httpMethod = "GET")
    public Map<String,Integer> getlist2(@RequestParam(value = "start", required = false)  Integer start,@RequestParam(value = "end", required = false)  Integer end){
        Map<String,Integer> mylist=new HashMap<>();
        mylist.put("鞋子",400);
        mylist.put("鼠标",40);
        mylist.put("书包",100);
        return mylist;
    }





    @RequestMapping(value = "/getwithparam",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "这是一个有参数的get请求",httpMethod = "GET")
    public String getWithParam(@RequestParam(value = "name",required = false) String name,@RequestParam String age){
        if(Objects.isNull(name)||Objects.isNull(age)){
            return "get请求参数不能为空";
        }
        else if("zhangsan".equals(name)&&"19".equals(age)){
            return "这是一个有参数的get请求";
        }
        else{
            return "get请求参数错误";
        }

    }

}
