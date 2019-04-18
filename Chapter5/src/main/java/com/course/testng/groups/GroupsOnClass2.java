package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass2 {
    public void stu2(){
        System.out.println("groupsonclass stu2");
    }
}
