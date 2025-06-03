package org.jingouzhui.observer;

import java.util.function.Consumer;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/6/3 23:35
 */
public class User {
    private final String name;

    private Consumer<String> consumer;

    public User(String name, Consumer<String> consumer) {
        this.name = name;
        this.consumer = consumer;

    }

    public void receiveInfo(String info) {
        consumer.accept(info);
    }
}
