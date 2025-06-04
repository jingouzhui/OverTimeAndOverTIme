package org.jingouzhui.observer;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/6/3 23:34
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        TVStation tv  = new TVStation();
        User xiaohan = new User("xiaohan",(info)-> {
            if (info.equals("雨天")) {
                System.out.println("下雨啦，xiaohan出去玩");
            }else {
                System.out.println("不下雨，xiaohan在家睡觉");
            }
        });
        User xiaoquan = new User("xiaoquan",(info)-> {
            if (info.equals("晴天")) {
                System.out.println("晴天,xiaoquan钻洞");
            }
        });

        tv.subscribe(xiaohan);
        tv.subscribe(xiaoquan);
        WeatherStation ws = new WeatherStation(tv);
        ws.start();
    }
}
