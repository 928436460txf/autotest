package com.course.testng.suite;

import org.testng.annotations.Test;

public class ExpectException {
    /**
     * 异常测试：比如传入不合法的参数，环境异常等
     */

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionsucess(){
        System.out.println("这是一个成功等异常测试");
        throw new RuntimeException();
    }
}


