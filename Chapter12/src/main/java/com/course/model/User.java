package com.course.model;


import lombok.Data;

@Data
public class User {
    private int id;
    private String userName;
    private String password;
    private String age;
    private String sex;
    private String permission;
    private String isDelete;

    @Override
    public String toString(){
        return (
                "id:"+id+","+
                        "username:"+userName+","+
                        "password:"+password+","+
                        "age:"+age+","+
                        "sex:"+id+","+
                        "permission:"+permission+","+
                        "isDelete:"+isDelete
        );
    }
}
