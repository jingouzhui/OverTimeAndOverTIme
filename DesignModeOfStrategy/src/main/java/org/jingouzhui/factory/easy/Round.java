package org.jingouzhui.factory.easy;

/**
 * @description: 圆形
 * @author: jingouzhui
 * @date: 2025/6/3 14:51
 */
public class Round extends Shape{
    @Override
    public void draw() {
        System.out.println("绘制圆形");
    }

    @Override
    public void erase() {
        System.out.println("擦除圆形");

    }
}
