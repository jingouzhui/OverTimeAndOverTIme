package org.jingouzhui.spring_chain.config;

import org.springframework.context.ApplicationEvent;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/5/31 22:32
 */
public class ApplicationInitializingEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public ApplicationInitializingEvent(Object source) {
        super(source);
    }
}
