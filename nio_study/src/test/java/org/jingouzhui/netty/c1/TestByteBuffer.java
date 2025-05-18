package org.jingouzhui.netty.c1;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static org.jingouzhui.Constants.directory.C1_FILE_DIR;

/**
 * @description: byteBufferDemo
 * @author: jingouzhui
 * @date: 2025/5/18 21:36
 */

public class TestByteBuffer {
    private static final Logger log = LoggerFactory.getLogger(TestByteBuffer.class);

    public static void main(String[] args) {
        //FileChannel
        //1.输入输出流 2.RandomAccessFile
        //通过channel读取文件内容
        try (FileChannel channel = new FileInputStream(C1_FILE_DIR + "byte_buffer_demo_data1.txt").getChannel()) {
            //用bytebuffer接收数据
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true) {
                //从channel中读取数据写入buffer中
                int len = channel.read(buffer);
                log.info("读取到的字节长度:{}", len);
                if (len == -1) {
                    log.info("数据读取完毕");
                    break;
                }

                //切换为读取模式
                buffer.flip();
                //查看是否还有剩余数据
                while (buffer.hasRemaining()) {
                    byte b = buffer.get();
                    log.info("读取到的数据:{}", (char) b);
                }
                //切换为写模式
                buffer.clear();

            }


        } catch (IOException e) {
            System.out.println("aa");
        }
    }
}
