package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserInfoCase;
import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetUserInfoTest {


    @Test(dependsOnGroups ="loginTrue" ,description = "删除用户信息接口测试")
    public void deleteUserInfo() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();

        System.out.println(TestConfig.getUserInfoUrl);
        System.out.println("先删除id为3的数据：");
        //删除插入的数据并返回所有结果
        System.out.println(session.delete("deleteUserInfoCase", 3));
        session.commit();
        session.close();

    }

    @Test(dependsOnMethods = "insertUserInfo", description = "获取用户信息接口测试")
    public void getUserInfo() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        System.out.println("最后查询id为3的数据：");
        GetUserInfoCase getUserInfoCase = session.selectOne("getUserInfoCase", 3);
        System.out.println(getUserInfoCase.toString());

        System.out.println("最后查询所有获取用户信息的数据：");

        List<GetUserInfoCase> getUserInfoCaseLs = session.selectList("getUserInfoCase", 4);

        for(GetUserInfoCase list:getUserInfoCaseLs){
            System.out.println(list.toString());
        }
        System.out.println(TestConfig.getUserInfoUrl);

    }

    @Test(dependsOnMethods = "deleteUserInfo", description = "插入用户信息接口测试")
    public void insertUserInfo() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();

        System.out.println("再插入id为3的数据：");
        GetUserInfoCase getUserInfoCase=new  GetUserInfoCase();
        getUserInfoCase.setId(3);
        getUserInfoCase.setUserId(3);
        getUserInfoCase.setExpected("3");

        int i=session.insert("insertUserInfoList", getUserInfoCase);
        session.commit();
        session.close();
        System.out.println(i);
    }

}
