package com.course.testng;

import org.testng.annotations.Test;

public class IgnoreTest {
    @Test
    public void ignoreTest1(){
        System.out.println("ignoreTest1 run");
    }

    @Test(enabled = false)
    public void ignoreTest2(){
        System.out.println("ignoreTest2 run");
    }

    @Test(enabled = true)
    public void ignoreTest3(){
        System.out.println("ignoreTest3 run");
    }
}
