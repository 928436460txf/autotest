package com.course.httpClient.demo;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;

public class MyHttpClient {
    @Test
    public void test(){
        String res=null;
        //使用get方法
        HttpGet get=new HttpGet("http://www.baidu.com");
        HttpClient httpClient=HttpClientBuilder.create().build();

        try {
            //响应内容类型为HttpResponse
            HttpResponse httpResponse=httpClient.execute(get);
            //响应内容，转化成String类型
            res= EntityUtils.toString(httpResponse.getEntity(),"utf-8");
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}