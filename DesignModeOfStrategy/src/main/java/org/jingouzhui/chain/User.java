package org.jingouzhui.chain;

import org.jingouzhui.chain.annotation.Length;
import org.jingouzhui.chain.annotation.Max;
import org.jingouzhui.chain.annotation.Min;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/5/29 17:16
 */
public class User {
    @Min(30)
    @Max(25)
    private Integer age;

    @Length(length = 4)
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }
    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
