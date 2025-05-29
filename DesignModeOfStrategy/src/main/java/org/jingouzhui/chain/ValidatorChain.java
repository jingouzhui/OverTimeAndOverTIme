package org.jingouzhui.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/5/29 18:59
 */
public class ValidatorChain {

    private List<ValidateHandler> validateHandlers = new ArrayList<>();



    public void validate(Object value) throws ValidateException {
         ValidatorContext context = new ValidatorContext(value);
        while (true) {
            int index = context.currentIndex();
            if (index == validateHandlers.size()) {
                break;
            }
            ValidateHandler handler = validateHandlers.get(index);
            handler.doValidate(context.getValue(),context);
            if (index == context.currentIndex()) {
                //说明当前处理的链没有调用下一个链即直接结束
                break;
            }
        }

        context.throwErrorMsgIFNecessary();
    }

    public void addValidateHandler(ValidateHandler handler) {
        this.validateHandlers.add(handler);
    }
}
