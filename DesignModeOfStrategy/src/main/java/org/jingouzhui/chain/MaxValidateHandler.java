package org.jingouzhui.chain;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/5/29 17:45
 */
public class MaxValidateHandler implements ValidateHandler{
    private int max;

    public  MaxValidateHandler(int max){
        this.max = max;
    }
    @Override
    public void doValidate(Object value) {
        if(value instanceof Integer){
            if(((Integer)value).intValue() > max){
                throw  new ValidateException("Age is greater than " + max);
            }

        }
    }
}
