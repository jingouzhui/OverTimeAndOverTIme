package org.jingouzhui;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @description: 定时任务类
 * @author: jingouzhui
 * @date: 2025/5/13 下午1:37
 */
public class ScheduleService {


    Executor executor = Executors.newFixedThreadPool(10);

    Trigger trigger = new Trigger();


    /**
     * 每隔delayTime时间执行一次task
     * @param task 待执行的任务
     * @param delayTime 延迟时间
     * @param timeUnit  时间单位
     */
    public void schedule(Runnable task, long delayTime, TimeUnit timeUnit){
        Job job = new Job();
        job.setTask(task);
        job.setDelayTime(delayTime);
        job.setTimeUnit(timeUnit);
        job.setStartTime(System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(delayTime, timeUnit));
        trigger.jobQueue.offer(job);
        trigger.wakeUp();
    }


    class Trigger{
        PriorityBlockingQueue<Job> jobQueue = new PriorityBlockingQueue<>();
            //优先级队列
            Thread thread = new Thread(() ->{


                while(true){
                    //while防止虚假唤醒问题
                    while( jobQueue.isEmpty()){
                        LockSupport.park();
                    }
                    //job1     job1和job2不一定是同一个，在多线程的情况下可能在52行判断结束后加入了新的任务
                    Job job = jobQueue.peek();
                    if (job.getStartTime() < System.currentTimeMillis()){
                        //job2
                        job = jobQueue.poll();
                        executor.execute(job.getTask());
                        Job nextJob = new Job(job.getTask(), System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(job.getDelayTime(), job.getTimeUnit()), job.getDelayTime(),job.getTimeUnit());
                        jobQueue.add(nextJob);
                    }else {
                        //park直到到达执行时间
                        LockSupport.parkUntil(job.getStartTime());
                    }




                }
            });

        {
            thread.start();
            System.out.println("触发器启动了");
        }


            void wakeUp(){
                LockSupport.unpark(thread);
            }
    }
}
