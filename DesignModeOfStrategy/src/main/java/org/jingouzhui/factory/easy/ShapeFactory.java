package org.jingouzhui.factory.easy;

import java.awt.Rectangle;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/6/3 14:54
 */
public class ShapeFactory {

    public static Shape getShape(String shapeName) {
        if (shapeName.equals("round")) {
            return new Round();
        }else if (shapeName.equals("triangle")) {
            return new Triangle();
        }else if (shapeName.equals("square")) {
            return new Square();
        }
        throw  new UnSupportedShapeException("不支持的图形");
    }
}
