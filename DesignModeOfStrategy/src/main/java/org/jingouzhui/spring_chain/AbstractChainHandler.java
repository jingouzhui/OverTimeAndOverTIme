package org.jingouzhui.spring_chain;

import org.springframework.core.Ordered;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/5/31 21:31
 */
public   interface AbstractChainHandler<T>  extends Ordered {

     void handle(T requestParam) throws NoSuchFieldException;


    String mark();
}
