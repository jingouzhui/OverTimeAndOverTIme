package org.jingouzhui.factory.factory.demo1;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/6/3 20:02
 */
public class GIFPic implements Picture{
    @Override
    public void readPicture() {
        System.out.println("读取GIF图片");
    }
}
