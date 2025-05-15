package org.jingouzhui.expression;

/**
 * @description: 乘法表达式接口
 * @author: jingouzhui
 * @date: 2025/5/16 上午12:36
 */
public class MultiplyExpression extends  BinaryOperatorExpression {


    public MultiplyExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int getValue() {
        return left.getValue() * right.getValue();
    }
}
