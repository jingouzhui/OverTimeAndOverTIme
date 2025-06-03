package org.jingouzhui.single;

import java.io.Serializable;

/**
 * @description: 单例模式 &序列化验证
 * @author: jingouzhui
 * @date: 2025/6/2 23:06
 */
public class Singleton implements Serializable {

    private Singleton(){}


    //静态内部类单例模式

    private static class SingletonBuilder{

        private static final Singleton singleton = new Singleton();
    }
    public static Singleton getSingleton() {
        return SingletonBuilder.singleton;
    }

    /**
     * 避免反序列化破坏单例
     */

    private Object readResolve() {
        return getSingleton();
    }


}
