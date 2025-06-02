package org.jingouzhui.spring_chain.validate;

import lombok.RequiredArgsConstructor;
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
public class LengthValidateHandler implements ValidatorChainHandler<ValidateReq> {


   /* @Override
    public void handler(Object value, ValidatorContext context) {

        if(value instanceof String){
            int len = ((String)value).length();
            if (len != length) {
                context.appendErrorMsg("Name length is wrong"+len+",true length is "+length);
            }
        }
        context.doNext(value);
    }*/

    @Override
    public void handle(ValidateReq requestParam) throws NoSuchFieldException {
        Integer len = requestParam.getLength();
        Length length = ValidateReq.class.getDeclaredField("length").getAnnotation(Length.class);

        if (len != length.length()){
            throw new RuntimeException("长度异常");
        }

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
