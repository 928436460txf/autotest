package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadOnAnnotion {
    @Test(invocationCount = 10,threadPoolSize = 3)     //0个线程3个线程池同时跑
    public void test(){
        System.out.println(1);
        System.out.println("Thread id: "+Thread.currentThread().getId());
    }
}
