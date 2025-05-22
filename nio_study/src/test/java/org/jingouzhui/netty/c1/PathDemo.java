package org.jingouzhui.netty.c1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/5/22 11:45
 */
public class PathDemo {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("d\\data\\projects\\a\\..\\b");
        Files.exists(path);
        Files.createDirectory(path);
        System.out.println(path);
        System.out.println(path.normalize());

    }
}
