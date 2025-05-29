package org.jingouzhui.chain;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/5/29 17:45
 */
public class LengthValidateHandler implements ValidateHandler{

    private int length;
    public LengthValidateHandler(int length) {
        this.length = length;
    }
    @Override
    public void doValidate(Object value) {

        if(value instanceof String){
            int len = ((String)value).length();
            if (len != length) {
                throw  new ValidateException("Name length is wrong");
            }
        }
    }
}
