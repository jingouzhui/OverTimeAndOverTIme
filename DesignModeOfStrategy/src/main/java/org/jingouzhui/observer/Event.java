package org.jingouzhui.observer;

/**
 * @description: 事件
 * @author: jingouzhui
 * @date: 2025/6/4 16:28
 */
public interface Event {
    /**
     * 事件发生的时间戳
     * @return
     */
    long timestamp();

    /**
     * 事件内容
     * @return
     */
    Object source();
}
