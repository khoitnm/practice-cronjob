package org.tnmk.practicespringjpa.pro00springcronjob.thread02;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.tnmk.practicespringjpa.pro00springcronjob.Starter;
import org.tnmk.practicespringjpa.pro00springcronjob.common.utils.ThreadUtils;

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

        // We can cancel the whole scheduling if we want.
        if (count.get() >= 15) {
            ScheduledExecutorService executor = threadPoolTaskScheduler.getScheduledExecutor();
            executor.awaitTermination(1, TimeUnit.SECONDS);
            executor.shutdown();
            log.info("Thread02 stop after count {}.", count.get());
        }
    }

    /**
     * By default, this job will never be finished because it's just run at fixed rate.
     * However, we need it to stop when other jobs are finished,
     * that's why in {@link Starter},
     * we apply {@code ((ConfigurableApplicationContext) context).close()},
     * that method will trigger this {@link #end()} method.
     */
    @EventListener(ContextClosedEvent.class)
    public void end() {
        log.info("Thread02: force ending: start...");
        ThreadUtils.sleep(2000);
        log.info("Thread02: force ending: finished!!!");
    }
}
