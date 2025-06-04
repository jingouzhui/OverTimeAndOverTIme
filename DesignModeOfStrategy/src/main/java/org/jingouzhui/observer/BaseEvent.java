package org.jingouzhui.observer;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/6/4 16:32
 */
public  abstract class BaseEvent implements  Event{

    private final long timestamp;

    protected BaseEvent() {
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public long timestamp() {
        return this.timestamp;
    }
}
