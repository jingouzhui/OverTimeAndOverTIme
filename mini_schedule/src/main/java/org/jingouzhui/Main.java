package org.jingouzhui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * test
 *
 */
public class Main
{
    public static void main( String[] args ) throws InterruptedException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        ScheduleService scheduleService = new ScheduleService();


        Job job1 = scheduleService.schedule(() -> {
            System.out.println(LocalDateTime.now().format(dateTimeFormatter) + "每隔1s执行一次");
        }, 1, TimeUnit.SECONDS);

        Job job2 = scheduleService.schedule(() -> {
            System.out.println(LocalDateTime.now().format(dateTimeFormatter) + "每隔2s执行一次");
        }, 2, TimeUnit.SECONDS);

        Thread.sleep(2000);
        job1.cancel();
        System.out.println("job1被中断了");
        Thread.sleep(10000);
        job2.cancel();
        System.out.println("job2被中断了");

    }
}
