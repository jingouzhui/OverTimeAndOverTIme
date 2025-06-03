package org.jingouzhui.factory.factory.demo1;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/6/3 20:04
 */
public class JPGReader implements PicReader{
    @Override
    public Picture getPicture() {
        System.out.println("创建JPGPic读取器");
        return new JPGPic();
    }
}
