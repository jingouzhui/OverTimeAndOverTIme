package org.jingouzhui.expression;

/**
 * @description: 数字表达式
 * @author: jingouzhui
 * @date: 2025/5/16 上午12:33
 */
public class NumberExpression implements  Expression {

    private int value;

    public NumberExpression(int value) {
        this.value = value;
    }
    @Override
    public int getValue() {
        return this.value;
    }
}
