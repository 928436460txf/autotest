package com.course.testng;

import org.testng.annotations.Test;

public class DependTest {
    @Test
    public void test1(){
        System.out.println("run test1");
     //   throw new RuntimeException();  被依赖的测试用例执行异常时会忽略后面的案例执行，只有被依赖的测试用例执行通过后才能执行后面的测试用例
    }

    @Test(dependsOnMethods = {"test1"})
    public void test2(){
        System.out.println("run test2");
    }

}
