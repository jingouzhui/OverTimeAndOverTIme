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
        validateHandlers.forEach(handler -> {
            handler.doValidate(value);
        });
    }

    public void addValidateHandler(ValidateHandler handler) {
        this.validateHandlers.add(handler);
    }
}
