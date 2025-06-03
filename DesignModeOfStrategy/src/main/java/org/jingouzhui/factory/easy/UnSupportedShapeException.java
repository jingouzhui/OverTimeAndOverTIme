package org.jingouzhui.factory.easy;

/**
 * @description: 自定义异常
 * @author: jingouzhui
 * @date: 2025/6/3 14:57
 */
public class UnSupportedShapeException extends RuntimeException{

    public UnSupportedShapeException(){}
    public UnSupportedShapeException(String message){
        super(message);
    }
    public UnSupportedShapeException(String message, Throwable cause){
        super(message, cause);
    }
    public UnSupportedShapeException(Throwable cause){
        super(cause);
    }
}
