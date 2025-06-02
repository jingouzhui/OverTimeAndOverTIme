package org.jingouzhui.spring_chain.config;

import org.springframework.beans.factory.InitializingBean;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/5/31 22:32
 */
public class FastJsonSafeMode implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.setProperty("fastjson2.parser.safeMode", "true");
    }
}
