package org.jingouzhui.chain;

import org.jingouzhui.chain.annotation.Length;
import org.jingouzhui.chain.annotation.Max;
import org.jingouzhui.chain.annotation.Min;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;


/**
 * @description: 校验
 * @author: jingouzhui
 * @date: 2025/5/29 17:22
 */
public class Validator {

/*private ValidatorChain buildValidatorChain(Field field){
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
}*/
private ValidatorChain buildValidatorChain(Field field) {
    ValidatorChain chain = new ValidatorChain();

    Map<Class<? extends Annotation>, Function<Annotation, ValidateHandler>> handlerMap = new HashMap<>();
    handlerMap.put(Max.class, ann -> new MaxValidateHandler(((Max)ann).value()));
    handlerMap.put(Min.class, ann -> new MinValidateHandler(((Min)ann).value()));
    handlerMap.put(Length.class, ann -> new LengthValidateHandler(((Length)ann).length()));

    handlerMap.forEach((annoClass, creator) ->
            Optional.ofNullable(field.getAnnotation(annoClass))
                    .ifPresent(ann -> chain.addValidateHandler(creator.apply(ann)))
    );

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
        User user = new User(26,"jingouzhui");
        validator.validate(user);
    }
}
