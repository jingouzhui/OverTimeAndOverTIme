package org.jingouzhui.observer.spring;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @description: 注册监听器
 * @author: jingouzhui
 * @date: 2025/6/4 18:56
 */
@Component
public class RegisterListener {


    @EventListener
    public void OnEvent(RegisterEvent event) {
        System.out.println(event.toString());
    }
}
