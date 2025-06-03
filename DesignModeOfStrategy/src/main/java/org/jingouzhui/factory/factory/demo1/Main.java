package org.jingouzhui.factory.factory.demo1;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/6/3 20:05
 */
public class Main {

    public static void main(String[] args) {
        PicReader reader = new GiFReader();
        reader.getPicture().readPicture();
        reader = new JPGReader();
        reader.getPicture().readPicture();
    }
}
