package org.jingouzhui.netty.c1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static org.jingouzhui.netty.c1.ByteBufferUtil.debugAll;

/**
 * @description: 测试读写功能
 * @author: jingouzhui
 * @date: 2025/5/19 11:10
 */
public class TestByteBufferDemo {
    private static final Logger logger = LoggerFactory.getLogger(TestByteBufferDemo.class);

    public static void main(String[] args) {
        // byteBufferDemo1();
        // byteBufferDemo2();
        byteBufferDemo3();
    }

    public static void byteBufferDemo1() {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        logger.info("向buffer中写入数据{}", 0x61);
        buffer.put((byte) 0x61);
        debugAll(buffer);
        logger.info("继续向buffer中写入数据");
        buffer.put(new byte[]{0x62, 0x63, 0x64, 0x65});
        debugAll(buffer);
        logger.info("切换为读模式");
        buffer.flip();
        logger.info("获取一个字节的数据,随着position也得移动一个指针");
        buffer.get();
        debugAll(buffer);
        logger.info("切换为写模式-clear");
        buffer.clear();
        debugAll(buffer);
        logger.info("继续向buffer中写入4个字节数据");
        buffer.put(new byte[]{0x62, 0x63, 0x64, 0x65});
        debugAll(buffer);
        logger.info("切换为读模式");
        buffer.flip();
        logger.info("读取一个字节的数据");
        buffer.get();
        debugAll(buffer);
        logger.info("切换为写模式-compact");
        buffer.compact();
        debugAll(buffer);
    }

    public static void byteBufferDemo2() {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});
        debugAll(buffer);
        //切换为读模式
        buffer.flip();
        debugAll(buffer);
        buffer.get(new byte[4]);
        debugAll(buffer);
        buffer.rewind();
        debugAll(buffer);
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        buffer.mark();
        debugAll(buffer);
        buffer.reset();
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        debugAll(buffer);
    }

    /**
     * 字符串和bytebuffer互转
     */
    public static  void byteBufferDemo3() {
        //字符串转bytebuffer
        //1.
        ByteBuffer buffer1 = ByteBuffer.allocate(16);
        buffer1.put("hello".getBytes());
        debugAll(buffer1);
        //2.
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("hello");
        debugAll(buffer2);
        //3.
        ByteBuffer buffer3 = ByteBuffer.wrap("hello".getBytes());
        debugAll(buffer3);
        //buffer转字符串
        System.out.println(StandardCharsets.UTF_8.decode(buffer2));
        System.out.println(StandardCharsets.UTF_8.decode(buffer3));
        //对于buffer1我们需要切换到读模式然后再转换 否则读不出
        System.out.println(StandardCharsets.UTF_8.decode(buffer1));
        buffer1.flip();
        System.out.println(StandardCharsets.UTF_8.decode(buffer1));
    }
}
