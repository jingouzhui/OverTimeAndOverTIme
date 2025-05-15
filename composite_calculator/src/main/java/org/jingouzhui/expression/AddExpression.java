package org.jingouzhui.expression;

/**
 * @description: 加法表达式
 * @author: jingouzhui
 * @date: 2025/5/16 上午12:35
 */
public class AddExpression extends BinaryOperatorExpression{


    public AddExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int getValue() {
        return left.getValue() + right.getValue();
    }
}
