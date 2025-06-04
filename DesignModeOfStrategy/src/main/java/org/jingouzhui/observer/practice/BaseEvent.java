package org.jingouzhui.observer.practice;

import org.jingouzhui.observer.practice.Event;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/6/4 22:21
 */
public  abstract class BaseEvent implements Event {

    private final long timestamp;

    protected BaseEvent() {
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public long timestamp() {
        return this.timestamp;
    }
}