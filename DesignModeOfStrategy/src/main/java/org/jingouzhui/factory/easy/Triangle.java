package org.jingouzhui.factory.easy;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/6/3 14:53
 */
public class Triangle extends  Shape{
    @Override
    public void draw() {
        System.out.println("绘制三角形");
    }

    @Override
    public void erase() {
        System.out.println("擦除三角形");

    }
}
