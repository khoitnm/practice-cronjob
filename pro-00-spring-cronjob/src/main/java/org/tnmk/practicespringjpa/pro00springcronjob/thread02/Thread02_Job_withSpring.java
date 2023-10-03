package org.tnmk.practicespringjpa.pro00springcronjob.thread02;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
@RequiredArgsConstructor
public class Thread02_Job_withSpring {
    private static AtomicInteger count = new AtomicInteger(0);

    private final ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Scheduled(fixedDelay = 1000)
    public void doSomething() throws InterruptedException {
        log.info("Thread02_Job_withSpring doSomething {}", count.getAndIncrement());

        // Cancel the whole scheduling after 5 second.
        if (count.get() >= 3) {
            ScheduledExecutorService executor = threadPoolTaskScheduler.getScheduledExecutor();
            executor.awaitTermination(1, TimeUnit.SECONDS);
            executor.shutdown();
            log.info("shutdown");
        }
    }
}
