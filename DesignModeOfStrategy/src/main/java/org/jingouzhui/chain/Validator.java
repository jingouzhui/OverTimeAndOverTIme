package org.jingouzhui.chain;

import org.jingouzhui.chain.annotation.Length;
import org.jingouzhui.chain.annotation.Max;
import org.jingouzhui.chain.annotation.Min;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 校验
 * @author: jingouzhui
 * @date: 2025/5/29 17:22
 */
public class Validator {

private ValidatorChain buildValidatorChain(Field field){
    ValidatorChain chain = new ValidatorChain();
    Max max = field.getAnnotation(Max.class);
    Min min = field.getAnnotation(Min.class);
    Length length = field.getAnnotation(Length.class);
    if(max != null){
        chain.addValidateHandler(new MaxValidateHandler(max.value()));
    }
    if(min != null){
        chain.addValidateHandler(new MinValidateHandler(min.value()));
    }
    if(length != null){
        chain.addValidateHandler(new LengthValidateHandler(length.length()));
    }

    return chain;
}
    //对传入的值进行校验
    public void validate(Object bean) throws IllegalAccessException, ValidateException {
        Class<?> valueClass = bean.getClass();
        for (Field field : valueClass.getDeclaredFields()) {
            field.setAccessible(true);
            ValidatorChain chain =  buildValidatorChain(field);
            chain.validate(field.get(bean));

        }

    }

    public static void main(String[] args) throws IllegalAccessException {
        Validator validator = new Validator();
        User user = new User(18,"zaxqa");
        validator.validate(user);
    }
}
