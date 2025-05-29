package org.jingouzhui.chain;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/5/29 17:45
 */
public class MinValidateHandler implements ValidateHandler {
    private int min;

    public MinValidateHandler(int min) {
        this.min = min;
    }

    @Override
    public void doValidate(Object value,ValidatorContext context) {
        if (value instanceof Integer) {
            Integer i = (Integer) value;
            if (i < min) {
                context.appendErrorMsg("Age is less than " + min);

            }
            context.doNext(value);
        }
    }
}
