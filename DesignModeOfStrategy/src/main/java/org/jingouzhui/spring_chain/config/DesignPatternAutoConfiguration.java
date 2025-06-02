package org.jingouzhui.spring_chain.config;

import org.jingouzhui.spring_chain.AbstractChainContext;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/5/31 22:30
 */
@ImportAutoConfiguration(ApplicationBaseAutoConfiguration.class)

public class DesignPatternAutoConfiguration {

    @Bean
    public AbstractChainContext abstractChainContext() {
        return new AbstractChainContext();
    }
}
