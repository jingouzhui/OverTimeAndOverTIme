package org.jingouzhui.single;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/6/3 15:26
 */
public class Test2 {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {

        //1.演示反射破坏单例
       /* Singleton2 instance = Singleton2.getInstance();


        Constructor<Singleton2> declaredConstructor = Singleton2.class.getDeclaredConstructor();
        //允许对私有构造函数操作
        declaredConstructor.setAccessible(true);
        Singleton2 singleton2 = declaredConstructor.newInstance();

        System.out.println(singleton2 == instance);*/

        //2.演示反序列化破坏单例
        Singleton2 instance = Singleton2.getInstance();
        ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get("singleton2.txt")));
        oos.writeObject(instance);
        oos.flush();
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get("singleton2.txt")));
        //这里通过Java 的 Unsafe 机制来创建对象的，而不是通过调用构造函数 所以可以破坏单例
        //解决方案
        /**
         * 解决反席列化的破坏单例，只需要我们白定义反序列化的策略就行了，
         * 就是说我们不要让他走默认逻辑一直调用到Unsafe创建对象，而是我们干预他的这个过程，
         * 干预方式就是在Singleton类中定义 readResolve，这样就可以解决该问题:
         */
        Singleton2 singleton2_2 = (Singleton2) ois.readObject();

        System.out.println(singleton2_2==instance);

        //枚举实现的单例模式  好处
        //1.简单 2.线程安全 3.不会被反序列化破坏
        Singleton3 instance1 = Singleton3.INSTANCE;
        ObjectOutputStream oos1 = new ObjectOutputStream(Files.newOutputStream(Paths.get("singleton3.txt")));
        oos1.writeObject(instance1);
        oos1.flush();
        oos1.close();
        ObjectInputStream ois1 = new ObjectInputStream(Files.newInputStream(Paths.get("singleton3.txt")));
        Singleton3 singleton3 = (Singleton3) ois1.readObject();
        System.out.println(singleton3==instance1);


    }
}
