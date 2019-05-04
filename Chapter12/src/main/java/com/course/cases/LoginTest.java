package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigFlie;
import com.course.utils.DatabaseUtil;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {

    @BeforeTest(groups = "loginTrue",description = "测试准备工作")
    public void beforeTest(){
        TestConfig.getUserInfoUrl= ConfigFlie.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.addUserUrl= ConfigFlie.getUrl(InterfaceName.ADDUSERINFO);
        TestConfig.getUserListUrl= ConfigFlie.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.loginUrl= ConfigFlie.getUrl(InterfaceName.LOGIN);
        TestConfig.updateUserInforUrl= ConfigFlie.getUrl(InterfaceName.UPDATEUSERINFO);

        TestConfig.defaultHttpClient=new DefaultHttpClient();
    }

    @Test(groups = "loginTrue",description = "用户登录成功接口测试")
    public void loginTrue() throws IOException {
        System.out.println(TestConfig.loginUrl);
        SqlSession session= DatabaseUtil.getSqlSession();
        LoginCase loginCase=session.selectOne("loginCase",1);
        System.out.println(loginCase.toString());

    }

    @Test(groups = "loginFalse",description = "用户登录失败接口测试")
    public void loginFalse() throws IOException{
        System.out.println(TestConfig.loginUrl);

        SqlSession session= DatabaseUtil.getSqlSession();
        LoginCase loginCase=session.selectOne("loginCase",2);
        System.out.println(loginCase.toString());
    }
}
