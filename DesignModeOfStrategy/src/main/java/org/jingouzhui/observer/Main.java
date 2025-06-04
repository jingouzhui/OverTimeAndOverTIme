package org.jingouzhui.observer;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/6/3 23:34
 */
public class Main {
        //观察者模式  事件生成者 直接去通知--> 事件消费者
        //发布订阅模式 事件生成者 -->总线-->事件消费者  解耦了生产者和消费者 他们彼此不知道自己的存在
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

        tv.subscribe(xiaohan,WeatherUpdateEvent.class);
        tv.subscribe(xiaoquan,WeatherUpdateEvent.class);
        WeatherStation ws = new WeatherStation(tv);
        ws.start();
    }
}
