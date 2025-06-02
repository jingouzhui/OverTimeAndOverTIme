package org.jingouzhui.spring_chain;

/**
 * @description: 审批责任链
 * @author: jingouzhui
 * @date: 2025/5/31 21:55
 */
public interface ApproveChainHandler  extends AbstractChainHandler{

    @Override
    default String mark() {
        return "approve";
    }
}
