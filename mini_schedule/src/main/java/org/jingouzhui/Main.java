package org.jingouzhui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args ) throws InterruptedException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        ScheduleService scheduleService = new ScheduleService();

        scheduleService.schedule(() -> {
            System.out.println(LocalDateTime.now().format(dateTimeFormatter) +"每隔1s执行一次");
        },1, TimeUnit.SECONDS);

        scheduleService.schedule(() -> {
            System.out.println(LocalDateTime.now().format(dateTimeFormatter) +"每隔2s执行一次");
        },2, TimeUnit.SECONDS);

        Thread.sleep(1000);
    }
}
