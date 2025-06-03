package org.jingouzhui.factory.easy;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/6/3 14:55
 */
public class Square extends Shape{
    @Override
    public void draw() {
        System.out.println("绘制正方形");
    }

    @Override
    public void erase() {
        System.out.println("擦除正方形");

    }
}
