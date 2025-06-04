package org.jingouzhui.observer.practice;

/**
 * @description: 事件
 * @author: jingouzhui
 * @date: 2025/6/4 22:19
 */
public interface Event {

     long  timestamp();

     Object source();
}
