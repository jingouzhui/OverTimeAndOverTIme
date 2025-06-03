package org.jingouzhui.observer;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @description: 天气站
 * @author: jingouzhui
 * @date: 2025/6/3 23:34
 */
public class WeatherStation {

    private Set<User> users = new HashSet<>();


    public void subsribe(User user) {
        users.add(user);
    }

    /**
     * 返回天气信息
     * @return
     */
    public String getInfo(){

        if (new Random().nextBoolean()) {
            return "晴天";
        }
        return "雨天";
    }

    /**
     * 每隔三秒获取最新的天气并且通知订阅了天气站的用户
     * @throws InterruptedException
     */
    public void start() throws InterruptedException {
        /**
         * 当前气象站的责任过重 不符合单一责任原则  TODO 修改
         */
        while (true) {
            //1.生成事件
            String info = getInfo();
            for (User user : users) {
                //2.通知所有订阅事件的用户
                user.receiveInfo(info);
            }
            Thread.sleep(3000);
        }
    }
}
