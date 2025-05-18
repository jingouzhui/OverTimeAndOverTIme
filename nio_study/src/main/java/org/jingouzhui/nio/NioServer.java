package org.jingouzhui.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @description: Nio服务端
 * @author: jingouzhui
 * @date: 2025/5/18 10:50
 */
public class NioServer {
    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel ssc  = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress("127.0.0.1", 8080));
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        
        while(true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while(iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if(key.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
                    SocketChannel client  = serverSocketChannel.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);
                    System.out.println(client.getRemoteAddress()+"连接了");

                }
                if (key.isReadable()) {
                    SocketChannel sc = (SocketChannel)key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    //read后是写入状态
                    int len = sc.read(buffer);
                   if (len == -1) {
                       System.out.println("客户端断开了连接"+sc.getRemoteAddress());
                       sc.close();
                   }else {
                       //切换为读取状态
                       buffer.flip();
                       byte[] bytes = new byte[buffer.remaining()];
                       buffer.get(bytes);
                       String msg = new String(bytes);
                       System.out.println(msg);
                   }

                }
            }
        }

    }
}
