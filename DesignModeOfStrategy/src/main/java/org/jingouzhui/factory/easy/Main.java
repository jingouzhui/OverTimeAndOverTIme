package org.jingouzhui.factory.easy;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/6/3 14:56
 */
public class Main {
    public static void main(String[] args) {
        Shape round = ShapeFactory.getShape("round1");
        round.draw();
        round.erase();
    }
}
