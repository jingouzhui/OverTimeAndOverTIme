package org.jingouzhui.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description: 阻塞io服务端
 * @author: jingouzhui
 * @date: 2025/5/18 0:33
 */
public class BioServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9090);
     while(true) {
         //accept等待客户端连接  accept()是阻塞的 成功连接后返回socket
         Socket socket = serverSocket.accept();

         InputStream inputStream = socket.getInputStream();
         byte[] buffer = new byte[1024];
         int length;
         while ((length = inputStream.read(buffer)) != -1) {
             String message = new String(buffer, 0, length);
             System.out.println(message);
         }
         System.out.println("客户端断开连接 ");
     }
    }
}
