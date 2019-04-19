package com.course.httpClient.cookies;

import com.sun.tools.doclint.Entity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultHttpClientConnectionOperator;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {
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
        res=EntityUtils.toString(httpResponse.getEntity(),"utf-8");
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
    public void testGetWithCookies() throws IOException {
        String res;
        String uri=bundle.getString("test.get.with.cookies");
        String testurl=this.url+uri;
        System.out.println(testurl);
        HttpGet httpGet=new HttpGet(testurl);

        DefaultHttpClient client=new DefaultHttpClient();
        //设置cookies信息
        client.setCookieStore(this.store);

        HttpResponse httpResponse=client.execute(httpGet);
        //获取响应吗
        int statuscode=httpResponse.getStatusLine().getStatusCode();
        if(statuscode==200){
            res=EntityUtils.toString(httpResponse.getEntity(),"utf-8");
            System.out.println(res);
        }
    }
}
