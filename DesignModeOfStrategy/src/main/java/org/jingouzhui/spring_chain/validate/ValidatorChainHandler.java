package org.jingouzhui.spring_chain.validate;

import org.jingouzhui.spring_chain.AbstractChainHandler;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/5/31 21:55
 */
public interface ValidatorChainHandler<T extends ValidateReq> extends AbstractChainHandler<ValidateReq> {

    @Override
    default String mark(){
        return "validate";
    }

}
