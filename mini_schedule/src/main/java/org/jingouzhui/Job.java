package org.jingouzhui;

import java.util.concurrent.TimeUnit;

/**
 * @description: 任务类
 * @author: jingouzhui
 * @date: 2025/5/13 下午1:39
 */
public class Job implements Comparable<Job> {

    private Runnable task;

    private long startTime;

    private long delayTime;

    private TimeUnit timeUnit;

    public Job(Runnable task, long startTime, long delayTime, TimeUnit timeUnit) {
        this.task = task;
        this.startTime = startTime;
        this.delayTime = delayTime;
        this.timeUnit = timeUnit;
    }

    public Job() {

    }

    public Runnable getTask() {
        return task;
    }

    public void setTask(Runnable task) {
        this.task = task;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    @Override
    public int compareTo(Job o) {
        return Long.compare(this.startTime, o.startTime);
    }

    static class JobBuilder{
        private Runnable task;
        private long startTime;
        private long delayTime;
        private TimeUnit timeUnit;

        public  static  JobBuilder builder() {
            return new JobBuilder();
        }
        public JobBuilder task(Runnable task) {
            this.task = task;
            return this;
        }
        public JobBuilder startTime(long startTime) {
            this.startTime = startTime;
            return this;
        }
        public JobBuilder delayTime(long delayTime) {
            this.delayTime = delayTime;
            return this;
        }
        public JobBuilder timeUnit(TimeUnit timeUnit) {
            this.timeUnit = timeUnit;
            return this;
        }
        public Job build() {
            return new Job(task, startTime, delayTime, timeUnit);
        }

    }
}
