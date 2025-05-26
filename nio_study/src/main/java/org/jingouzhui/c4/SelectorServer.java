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
        log.info("register Key:{}",sscKey);
        //绑定监听端口
        ssc.bind(new InetSocketAddress(8881));

        while (true) {
            //select 方法，没有事件发生，线程阻塞，有事件，线程才会恢复运行
            selector.select();
            //处理事件，selectedKeys 内部包含了所有发生的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
               // iterator.remove();
                log.info("key:{}",key);
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    SelectionKey scKey = socketChannel.register(selector, 0, null);
                    scKey.interestOps(SelectionKey.OP_READ);
                    log.info("socketChannel:{}",socketChannel);
                }else if(key.isReadable()){
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(16);
                    channel.read(byteBuffer);
                    byteBuffer.flip();
                    debugRead(byteBuffer);
                }


            }

        }
    }
}
