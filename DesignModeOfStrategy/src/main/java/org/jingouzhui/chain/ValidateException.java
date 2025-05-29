package org.jingouzhui.chain;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/5/29 17:35
 */
public class ValidateException extends RuntimeException {
    public ValidateException() {

    }
    public ValidateException(String message) {
        super(message);
    }
    public ValidateException(String message, Throwable cause) {
        super(message, cause);
    }
}
