package org.jingouzhui.observer;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @description: 天气站
 * @author: jingouzhui
 * @date: 2025/6/3 23:34
 */
//气象站只负责生成消息 封装成事件 交给电视台发布事件
public class WeatherStation {

  private TVStation tvStation;

  public WeatherStation(TVStation tvStation) {
      this.tvStation = tvStation;
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

        while (true) {
            //1.生成事件
            String info = getInfo();
            WeatherUpdateEvent updateEvent = new WeatherUpdateEvent(info);
            tvStation.publish(updateEvent);
            Thread.sleep(3000);
        }
    }
}
