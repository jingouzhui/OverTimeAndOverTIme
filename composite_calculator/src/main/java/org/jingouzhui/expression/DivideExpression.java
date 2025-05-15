package org.jingouzhui.expression;

/**
 * @description: 除法表达式接口
 * @author: jingouzhui
 * @date: 2025/5/16 上午12:38
 */
public class DivideExpression extends  BinaryOperatorExpression {


    public DivideExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int getValue() {
        return left.getValue() / right.getValue();
    }
}
