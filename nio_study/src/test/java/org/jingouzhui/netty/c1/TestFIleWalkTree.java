package org.jingouzhui.netty.c1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 文件遍历
 * @author: jingouzhui
 * @date: 2025/5/22 21:00
 */
public class TestFIleWalkTree {
    private static final Logger logger = LoggerFactory.getLogger(TestFIleWalkTree.class);
    public static void main(String[] args) throws IOException {

        Path source = Paths.get("D:/Temp");
        Path target = Paths.get("D:/Temp2");
        Files.walkFileTree(source, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                String targetFileName = dir.toString().replace(source.toString(), target.toString());
                    Files.createDirectory(Paths.get(targetFileName));
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String targetFileName = file.toString().replace(source.toString(), target.toString());
                Files.copy(file,Paths.get(targetFileName));
                return super.visitFile(file, attrs);
            }
        });


    }

    private static void copyDirectories() throws IOException {
        Path path = Paths.get("D:\\ediary-4.2.6-win64-copy - 副本");
        // Files.delete(path);
        Files.walkFileTree(path,new SimpleFileVisitor<Path>() {

            //先删除每个目录里面的文件
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return super.visitFile(file, attrs);
            }
            //再删除该目录即可
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return super.postVisitDirectory(dir, exc);
            }
        });
    }

    private static void jarEndWithFiles() throws IOException {
        AtomicInteger jarCount = new AtomicInteger();
        Files.walkFileTree(Paths.get("D:\\JDK\\JDK1.8"),new SimpleFileVisitor<Path>(){

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(".jar")) {
                    logger.info("===>jar:{}",file);
                    jarCount.incrementAndGet();
                }
                return super.visitFile(file, attrs);
            }
        });
        logger.info("jarCount:{}",jarCount);
    }

    private static void walkFileTree() throws IOException {
        AtomicInteger dirCount = new AtomicInteger();
        AtomicInteger fileCount = new AtomicInteger();
        Files.walkFileTree(Paths.get("D:\\JDK\\JDK1.8"),new SimpleFileVisitor<Path>(){

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                logger.info("====>dir:{}",dir.toString());
                dirCount.incrementAndGet();
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                logger.info("===>file:{}",file.toString());
                fileCount.incrementAndGet();
                return super.visitFile(file, attrs);
            }
        });
        logger.info("dirCount:{}",dirCount);
        logger.info("fileCount:{}",fileCount);
    }
}
