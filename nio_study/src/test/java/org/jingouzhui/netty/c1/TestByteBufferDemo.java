package org.jingouzhui.netty.c1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

import static org.jingouzhui.Constants.directory.C1_FILE_DIR;
import static org.jingouzhui.netty.c1.ByteBufferUtil.debugAll;

/**
 * @description: 测试读写功能
 * @author: jingouzhui
 * @date: 2025/5/19 11:10
 */
public class TestByteBufferDemo {
    private static final Logger logger = LoggerFactory.getLogger(TestByteBufferDemo.class);

    public static void main(String[] args) throws Exception {
        // byteBufferDemo1();
        // byteBufferDemo2();
        //byteBufferDemo3();
        //byteBufferDemo4();
        //byteBufferDemo5();
       // byteBufferDemo6();
        //byteBufferDemo7();
        byteBufferDemo8();
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

    /**
     * 分散读 scattering read
     */
    public static void byteBufferDemo4() {
        //也可以使用 FIleInputStream
        try (FileChannel channel = new RandomAccessFile(C1_FILE_DIR + "3parts.txt", "r").getChannel()) {
            ByteBuffer b1 = ByteBuffer.allocate(3);
            ByteBuffer b2 = ByteBuffer.allocate(3);
            ByteBuffer b3 = ByteBuffer.allocate(3);
            channel.read(new ByteBuffer[]{b1, b2, b3});
            b1.flip();
            b2.flip();
            b3.flip();
            debugAll(b1);
            debugAll(b2);
            debugAll(b3);
        } catch (IOException e) {
        }

    }
    /**
     * 集中写 gathering writes
     */
    public static void byteBufferDemo5() {
        //也可以使用RandomAccessFile("name","rw")
        try (FileChannel channel = new FileOutputStream(C1_FILE_DIR + "helloword.txt").getChannel()) {
            ByteBuffer b1 = StandardCharsets.UTF_8.encode("hello");
            ByteBuffer b2 = StandardCharsets.UTF_8.encode("world");
            ByteBuffer b3 = StandardCharsets.UTF_8.encode("你好世界");

            channel.write(new ByteBuffer[]{b1, b2, b3});

        } catch (IOException e) {
        }

    }

    /**
     * 黏包半包问题

    网络上有多条数据发送给服务端，数据之间使用\n进行分但由于某种原因这些数据在接收时，被进行了重新组合，例如原始数据有3条为
    Hello,world\n
    I'm zhangsan\n
    How are you?\n
    变成了下面的两个 byteBuffer(包，半包)
     Hello,world\nI'm zhangsan\nHo
    w are you?\n
    现在要求你编写程序，将错乱的数据恢复成原始的按\n分割的数据
     */
    public static void byteBufferDemo6() throws Exception{
        ByteBuffer buffer = ByteBuffer.allocate(64);
        buffer.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        split(buffer);
        buffer.put("w are you?\n".getBytes());
        split(buffer);

    }

    private static  void split(ByteBuffer buffer){
        //切换为读模式
        buffer.flip();
        int pos = 0;
        for(int i = 0; i < buffer.limit();i ++){
            //找到完整的消息
            if (buffer.get(i) == '\n') {
                int len = i + 1 - pos;
                pos = i + 1;
                ByteBuffer target = ByteBuffer.allocate(len);
                //写入正确的buffer中
                for (int j = 0; j < len; j++) {
                    target.put(buffer.get());
                }
                debugAll(target);
            }
        }
        //切换为写模式 由于有黏包和半包 所以不能使用clear
        buffer.compact();
    }

    /**
     * 文件传输功能
     */
    public static void byteBufferDemo7() {
        try (FileChannel from = new FileInputStream(C1_FILE_DIR + "from.txt").getChannel();
             FileChannel to = new FileOutputStream(C1_FILE_DIR + "to.txt").getChannel();) {
                long startTime = System.currentTimeMillis();
            logger.info("开始时间:{}",startTime);
            //效率高  底层使用零拷贝优化
            from.transferTo(0,from.size(),to);
            long endTime = System.currentTimeMillis();
            logger.info("结束时间:{}",endTime);
            logger.info("花费时间:{}",(endTime-startTime));
        } catch (Exception e) {
            logger.error("传输异常",e);
        }

    }

    /**
     * 当文件大小超过2g时
     */
    public static void byteBufferDemo8() {
        try (FileChannel from = new FileInputStream(C1_FILE_DIR + "from.txt").getChannel();
             FileChannel to = new FileOutputStream(C1_FILE_DIR + "to.txt").getChannel();) {
            long startTime = System.currentTimeMillis();
            logger.info("开始时间:{}",startTime);
            //效率高  底层使用零拷贝优化
            long size = from.size();
            for (long left = size; left >0;){
                logger.info("position:{},left:{}",size - left,left);
               left -= from.transferTo(size - left,left,to);

            }
            long endTime = System.currentTimeMillis();
            logger.info("结束时间:{}",endTime);
            logger.info("花费时间:{}",(endTime-startTime));
        } catch (Exception e) {
            logger.error("传输异常",e);
        }

    }


}
