package org.jingouzhui.chain;

/**
 * @description:  每个具体的责任链可以影响下一个责任链的值和是否传递到下一个责任链
 * @author: jingouzhui
 * @date: 2025/5/29 17:44
 */
public interface ValidateHandler {
    void doValidate(Object value,ValidatorContext context);
}
