package org.jingouzhui.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 电视台  (消息总线)
 * @author: jingouzhui
 * @date: 2025/6/4 0:46
 */
//只做两件事 1.让监听者订阅 2.通知监听者有事件发生
public class TVStation {
    private Map<Class<? extends Event>, List<EventListener>> listenerMap = new HashMap();

    public void subscribe(EventListener listener,Class<? extends Event> listenerClasses) {
        listenerMap.computeIfAbsent(listenerClasses, k -> new ArrayList<>()).add(listener);
    }

    public void publish(Event event) {
        Class<? extends Event> aClass = event.getClass();
        List<EventListener> eventListeners = listenerMap.get(aClass);
        if (eventListeners == null) {return;}
        for (EventListener eventListener : eventListeners) {
            eventListener.onEvent(event);
        }
    }

}
