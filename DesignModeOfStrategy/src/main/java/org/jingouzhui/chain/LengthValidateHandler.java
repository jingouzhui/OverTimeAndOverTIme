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
    public void doValidate(Object value,ValidatorContext context) {

        if(value instanceof String){
            int len = ((String)value).length();
            if (len != length) {
                context.appendErrorMsg("Name length is wrong"+len+",true length is "+length);
            }
        }
        context.doNext(value);
    }
}
