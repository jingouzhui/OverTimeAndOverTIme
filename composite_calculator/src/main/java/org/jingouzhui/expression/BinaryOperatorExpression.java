package org.jingouzhui.expression;

/**
 * @description: 二元表达式基类
 * @author: jingouzhui
 * @date: 2025/5/16 上午12:33
 */
public abstract class BinaryOperatorExpression implements Expression{

    Expression left;
    Expression right;

    protected BinaryOperatorExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
}
