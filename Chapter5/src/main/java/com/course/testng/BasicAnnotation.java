package com.course.testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BasicAnnotation {
    @Test
    public void testcase1(){
        System.out.println("Thread id: "+Thread.currentThread().getId());
        System.out.println("case1");
    }

    @BeforeMethod
    public void beforemethod(){
        System.out.println("Thread id: "+Thread.currentThread().getId());
        System.out.println("before method!");
    }

    @AfterMethod
    public void aftermethod(){
        System.out.println("Thread id: "+Thread.currentThread().getId());
        System.out.println("after method!");
    }
}
