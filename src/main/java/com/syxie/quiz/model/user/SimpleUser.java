package com.syxie.quiz.model.user;

public class SimpleUser {
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "SimpleUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
