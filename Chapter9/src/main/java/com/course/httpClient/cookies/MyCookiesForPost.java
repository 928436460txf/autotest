package com.course.httpClient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {
    private String url;
    private ResourceBundle bundle; //读取配置文件
    private CookieStore store;

    @BeforeTest
    public void beforeTest(){
        bundle= ResourceBundle.getBundle("application", Locale.CHINA);//自动找到resource下的配置文件的前缀名，文件格式不用带自动获取.properties文件
        url=bundle.getString("test.url");

    }

    @Test
    public void cookieTest() throws IOException {
        String res;
        String uri=bundle.getString("getCookies.uri");
        String testurl=this.url+uri;

        System.out.println(testurl);
        HttpGet httpGet=new HttpGet(testurl);

        //HttpClient方法是没有获取cookies信息，获取cookies需要使用DefaultHttpClient类
        DefaultHttpClient httpClient= new DefaultHttpClient();
        HttpResponse httpResponse=httpClient.execute(httpGet);
        res= EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        System.out.println(res);

        //获取cookies内容,cookies有多组时怎么获取？？？
        this.store=httpClient.getCookieStore();

        List<Cookie> cookies=this.store.getCookies();

        System.out.println(cookies.size());

        for(Cookie cookie:cookies){
            String name=cookie.getName();
            String value=cookie.getValue();

            System.out.println("name="+name+"   value="+value);
        }

    }

    @Test(dependsOnMethods = "cookieTest")
    public void testPosttWithCookies() throws IOException {

        String uri=bundle.getString("test.post.with.cookies");
        String testurl=this.url+uri;
        System.out.println(testurl);

        //声明一个Client对象用来进行方法的执行
        DefaultHttpClient client=new DefaultHttpClient();
        //声明一个Post方法
        HttpPost httpPost=new HttpPost(testurl);
        //添加参数
        JSONObject param=new JSONObject();
        param.put("name","huhansan");
        param.put("sex","man");
        //设置请求头参数,设置header
        httpPost.setHeader("Content-Type","application/json");
        //将参数信息添加到方法中
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(entity);

        //设置cookies信息
        client.setCookieStore(this.store);
        // 声明一个对象来进行响应结果存放
        String res=null;
        HttpResponse response=client.execute(httpPost);
        int statuscode=response.getStatusLine().getStatusCode();
        System.out.println("响应码："+statuscode);
        res=EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("响应结果："+res);
        //断言结果是否正确
        //将返回的响应结果字符串转换成json格式
        JSONObject resultJson=new JSONObject(res);

        //具体的判断返回结果的值
        String success=resultJson.getString("huhansan");
        String status=resultJson.getString("status");
        //断言
        Assert.assertEquals("success",success);
        Assert.assertEquals("1",status);


    }
}
