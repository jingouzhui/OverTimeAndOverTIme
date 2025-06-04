package org.jingouzhui.observer.practice;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: 总线
 * @author: jingouzhui
 * @date: 2025/6/4 22:32
 */
public class Bus {
    private Set<EventListener> listeners = new HashSet<>();


    public void subscribe(EventListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(EventListener listener) {
        listeners.remove(listener);
    }

    public void publish(Event event) {
       if(event instanceof StockPriceEvent) {
           for(EventListener listener : listeners) {
               listener.onEvent(event);
           }
       }

    }
}
