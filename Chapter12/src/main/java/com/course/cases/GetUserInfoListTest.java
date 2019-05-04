package com.course.cases;


import com.course.config.TestConfig;
import com.course.model.GetUserListCase;
import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetUserInfoListTest {

    @Test(dependsOnGroups = "loginTrue",description = "获取用户列表接口测试")
    public void getUserInfoList() throws IOException{
        SqlSession session= DatabaseUtil.getSqlSession();

        GetUserListCase getUserListCase=session.selectOne("getUserListCase",1);

        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUrl);

    }

}

