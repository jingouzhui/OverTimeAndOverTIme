package org.jingouzhui.c4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @description: 可写事件客户端
 * @author: jingouzhui
 * @date: 2025/5/26 22:23
 */
public class WriteClient {
    private static final Logger log = LoggerFactory.getLogger(WriteClient.class);
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        //sc.configureBlocking(false);
        sc.connect(new InetSocketAddress("localhost", 8080));
        ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);

        int count = 0;
        while(true) {

            count += sc.read(buffer);
            log.info(String.valueOf(count));
            buffer.clear();
        }
    }
}
