package org.jingouzhui.c4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @description: 可写事件
 * @author: jingouzhui
 * @date: 2025/5/26 22:09
 */
public class WriteServer {

    private static final Logger log = LoggerFactory.getLogger(WriteServer.class);
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress( 8080));

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while(true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while(iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if(key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    SelectionKey sckey = sc.register(selector, SelectionKey.OP_READ);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 10000000; i++) {
                        sb.append("zxq");
                    }

                    ByteBuffer buffer = Charset.defaultCharset().encode(sb.toString());
                    int write = sc.write(buffer);
                    log.info(String.valueOf(write));
                    //查看是否还有可写的内容如果有则关注可写事件
                    if (buffer.hasRemaining()) {
                        sckey.interestOps(sckey.interestOps() + SelectionKey.OP_WRITE);
                        //将未写完的数据绑定到key中
                        sckey.attach(buffer);
                    }
                }else if (key.isWritable()) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    int write = sc.write(buffer);
                    log.info(String.valueOf(write));
                    //清理操作
                    if (! buffer.hasRemaining()) {
                        //清理buffer
                        key.attach(null);
                        key.interestOps(key.interestOps() - SelectionKey.OP_WRITE);
                    }
                }
            }

        }
    }
}
