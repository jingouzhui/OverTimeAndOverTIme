package org.jingouzhui.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @description: 阻塞io客户端
 * @author: jingouzhui
 * @date: 2025/5/18 0:33
 */
public class BioClient {

    public static void main(String[] args) throws Exception {
        Thread jingouzhui = new Thread(() -> {
            try {
                sendMessage();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "jingouzhui");

        Thread zhuxiaohan = new Thread(() -> {
            try {
                sendMessage();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "zhuxiaohan");
        jingouzhui.start();
        zhuxiaohan.start();

        jingouzhui.join();
        zhuxiaohan.join();
    }

    private static void sendMessage() throws IOException, InterruptedException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost",8080));

        OutputStream outputStream = socket.getOutputStream();
        for (int i = 0; i < 10; i++) {
            outputStream.write((Thread.currentThread().getName()+"HelloWorld"+i).getBytes());
            outputStream.flush();
        }

        Thread.sleep(5000);
        socket.close ();
    }

}
