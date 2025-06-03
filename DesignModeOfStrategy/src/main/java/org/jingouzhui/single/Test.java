package org.jingouzhui.single;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/6/2 23:10
 */
public class Test {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Singleton singleton1 = Singleton.getSingleton();

        ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(new File("singleton1.txt").toPath()));

        oos.writeObject(singleton1);

        oos.flush();
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(new File("singleton1.txt").toPath()));
       Singleton singleton2 = (Singleton) ois.readObject();

        System.out.println(singleton1 == singleton2);


    }
}
