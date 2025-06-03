package org.jingouzhui.factory.factory.demo1;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/6/3 20:04
 */
public class GiFReader implements PicReader{
    @Override
    public Picture getPicture() {
        System.out.println("创建GIFPic读取器");
        return new GIFPic();
    }
}
