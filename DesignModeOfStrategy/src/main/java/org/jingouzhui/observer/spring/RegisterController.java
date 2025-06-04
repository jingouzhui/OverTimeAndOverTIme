package org.jingouzhui.observer.spring;

import jakarta.annotation.PostConstruct;
import org.jingouzhui.observer.spring.service.GiftService;
import org.jingouzhui.observer.spring.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/6/4 18:51
 */
@RestController
@RequestMapping("/login")
public class RegisterController {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private ApplicationEventMulticaster multicaster;


    @Autowired
    private GiftService giftService;

    @Autowired
    private MailService mailService;



    @PostConstruct
    public void init(){
        System.out.println(applicationContext == publisher);
       //publisher.publishEvent(new RegisterEvent("aaa"));
    }

    @GetMapping
    public String login(String user){
        RegisterEvent event = new RegisterEvent(user);
        publisher.publishEvent(event);
        return "ok";
    }
}
