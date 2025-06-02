package org.jingouzhui.spring_chain.validate;

import lombok.RequiredArgsConstructor;
import org.jingouzhui.chain.ValidateHandler;
import org.jingouzhui.chain.ValidatorContext;
import org.jingouzhui.chain.annotation.Max;
import org.jingouzhui.chain.annotation.Min;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/5/29 17:45
 */
@Component
@RequiredArgsConstructor
public class MinValidateHandler implements ValidatorChainHandler<ValidateReq> {


    @Override
    public void handle(ValidateReq requestParam) throws NoSuchFieldException {
        Integer paramMin = requestParam.getMax();
        Min min = ValidateReq.class.getDeclaredField("min").getAnnotation(Min.class);

        if (paramMin < min.value()){
            throw new RuntimeException("小于最小值");
        }

    }

    @Override
    public int getOrder() {
        return 2;
    }
}