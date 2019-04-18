package com.course.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest {
    @Test(dataProvider = "data")
    public void testDataProvider(String name,int age){
        System.out.println("name="+name+";age="+age);
    }

    @DataProvider(name="data")
    public Object[][] providerData(){
        return new Object[][]{
            {"zhangsan",10},
            {"lisi",19},
            {"wanwu",30}
        };
    }


    @Test(dataProvider = "methodData")
    public void test1(String name,int age){
        System.out.println("方法111，name="+name+";age="+age);
    }

    @Test(dataProvider = "methodData")
    public void test2(String name,int age){
        System.out.println("方法2222，name="+name+";age="+age);
    }

    @DataProvider(name="methodData")
    public Object[][] methodDataTest(Method method){
        Object[][] result=null;
        if(method.getName().equals("test1")){
            result= new Object[][]{
                    {"zhansan",20},
                    {"lisi",30}
            };
        }
        else if(method.getName().equals("test2")){
            result= new Object[][]{
                    {"lisi",40},
                    {"wanwu",60}
            };
        }
        return result;
    }

}
