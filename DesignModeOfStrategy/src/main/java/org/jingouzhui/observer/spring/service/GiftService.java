package org.jingouzhui.observer.spring.service;

import org.jingouzhui.observer.spring.RegisterEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/6/4 19:03
 */
@Service
public class GiftService {


    @EventListener
    public void OnEvent(RegisterEvent event) {
        System.out.println(event.getSource() +"发送新手礼包");
    }
}
