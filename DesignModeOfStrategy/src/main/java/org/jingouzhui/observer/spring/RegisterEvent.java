package org.jingouzhui.observer.spring;

import org.springframework.context.ApplicationEvent;

/**
 * @description: 注册事件
 * @author: jingouzhui
 * @date: 2025/6/4 18:54
 */
public class RegisterEvent extends ApplicationEvent {

    public RegisterEvent(Object source) {
        super(source);
    }
}
