package org.jingouzhui.single;

import java.io.Serializable;

/**
 * @description: DCL  双重判定锁实现单例
 * @author: jingouzhui
 * @date: 2025/6/3 15:17
 */
public class Singleton2 implements Serializable {

    private volatile static Singleton2 singleton2;

    private Singleton2() {
        if (singleton2 != null) {
            //阻止反序列化破坏单例
            throw new RuntimeException("Singleton2 has already been created");
        }
    }
    public static Singleton2 getInstance() {
            if (singleton2 == null) {
                synchronized (Singleton2.class) {
                    if (singleton2 == null) {
                        singleton2 = new Singleton2();
                    }
                }
            }
            return singleton2;
    }

    private Object readResolve(){
        return singleton2;
    }

}
