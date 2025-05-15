package org.jingouzhui.expression;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @description: 表达式解析器
 * @author: jingouzhui
 * @date: 2025/5/16 上午12:53
 */
public class ExpressionParser {

    private String infixExpression;

    /**
     * 指针的位置 用于解析表达式
     */
    int point = 0;

    public  ExpressionParser(String infixExpression) {
        this.infixExpression = infixExpression;
    }

    /**
     * 将一个中缀表达式转换为后缀表达式,list中是表达式的每一个元素
     * 遇到操作数，直接push到结果
     * 遇到(，入辅助栈
     * 遇到)，辅助栈顶元素push到结果，直到遇到然后删除一对括号
     * 遇到运算符
     * 让辅助栈顶优先级大于等于自己的运算符push到结果
     * 如果优先级小于自己或者没有运算符则自己入栈
     */
    public List<String> toSuffix() {
        List<String> suffixList = new ArrayList<>();
        LinkedList<String> supStack = new LinkedList<>();
        while (point < infixExpression.length()) {
            char curChar = infixExpression.charAt(point);
            if (curChar == '(') {
                supStack.addLast(String.valueOf(curChar));
            } else if (curChar == ')') {
                while(!Objects.equals(supStack.getLast(), "(")){
                    suffixList.add(supStack.removeLast());
                }
                supStack.removeLast();
            }else if (curChar == '+' || curChar == '-') {
                while (!supStack.isEmpty()&&(Objects.equals(supStack.getLast(), "+") || Objects.equals(supStack.getLast(), "-") || Objects.equals(supStack.getLast(), "*")
                || Objects.equals(supStack.getLast(), "/"))) {
                    suffixList.add(supStack.removeLast());
                }
                supStack.addLast(String.valueOf(curChar));

            } else if (curChar == '*' || curChar == '/') {
                while (!supStack.isEmpty()&&(Objects.equals(supStack.getLast(), "*") || Objects.equals(supStack.getLast(), "/"))) {
                    suffixList.add(supStack.removeLast());
                }
                supStack.addLast(String.valueOf(curChar));
            }else if (Character.isDigit(curChar)){
                StringBuilder  stringBuilder = new StringBuilder();
                while (point < infixExpression.length() && (Character.isDigit(infixExpression.charAt(point)))) {
                    stringBuilder.append(infixExpression.charAt(point));
                    point++;
                }
                point--;
                suffixList.add(stringBuilder.toString());
            }else {
                throw  new IllegalArgumentException("表达式异常");
            }
            point++;
        }
        while (!supStack.isEmpty()) {
            suffixList.add(supStack.removeLast());
        }

        return suffixList;
    }

    /**
     * 将后缀运算符组装为一个表达式
     * @return
     */
    public Expression parse(){
        List<String> suffixList = this.toSuffix();
        LinkedList<Expression> stack = new LinkedList<>();
        for (String item : suffixList) {
            if (item.equals("+")){
                Expression right = stack.removeLast();
                stack.add(new AddExpression(stack.removeLast(),right));
            }else if (item.equals("-")){
                Expression right = stack.removeLast();
                stack.add(new SubExpression(stack.removeLast(),right));
            } else if (item.equals("*")){
                Expression right = stack.removeLast();
                stack.add(new MultiplyExpression(stack.removeLast(),right));
            }else if (item.equals("/")){
                Expression right = stack.removeLast();
                stack.add(new DivideExpression(stack.removeLast(),right));
            }else{
                //数字
                stack.addLast(new NumberExpression(Integer.parseInt(item)));
            }
        }
        return stack.getLast();
    }
}
