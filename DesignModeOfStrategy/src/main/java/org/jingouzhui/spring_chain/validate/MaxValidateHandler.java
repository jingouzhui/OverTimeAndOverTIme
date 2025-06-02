package org.jingouzhui.spring_chain.validate;

import lombok.RequiredArgsConstructor;
import org.jingouzhui.chain.ValidateHandler;
import org.jingouzhui.chain.ValidatorContext;
import org.jingouzhui.chain.annotation.Length;
import org.jingouzhui.chain.annotation.Max;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/5/29 17:45
 */
@Component
@RequiredArgsConstructor
public class MaxValidateHandler implements ValidatorChainHandler<ValidateReq> {


    @Override
    public void handle(ValidateReq requestParam) throws NoSuchFieldException {
        Integer paramMax = requestParam.getMax();
        Max max = ValidateReq.class.getDeclaredField("max").getAnnotation(Max.class);

        if (paramMax > max.value()){
            throw new RuntimeException("超过最大值");
        }

    }

    @Override
    public int getOrder() {
        return 1;
    }
}
