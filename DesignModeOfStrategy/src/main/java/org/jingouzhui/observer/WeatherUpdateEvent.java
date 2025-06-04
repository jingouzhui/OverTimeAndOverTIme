package org.jingouzhui.observer;

/**
 * @description: 天气更新事件
 * @author: jingouzhui
 * @date: 2025/6/4 16:29
 */
public class WeatherUpdateEvent extends BaseEvent {

    private String info;

    public WeatherUpdateEvent(String info) {
        this.info = info;
    }


    @Override
    public Object source() {
        return info;
    }
}
