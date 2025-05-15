package org.jingouzhui;

import org.jingouzhui.expression.Expression;
import org.jingouzhui.expression.ExpressionParser;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        /**
         * 遇到操作数，直接push到结果
         * 遇到(，入辅助栈
         * 遇到)，辅助栈顶元素push到结果，直到遇到然后删除一对括号
         * 遇到运算符
         * 让辅助栈顶优先级大于等于自己的运算符push到结果
         * 如果优先级小于自己或者没有运算符则自己入栈
         */
        String s = "2*(3+1)+10/5+3*(6-3)";
        ExpressionParser parser = new ExpressionParser(s);
        Expression expression = parser.parse();
        System.out.println(expression.getValue());
        System.out.println(2*(3+1)+10/5+3*(6-3));


    /*
     校验后缀表达式解析器
     ExpressionParser parser = new ExpressionParser(expression);
        List<String> suffix = parser.toSuffix();
        StringBuilder stringBuilder = new StringBuilder();

        // 2 3 1 + * 10 5 / + 3 6 3 - * +
        for (String s : suffix) {
            stringBuilder.append(s);
        }
        System.out.println(stringBuilder);*/


    }
}