package org.jingouzhui.observer.practice;

/**
 * @description: 事件监听者
 * @author: jingouzhui
 * @date: 2025/6/4 22:36
 */
public class Investor implements EventListener{

    private String name;
    public Investor(String name) {
        this.name = name;
    }
    @Override
    public void onEvent(Event event) {
        System.out.println(name+"准备出售股票");
    }
}
