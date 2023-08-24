package org.example.test.bean;

public class UserService {

    private String name;

    /**
     * 添加一个有参构造函数，用于测试
     *
     */
    public UserService(String name) {
        this.name = name;
    }

    public void queryInfo() {
        System.out.println(this.name + " querying...");
    }
}
