package org.jingouzhui.observer;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: 电视台  (消息总线)
 * @author: jingouzhui
 * @date: 2025/6/4 0:46
 */
//只做两件事 1.让监听者订阅 2.通知监听者有事件发生
public class TVStation {
    private Set<EventListener> listeners = new HashSet<>();


    public void subscribe(EventListener listener) {
        listeners.add(listener);
    }

    public void publish(Event event) {
        for (EventListener listener : listeners) {
            listener.onEvent(event);
        }
    }

}
