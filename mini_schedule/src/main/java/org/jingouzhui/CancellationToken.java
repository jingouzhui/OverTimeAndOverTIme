package org.jingouzhui;

/**
 * @description: 中断标识
 * @author: jingouzhui
 * @date: 2025/5/13 下午3:59
 */
public class CancellationToken {
    private  volatile boolean cancelled = false;


    public void cancel(){
        cancelled = true;
    }

    public boolean isCancelled(){
        return cancelled;
    }
}
