package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.UpdateUserInfoCase;
import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateUserInfoTest {
    @Test(dependsOnGroups = "loginTrue", description = "编辑用户信息接口测试")
    public void updateUserInfo() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();

        UpdateUserInfoCase updateUserInfoCase = session.selectOne("updateUserInfoCase", 1);

        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInforUrl);

    }

    @Test(dependsOnGroups = "loginTrue", description = "删除用户信息接口测试")
    public void deleteUserInfo() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();

        UpdateUserInfoCase updateUserInfoCase = session.selectOne("updateUserInfoCase", 2);

        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInforUrl);

    }
}
