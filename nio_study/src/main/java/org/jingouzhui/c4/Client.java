package org.jingouzhui.c4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/5/23 14:33
 */
public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("127.0.0.1", 8881));
        sc.write(StandardCharsets.UTF_8.encode("helloworld1234567890\nabc"));
        sc.write(StandardCharsets.UTF_8.encode("123"));
        sc.write(StandardCharsets.UTF_8.encode("sadf123\n"));
        System.out.println("waiting for connection");

    }
}
