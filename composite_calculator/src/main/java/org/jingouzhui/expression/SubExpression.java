package org.jingouzhui.expression;

/**
 * @description: 减法表达式
 * @author: jingouzhui
 * @date: 2025/5/16 上午12:36
 */
public class SubExpression extends BinaryOperatorExpression {

    public SubExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int getValue() {
        return left.getValue() - right.getValue();
    }
}
