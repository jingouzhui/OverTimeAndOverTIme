package org.jingouzhui.c4;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static org.jingouzhui.ByteBufferUtil.debugRead;

/**
 * @description: 多线程服务端
 * @author: jingouzhui
 * @date: 2025/5/27 14:00
 */
public class MultiThreadServer {
    private static final Logger log = LoggerFactory.getLogger(MultiThreadServer.class);


    public static void main(String[] args) throws IOException {

        Thread.currentThread().setName("boss");
        //ssc
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8881));
        //selector
        Selector selector = Selector.open();
        //注册
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        //worker 创建固定数量的worker并初始化
        Worker[] workers = new Worker[2];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker("worker-"+i);
        }
        AtomicInteger count = new AtomicInteger(0);

        while(true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel sc = server.accept();
                    sc.configureBlocking(false);
                    log.info("connected...{}",sc.getRemoteAddress());
                    //关联selector 读事件由专门的worker来处理
                    log.info("before register:{}",sc.getRemoteAddress());
                    workers[count.getAndIncrement() % workers.length].register(sc);
                    //sc.register(worker.selector, SelectionKey.OP_READ);
                    log.info("after register:{}",sc.getRemoteAddress());

                }
            }
        }
    }
    static class Worker implements Runnable{
        private Thread thread;
        private Selector selector;
        private String name;
        private boolean start = false;
        private ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue();

        public Worker(String name) {
            this.name = name;
        }

        public void register(SocketChannel sc) throws IOException {
            if (! start) {
                selector = Selector.open();
                thread = new Thread(this,name);
                thread.start();
                start = true;
            }
            queue.add(() ->{
                try {
                    sc.register(this.selector, SelectionKey.OP_READ);
                } catch (ClosedChannelException e) {
                    throw new RuntimeException(e);
                }
            });
           selector.wakeup();
        }

        @Override
        public void run() {
            while (true) {
                try {
                    selector.select();
                    Runnable task = queue.poll();
                    if (task != null) {
                        task.run();
                    }
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isReadable()) {

                            ByteBuffer buffer = ByteBuffer.allocate(16);
                            SocketChannel sc = (SocketChannel) key.channel();
                            log.info("readable:{}",sc.getRemoteAddress());
                            sc.read(buffer);
                            buffer.flip();
                            debugRead(buffer);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}
