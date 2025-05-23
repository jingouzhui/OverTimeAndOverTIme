package org.jingouzhui.c4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import static org.jingouzhui.ByteBufferUtil.debugRead;

/**
 * @description: 使用nio理解阻塞模式
 * @author: jingouzhui
 * @date: 2025/5/23 14:24
 */
public class NoBlockServer {
    private static final Logger log = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) throws IOException {
        //创建ByteBuffer接收数据
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        //绑定监听端口
        ssc.bind(new InetSocketAddress(8881));

        //连接集合
        List<SocketChannel> channels = new ArrayList<>();
        while (true) {
            //log.info("开始建立连接");
            //accept与客户端建立连接
            SocketChannel sc = ssc.accept();
            if (sc != null) {
                log.info("连接建立完毕{}", sc);
                sc.configureBlocking(false);
                channels.add(sc);

            }
            for (SocketChannel channel : channels) {
                //log.info("准备向buffer写入数据");
                int read = channel.read(buffer);
                if (read > 0) {
                    //切换读模式
                    buffer.flip();
                    log.info("读取buffer中的数据");
                    debugRead(buffer);
                    log.info("读取完成切换为写模式为下个连接写入做准备");
                    buffer.clear();
                }


            }
        }
    }
}
