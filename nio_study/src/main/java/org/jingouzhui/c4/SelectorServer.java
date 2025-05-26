package org.jingouzhui.c4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.jingouzhui.ByteBufferUtil.debugAll;
import static org.jingouzhui.ByteBufferUtil.debugRead;

/**
 * @description: selector
 * @author: jingouzhui
 * @date: 2025/5/23 18:08
 */
public class SelectorServer {
    private static final Logger log = LoggerFactory.getLogger(SelectorServer.class);

    public static void main(String[] args) throws IOException {
        //创建selector
        Selector selector = Selector.open();
        //创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        //注册到selector  建立和channel的连接
        SelectionKey sscKey = ssc.register(selector, 0, null);
        //key只关注accept事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        log.info("register Key:{}", sscKey);
        //绑定监听端口
        ssc.bind(new InetSocketAddress(8881));

        while (true) {
            //select 方法，没有事件发生，线程阻塞，有事件，线程才会恢复运行
            selector.select();
            //处理事件，selectedKeys 内部包含了所有发生的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                log.info("key:{}", key);
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    ByteBuffer buffer = ByteBuffer.allocate(16);
                    //将buffer作为附件关联到selectKey上
                    SelectionKey scKey = socketChannel.register(selector, 0, buffer);
                    scKey.interestOps(SelectionKey.OP_READ);
                    log.info("socketChannel:{}", socketChannel);
                } else if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    try {
                        int read = channel.read(buffer);
                        if (read == -1) {
                            log.info("客户端正常关闭");
                            key.cancel();
                        } else {
                            split(buffer);
                            //扩容
                            if (buffer.position() == buffer.limit()) {
                                ByteBuffer newByteBuffer = ByteBuffer.allocate(buffer.capacity() * 2);
                                buffer.flip();
                                newByteBuffer.put(buffer);
                                key.attach(newByteBuffer);
                            }
                        }
                    } catch (IOException e) {
                        log.info("客户端强制断开", e);
                        //因为客户端断开了 所以要要key从取消,从selector的keys集合中删除该key，否则下次会继续认为有事业发生了
                        key.cancel();
                    }
                }


            }

        }
    }
    private static void split(ByteBuffer buffer) {
        //切换为读模式
        buffer.flip();
        for(int i = 0; i < buffer.limit(); i++) {
            if (buffer.get(i) == '\n') {
                int split = i + 1 - buffer.position();
                ByteBuffer target = java.nio.ByteBuffer.allocate(split);
                for (int j = 0; j < split; j++) {
                    target.put(buffer.get());
                }
                debugAll(target);
            }
        }
        //切换为写模式，由于存在半包黏包现象所以使用compact
        buffer.compact();
    }
}
